# Retire `libs/` ZXing jars and `grails-wrapper.jar` — Validation

**Bar:** round-trip spec + dev QA. An automated check proves the barcodes still decode after
the version change. A person checks that images still render in the app and that a printed
label still scans. CSV import, mail, database, and the API are not affected and are not
tested here.

## Merge criteria

- [ ] `./gradlew clean build` green from `pegr/`.
- [ ] `./gradlew test` green: **31 tests** (16 existing + 15
      `BarcodeServiceSpec` rows).
- [ ] `BarcodeServiceSpec` was committed in group 1, passed against the vendored jars, and
      is **unchanged** in the groups 2 and 3 commits (`git log -p` on the file shows one commit).
- [ ] `git ls-files pegr/libs` lists only `opencsv-3.7.jar`.
- [ ] `pegr/grailsw` and `pegr/grailsw.bat` are deleted.
- [ ] `build.gradle` declares `com.google.zxing:core:3.5.4` and `com.google.zxing:javase:3.5.4`.
      The `fileTree` line is still present.
- [ ] The WAR has no 3.2.1 ZXing jar and no `grails-wrapper.jar`, and has exactly one
      `jai-imageio-core` jar.
- [ ] Manual QA steps 1–5 pass on dev.
- [ ] One printed label from step 4 scans correctly on the lab's scanner, or this is marked
      **Outstanding** with a reason rather than silently skipped (see Open questions in
      `requirements.md`).
- [ ] The PR describes this as a ZXing 3.2.1 → 3.5.4 upgrade.

## Automated

| Check | Command | Expected |
|---|---|---|
| Compile + assemble | `cd pegr && ./gradlew clean build` | BUILD SUCCESSFUL; `build/libs/pegr-0.1.war` produced |
| Unit tests | `cd pegr && ./gradlew test` | BUILD SUCCESSFUL; 31 tests, 0 failures |
| Round-trip decode | `cd pegr && ./gradlew test --tests pegr.BarcodeServiceSpec` | All rows pass: QR and CODE_39 decode to the input string at the requested dimensions |
| Spec not edited after group 1 | `git log --oneline -- pegr/src/test/groovy/pegr/BarcodeServiceSpec.groovy` | One commit |
| ZXing declared | `cd pegr && ./gradlew dependencies --configuration runtimeClasspath \| grep -A3 zxing` | `core:3.5.4`, `javase:3.5.4` → `jcommander`, `jai-imageio-core:1.4.0` |
| No duplicate jai-imageio | same report, `grep jai-imageio` | Only `1.4.0`, and no `->` conflict arrow to another version |
| libs/ contents | `git ls-files pegr/libs` | `pegr/libs/opencsv-3.7.jar` only |
| WAR contents | `unzip -l pegr/build/libs/pegr-0.1.war \| grep -iE 'zxing\|core-3\|javase-\|jcommander\|jai-imageio\|grails-wrapper'` | `core-3.5.4.jar`, `javase-3.5.4.jar`, one `jcommander`, one `jai-imageio-core-1.4.0.jar`; no `*-3.2.1.jar`, no `grails-wrapper.jar` |
| No stray wrapper refs | `grep -rn grailsw . --exclude-dir=build --exclude-dir=.gradle` (repo root) | No matches |

## Manual QA

Environment: `./gradlew bootRun` on **dev H2**, logged in as `labadmin`. Barcode rendering
does not depend on production data: it encodes whatever string it is given. An empty dev
database is enough once step 1 has created an item. Restart `bootRun` first.

1. **Create an item with a generated barcode.** Use the item create form that generates a
   barcode (`_formWithBarcode.gsp`). *Expected:* a `P<n>R` barcode is assigned and a QR image
   appears. The AJAX `displayBarcode?…&formatStr=QR` request returns `200 image/png`.
2. **Item show page.** Open the item. *Expected:* the QR image from `_barcodeImage.gsp`
   renders at 100×100. Switch to the CODE_39 view if the page offers it (the JS at
   `_barcodeImage.gsp:52` builds a link without `formatStr`). *Expected:* a linear barcode
   renders, not a broken image.
3. **Direct request, both formats.** Open
   `/pegr/item/displayBarcode?barcode=P123R&width=100&height=100&formatStr=QR` and the same
   URL with `formatStr` removed and `width=300&height=80`. *Expected:* both return PNGs. A
   phone scanner app reads `P123R` from both.
4. **Barcode sheet.** Open the item barcode list page (`generateBarcodeList.gsp`, 80 codes).
   *Expected:* every cell shows a QR image and none are broken. **Print it** and scan one
   label on the lab's scanner. *Expected:* it reads the printed `P<n>R` value.
5. **Protocol instance bag barcode.** From a protocol instance bag, use the generate-barcode
   flow (`protocolInstanceBag/generateBarcode.gsp`). *Expected:* QR image renders.

If an image is broken, check the server log first. `NoClassDefFoundError` points at the
classpath (group 2/3). An exception from inside `com.google.zxing.*` points at the version
bump (group 3).

## Regression risk

- **Label compatibility with existing stock.** Labels printed under 3.2.1 are stuck on
  physical tubes. The bump changes only how *new* images are drawn, not stored barcode
  strings, so old labels keep matching their records. The risk is new labels scanning
  differently if the default QR margin or error-correction level changed between versions.
  The dimension assertions in the spec and step 4's physical scan cover this.
- **Code 39 character handling.** Real barcodes are `P<digits>R`, all in the basic Code 39
  set. If any legacy barcode holds lowercase or punctuation, newer ZXing encoding could
  differ. Not expected, and not tested beyond the `P…R` shape.
- **`jai-imageio-core` version conflict.** `javase` 3.5.x brings it in transitively, and
  PEGR already declares 1.4.0. If a future bump resolves a different version, image I/O
  elsewhere (uploads, reports) could be affected. The dependency-report check guards this.
- **`grailsw` used outside the repo.** Anything outside the repo that invokes `pegr/grailsw`
  breaks. No in-repo references exist. Confirm with whoever maintains deployment.
- **Not touched:** CSV imports (opencsv), mail, the API contract, Quartz jobs, report
  rendering, role-based access, Liquibase state. No changesets are added.

## Rollback

Code-only, no schema or config change. `git revert` the merge. To keep the Maven move but
undo only the version change, revert the group 3 commit to go back to 3.2.1 from Maven.
Printed labels need no rollback, since stored barcode strings never change.
