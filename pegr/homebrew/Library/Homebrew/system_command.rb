require "open3"
require "ostruct"
require "vendor/plist/plist"
require "shellwords"

require "extend/io"
require "extend/hash_validator"
using HashValidator
require "extend/predicable"

def system_command(*args)
  SystemCommand.run(*args)
end

def system_command!(*args)
  SystemCommand.run!(*args)
end

class SystemCommand
  extend Predicable

  def self.run(executable, **options)
    new(executable, **options).run!
  end

  def self.run!(command, **options)
    run(command, **options, must_succeed: true)
  end

  def run!
    @merged_output = []
    odebug command.shelljoin

    each_output_line do |type, line|
      case type
      when :stdout
        puts line.chomp if print_stdout?
        @merged_output << [:stdout, line]
      when :stderr
        $stderr.puts Formatter.error(line.chomp) if print_stderr?
        @merged_output << [:stderr, line]
      end
    end

    assert_success if must_succeed?
    result
  end

  def initialize(executable, args: [], sudo: false, input: [], print_stdout: false, print_stderr: true, must_succeed: false, env: {}, **options)
    @executable = executable
    @args = args
    @sudo = sudo
    @input = [*input]
    @print_stdout = print_stdout
    @print_stderr = print_stderr
    @must_succeed = must_succeed
    options.assert_valid_keys!(:chdir)
    @options = options
    @env = env

    @env.keys.grep_v(/^[\w&&\D]\w*$/) do |name|
      raise ArgumentError, "Invalid variable name: '#{name}'"
    end
  end

  def command
    [*sudo_prefix, *env_args, executable.to_s, *expanded_args]
  end

  private

  attr_reader :executable, :args, :input, :options, :env

  attr_predicate :sudo?, :print_stdout?, :print_stderr?, :must_succeed?

  def env_args
    return [] if env.empty?

    variables = env.map do |name, value|
      sanitized_name = Shellwords.escape(name)
      sanitized_value = Shellwords.escape(value)
      "#{sanitized_name}=#{sanitized_value}"
    end

    ["env", *variables]
  end

  def sudo_prefix
    return [] unless sudo?
    askpass_flags = ENV.key?("SUDO_ASKPASS") ? ["-A"] : []
    ["/usr/bin/sudo", *askpass_flags, "-E", "--"]
  end

  def assert_success
    return if @status.success?
    raise ErrorDuringExecution.new(command,
                                   status: @status,
                                   output: @merged_output)
  end

  def expanded_args
    @expanded_args ||= args.map do |arg|
      if arg.respond_to?(:to_path)
        File.absolute_path(arg)
      elsif arg.is_a?(Integer) || arg.is_a?(Float)
        arg.to_s
      else
        arg.to_str
      end
    end
  end

  def each_output_line(&b)
    executable, *args = command

    raw_stdin, raw_stdout, raw_stderr, raw_wait_thr =
      Open3.popen3([executable, executable], *args, **options)

    write_input_to(raw_stdin)
    raw_stdin.close_write
    each_line_from [raw_stdout, raw_stderr], &b

    @status = raw_wait_thr.value
  rescue SystemCallError => e
    @status = $CHILD_STATUS
    @merged_output << [:stderr, e.message]
  end

  def write_input_to(raw_stdin)
    input.each(&raw_stdin.method(:write))
  end

  def each_line_from(sources)
    loop do
      readable_sources, = IO.select(sources)

      readable_sources = readable_sources.reject(&:eof?)

      break if readable_sources.empty?

      readable_sources.each do |source|
        begin
          line = source.readline_nonblock || ""
          type = (source == sources[0]) ? :stdout : :stderr
          yield(type, line)
        rescue IO::WaitReadable, EOFError
          next
        end
      end
    end

    sources.each(&:close_read)
  end

  def result
    output = @merged_output.each_with_object(stdout: "", stderr: "") do |(type, line), hash|
      hash[type] << line
    end

    Result.new(command, output[:stdout], output[:stderr], @status.exitstatus)
  end

  class Result
    attr_accessor :command, :stdout, :stderr, :exit_status

    def initialize(command, stdout, stderr, exit_status)
      @command     = command
      @stdout      = stdout
      @stderr      = stderr
      @exit_status = exit_status
    end

    def success?
      @exit_status.zero?
    end

    def plist
      @plist ||= begin
        output = stdout

        if /\A(?<garbage>.*?)<\?\s*xml/m =~ output
          output = output.sub(/\A#{Regexp.escape(garbage)}/m, "")
          warn_plist_garbage(garbage)
        end

        if %r{<\s*/\s*plist\s*>(?<garbage>.*?)\Z}m =~ output
          output = output.sub(/#{Regexp.escape(garbage)}\Z/, "")
          warn_plist_garbage(garbage)
        end

        Plist.parse_xml(output)
      end
    end

    def warn_plist_garbage(garbage)
      return unless ARGV.verbose?
      return unless garbage =~ /\S/
      opoo "Received non-XML output from #{Formatter.identifier(command.first)}:"
      $stderr.puts garbage.strip
    end
    private :warn_plist_garbage
  end
end
