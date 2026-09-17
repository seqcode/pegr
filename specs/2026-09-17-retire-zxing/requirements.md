# Retire `libs/` ZXing jars and `grails-wrapper.jar` — Requirements

**Roadmap item:** P0 → Phase 0, "Replace `libs/` jars with Maven dependencies; delete
`grails-wrapper.jar`" — the `core-3.2.1.jar` + `javase-3.2.1.jar` (ZXing barcode) and
`grails-wrapper.jar` rows of the bundled-jars list
**Branch:** `feature/retire-zxing`, off `grails7`
**Started:** 2026-09-17

## Context

`pegr/libs/` still holds four jars from `daefca6d` (2019-07-24), pulled in by
`implementation fileTree(dir: 'libs', include: '*.jar')`. This is the second of the
one-library-per-branch retirements. The first, `feature/retire-javax-mail`, merged as PR #374
and named this branch as the next one, with `grails-wrapper.jar` removed alongside it
(see `specs/2026-09-03-retire-javax-mail/requirements.md` → Out of scope).

Unlike javax.mail, **nothing resolves ZXing transitively.** `./gradlew dependencies
--configuration runtimeClasspath` has no `com.google.zxing` entry, so the vendored jars are
the only copy. Deleting them without declaring coordinates would fail to compile
`BarcodeService`. So this is the first branch that uses the **pinned-then-bump** approach
from the javax.mail plan: declare 3.2.1 exactly, prove nothing changed, then upgrade.

**What uses ZXing.** Only `grails-app/services/pegr/BarcodeService.groovy`:

- `renderImage(response, data, width, height, formatStr)` — `"QR"` → `QR_CODE`, anything
  else → `CODE_39`. Hints: `CHARACTER_SET = UTF8`. Writes PNG through
  `MatrixToImageWriter.writeToStream` (the `javase` jar).
- `renderImageToFile(...)` — no callers found in `grails-app/`. Kept, not in scope to remove.

It is reached through `ItemController.displayBarcode`, used by about ten views: item show and
barcode partials, the barcode sheet pages (`generateBarcodeList*.gsp`), protocol-instance-bag
barcode generation, and the JS in `_barcodeImage.gsp` / `generateBarcode.gsp` that asks for
the `CODE_39` variant. Barcode strings look like `P<id>R`
(`BarcodeService.generateBarcode`). The barcode-*string* generation code does not touch
ZXing and is out of the blast radius.

**`grails-wrapper.jar`** holds `grails/init/Start.class` (built 2017). It is used only by
`pegr/grailsw` and `pegr/grailsw.bat` (both from the initial commit). Grails 6 projects are
driven through `./gradlew`, and no docs mention `grailsw`.

**Version target.** On Maven Central, `com.google.zxing:javase` latest is **3.5.4**. The
`javase` 3.5.x POM lists `com.beust:jcommander` (already present at 1.48 through `javase` 3.2.1) and adds
`com.github.jai-imageio:jai-imageio-core` as transitive dependencies. PEGR already declares `jai-imageio-core:1.4.0`, the version the
3.5.3 POM names.

**Roadmap is stale.** `specs/roadmap.md` still calls `javax.mail.jar` "not merged". PR #374
has since merged. This branch leaves the roadmap alone, for the same reason the javax.mail
branch did: the Phase 0 bullet is not done until opencsv is also retired.

## In scope

- A Spock spec, `BarcodeServiceSpec`, that renders QR and CODE_39 PNGs through
  `BarcodeService.renderImage` and **decodes them back** with ZXing's reader to the original
  string. It is written and passing *against the vendored jars* before anything moves.
- Declare `com.google.zxing:core:3.2.1` and `com.google.zxing:javase:3.2.1` in
  `pegr/build.gradle`. `git rm` `libs/core-3.2.1.jar` and `libs/javase-3.2.1.jar`.
- Bump both coordinates to **3.5.4** in a separate commit.
- `git rm` `libs/grails-wrapper.jar`, `grailsw`, and `grailsw.bat`.

## Out of scope

- **opencsv 3.7** and **removing the `fileTree(dir: 'libs')` line.** Both belong to
  `feature/retire-opencsv`, which retires the last jar. `libs/` still holds `opencsv-3.7.jar`
  after this branch, and a `.DS_Store` that git does not track.
- **Any change to barcode behaviour**: string format, image sizes, the `QR`/`CODE_39`
  mapping, or the null-width handling in `displayBarcode`.
- **Removing the unused `renderImageToFile`.** It is dead code, but cleaning it up is not
  what this branch is for.
- **Declaring `jcommander` directly.** It stays transitive.

## Decisions

| Decision | Choice | Rationale |
|---|---|---|
| Scope | ZXing + `grails-wrapper.jar` (+ `grailsw` scripts) | User choice. Matches what the javax.mail spec agreed. The scripts go with the jar because they cannot work without it. |
| Sequencing | Test, pin, then bump | User choice. The spec is the only automated check that a version change still produces scannable images. Pinning first splits "moved to Maven" from "changed version" into separate commits. |
| Merge bar | Round-trip spec + dev QA | User choice. See `validation.md`. |
| Target version | 3.5.4 | Latest release on Maven Central as of 2026-09-17. |
| Branch base | `grails7` | Integration line for Phase 0, following the javax.mail branch. The skill's default `dev` → `master` rule does not apply: there is no `dev` branch, and `master` is the release line. |
| Round-trip vs. golden bytes | Decode and compare strings | Byte-for-byte PNG comparison would fail on harmless encoder changes. What matters is that the image still scans to the same value. |

## Open questions

- **Do printed labels need physical scanning, or is decoding in the spec enough?** The
  agreed bar is a decode spec plus dev QA that includes one printed/scanned label. Whether
  the lab's scanner handles CODE_39 differently from ZXing's reader is unknown. *Owner:* lab
  manager. Answer before merge.
- **Did Code 39 encoding change between 3.2.1 and 3.5.4?** Barcodes are uppercase
  `P<digits>R`, which is plain Code 39. Encoding could differ for characters outside the
  basic set. The spec should cover the real `P…R` shape, and group 3 checks the ZXing
  release notes. *Owner:* implementer.
- **Does anything outside the repo call `grailsw`?** No references in repo docs or scripts.
  Deployment tooling outside the repo is unknown. *Owner:* whoever deploys to staging.

## Constraints

From `specs/tech-stack.md`:

- No schema changes and no Liquibase changesets on this branch.
- `./gradlew clean build` from `pegr/` is the honest build check (`minifyJs`/`minifyCss` on).
- `./gradlew test` runs through the JUnit Platform. Check that the test count goes up by the
  new spec's features, not just `BUILD SUCCESSFUL`.
- The dev server does not hot-reload controllers. `displayBarcode` lives in `ItemController`,
  so restart `bootRun` before QA.
- Java 21, Gradle 8.5, Groovy 3.0.21. ZXing 3.5.x needs Java 8+.
