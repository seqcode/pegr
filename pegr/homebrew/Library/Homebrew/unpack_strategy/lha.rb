module UnpackStrategy
  class Lha
    include UnpackStrategy

    def self.can_extract?(path:, magic_number:)
      magic_number.match?(/\A..-(lh0|lh1|lz4|lz5|lzs|lh\\40|lhd|lh2|lh3|lh4|lh5)-/n)
    end

    def dependencies
      @dependencies ||= [Formula["lha"]]
    end

    private

    def extract_to_dir(unpack_dir, basename:, verbose:)
      system_command! "lha",
                      args: ["xq2w=#{unpack_dir}", path],
                      env: { "PATH" => PATH.new(Formula["lha"].opt_bin, ENV["PATH"]) }
    end
  end
end
