require "cmd/style"

describe "brew style" do
  around do |example|
    begin
      FileUtils.ln_s HOMEBREW_LIBRARY_PATH, HOMEBREW_LIBRARY/"Homebrew"
      FileUtils.ln_s HOMEBREW_LIBRARY_PATH.parent/".rubocop.yml", HOMEBREW_LIBRARY/".rubocop_audit.yml"

      example.run
    ensure
      FileUtils.rm_f HOMEBREW_LIBRARY/"Homebrew"
      FileUtils.rm_f HOMEBREW_LIBRARY/".rubocop_audit.yml"
    end
  end

  describe "Homebrew::check_style_json" do
    let(:dir) { mktmpdir }

    it "returns RubocopResults when RuboCop reports offenses" do
      formula = dir/"my-formula.rb"

      formula.write <<~'EOS'
        class MyFormula < Formula

        end
      EOS

      rubocop_result = Homebrew::Style.check_style_json([formula])

      expect(rubocop_result.file_offenses(formula.realpath.to_s).map(&:message))
        .to include("Extra empty line detected at class body beginning.")
    end
  end

  describe "Homebrew::check_style_and_print" do
    let(:dir) { mktmpdir }

    it "returns false for conforming file with only audit-level violations" do
      # This file is known to use non-rocket hashes and other things that trigger audit,
      # but not regular, cop violations
      target_file = HOMEBREW_LIBRARY_PATH/"utils.rb"

      rubocop_result = Homebrew::Style.check_style_and_print([target_file])

      expect(rubocop_result).to eq false
    end
  end
end
