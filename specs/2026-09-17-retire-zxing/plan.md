# Retire `libs/` ZXing jars and `grails-wrapper.jar` — Plan

**Roadmap item:** P0 → Phase 0, "Replace `libs/` jars with Maven dependencies; delete
`grails-wrapper.jar`"

Groups run strictly in order. Groups 1 → 2 → 3 depend on each other: each one's check is the
previous group's result. Group 4 does not depend on the ZXing work and could run at any
point, but it comes last so each commit changes one thing. Each group is one commit, with a
green `./gradlew clean build` at the end.

## 1. Characterization spec against the vendored jars — **done 2026-09-17**

- [x] Save a baseline outside the repo: `./gradlew dependencies --configuration
      runtimeClasspath`, and `unzip -l build/libs/pegr-0.1.war | grep -iE 'zxing|core-|javase|jcommander|jai'`.
      Dependency report is 874 lines, with no `com.google.zxing` entry. `WEB-INF/lib` holds
      `core-3.2.1.jar`, `javase-3.2.1.jar`, `grails-wrapper.jar`, `opencsv-3.7.jar` and
      `jai-imageio-core-1.4.0.jar`.
- [x] Add `pegr/src/test/groovy/pegr/BarcodeServiceSpec.groovy`:
  - Call `renderImage` with a `MockHttpServletResponse` (or a stub whose `outputStream` is a
    `ByteArrayOutputStream`). Read the bytes with `ImageIO.read` and decode with
    `MultiFormatReader` over `BufferedImageLuminanceSource` → `HybridBinarizer`.
  - Data table covering `formatStr` `"QR"` and `null`/other (→ CODE_39), with realistic
    barcodes (`P1R`, `P123456R`) and the widths/heights the views use (100×100, 75×75,
    60×60 for QR). Pick a CODE_39 size wide enough to decode. Find it by probing, and
    record the value in the spec.
  - Assert the decoded text equals the input and the decoded format is the expected
    `BarcodeFormat`.
  - Assert the output is a PNG of the requested dimensions, so a sizing change shows up.
  - No GORM needed: `renderImage` does not touch domain classes. Instantiate the service
    directly rather than with `ServiceUnitTest`, unless that turns out to be needed.
- [x] `./gradlew test` passes, and the test count goes up by the new rows, starting from the
      current 16.

**Result:** 15 new rows. QR uses 3 barcodes × 3 view sizes, all at their exact requested
dimensions. CODE_39 uses 2 barcodes × `formatStr` `null`/`""`/`"CODE_39"` at 300×80, also
exact. Decoding uses `PURE_BARCODE`. `./gradlew clean build`: **31 tests, 0 failures**
(16 + 15).

**Done when:** `BarcodeServiceSpec` passes against `libs/core-3.2.1.jar` +
`libs/javase-3.2.1.jar`, and the build's reported test count includes it.

## 2. Move ZXing to Maven, pinned at 3.2.1 — **done 2026-09-17**

- [x] Add `implementation 'com.google.zxing:core:3.2.1'` and
      `implementation 'com.google.zxing:javase:3.2.1'` to `pegr/build.gradle`.
- [x] `git rm pegr/libs/core-3.2.1.jar pegr/libs/javase-3.2.1.jar`.
- [x] Keep the `fileTree` line: `opencsv-3.7.jar` still needs it.
- [x] `./gradlew clean build`. `BarcodeServiceSpec` should pass unchanged. **31 tests, 0 failures.**
- [x] Diff the dependency report against the baseline. **Correction to the original
      prediction:** besides the two `com.google.zxing` entries, the report has one more
      addition. `javase:3.2.1` depends on `com.beust:jcommander:1.48`. The bundled jars
      hid this because the POM was never read. ZXing uses `jcommander` only for its
      command-line decoder (`CommandLineRunner`), not for encoding, so it stays transitive
      and is not excluded.
- [x] Check the WAR has exactly one copy of each. The WAR jar list differs from the
      baseline only by `jcommander-1.48.jar`. The sha1s of `core-3.2.1.jar`
      (`2287494d…`) and `javase-3.2.1.jar` (`78e98099…`) match the bundled jars and the
      Gradle cache, so the bundled copies were stock Maven Central artifacts and this step
      changes no ZXing bytes.

**Done when:** the two jars are gone from `git ls-files pegr/libs`, `build.gradle` declares
both coordinates at 3.2.1, the build and spec are green, and the dependency diff shows only
the two ZXing entries plus `jcommander:1.48`.

## 3. Bump ZXing 3.2.1 → 3.5.4 — **done 2026-09-17**

- [x] Change both coordinates to `3.5.4`.
- [x] Read the ZXing release notes for 3.3.x–3.5.x. **Done by diffing 3.2.1 vs 3.5.4 source jars instead; findings in `requirements.md` → Open questions.** Look for changes to `Code39Writer`,
      `QRCodeWriter` (default margin, error correction), `MatrixToImageWriter`, and
      `EncodeHintType.CHARACTER_SET`. Note anything relevant in `requirements.md` → Open
      questions.
- [x] `./gradlew clean build`. `BarcodeServiceSpec` passes **without edits**. **31 tests, 0 failures; the spec has one commit (`55c8da96`).** If a
      dimension assertion fails, stop: it is a visible change to printed labels and goes
      back to the user, not into a spec fix.
- [x] Check the dependency report: `com.beust:jcommander:1.48` gone and `org.jcommander:jcommander:1.85` present (group changed), and `jai-imageio-core`
      still resolves to 1.4.0, not a conflicting version.
- [x] Check the WAR: `core-3.5.4.jar`, `javase-3.5.4.jar`, `jcommander-*.jar`, and one
      `jai-imageio-core-1.4.0.jar`. No 3.2.1 jar left.

**Result:** the dependency diff against group 2 is exactly the ZXing pair 3.2.1 → 3.5.4,
`com.beust:jcommander:1.48` → `org.jcommander:jcommander:1.85`, and a new transitive edge to
`jai-imageio-core:1.4.0` (the version PEGR already declares, so no conflict). The WAR diff is
exactly three swaps: `core`, `javase`, `jcommander`. Nothing else in `WEB-INF/lib` moved.

**Done when:** the build is green at 3.5.4, the spec is unmodified since group 1, and the WAR
contains only 3.5.4 ZXing jars.

## 4. Delete `grails-wrapper.jar` and the `grailsw` scripts — **done 2026-09-17**

- [x] `grep -rn grailsw` across the repo (excluding `build/`, `.gradle/`) to confirm the only
      references are the two scripts. Everything else that matched was documentation under
      `specs/`.
- [x] `git rm pegr/libs/grails-wrapper.jar pegr/grailsw pegr/grailsw.bat`.
- [x] `./gradlew clean build`. Confirm `grails-wrapper.jar` is absent from `WEB-INF/lib`.
      Today it ships there through `fileTree`, even though nothing at runtime uses it.

**Result:** 31 tests, 0 failures. The WAR differs from group 3 only by the missing
`grails-wrapper.jar`. The dependency report is unchanged. `git ls-files pegr/libs` →
`pegr/libs/opencsv-3.7.jar`.

**Done when:** `git ls-files pegr/libs` lists only `opencsv-3.7.jar`, the scripts are gone,
and the build is green.

## 5. Manual QA and close-out

- [ ] Restart `bootRun`. `displayBarcode` is on `ItemController`, which does not hot-reload.
- [ ] Run the manual QA in `validation.md`, including one printed label scanned with a
      physical scanner.
- [ ] PR into `grails7`. It must say this is a **ZXing 3.2.1 → 3.5.4 upgrade**, not just a
      file move, and list the commits so reviewers can separate the pin from the bump.
- [ ] Leave `specs/roadmap.md` untouched. The Phase 0 bullet closes when opencsv is done.
- [ ] Add a `CHANGELOG.md` entry on merge.

**Done when:** every merge criterion in `validation.md` is ticked or explicitly marked
outstanding, and the PR is open.
