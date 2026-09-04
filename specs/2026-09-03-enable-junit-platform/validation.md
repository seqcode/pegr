# Enable the JUnit Platform — Validation

**Bar:** the suite must demonstrably execute tests on Java 21, and the build must produce
a deployable WAR. Not "the build is green" — a green build reporting zero tests is the
defect being fixed.

## Merge criteria

- [ ] `./gradlew clean build` green on JDK 21 with **no** `JAVA_HOME` override.
- [ ] The test report shows a **non-zero** test count. This is the criterion that matters;
      `BUILD SUCCESSFUL` alone was already true when nothing ran.
- [ ] `TEST-pegr.SampleServiceSpec.xml` exists with `tests="12" failures="0"`.
- [ ] Both WARs are produced at roughly their previous sizes.
- [ ] Verified with the compiled-script cache cleared, so no cached script can mask a
      build-file compilation failure.
- [ ] `build.gradle` changes by exactly one line; no dependency or plugin version moves.

## Automated

| Check | Command | Expected |
|---|---|---|
| Clean the script cache first | `rm -rf ~/.gradle/caches/*/scripts ~/.gradle/caches/*/scripts-remapped` | — mandatory before the run below |
| Full build | `cd pegr && ./gradlew clean build` | BUILD SUCCESSFUL |
| Tests actually ran | `python3 -c "…"` on `build/reports/tests/test/index.html`, or read the counters | `12 tests, 0 failures` — **not** `0 tests` |
| Per-class results | `ls build/test-results/test/TEST-*.xml` | `TEST-pegr.SampleServiceSpec.xml` present |
| Toolchain in use | `./gradlew -version` | `Gradle 8.5`, `JVM: 21.x` |
| WARs produced | `ls -la build/libs/*.war` | `pegr-0.1.war` ≈ 168 MB, `pegr-0.1-plain.war` ≈ 153 MB |

A deliberately failing spec is the strongest check available: add one, confirm the build
goes red, remove it. Until the suite can fail, "12 tests passed" is unproven.

## Manual QA

1. **Deploy the Gradle 8.5 WAR to staging.** *Expected:* the app starts and serves the
   login page. Nothing in this branch touches application code, but the WAR is assembled
   by a different Gradle major version and has never been run anywhere.
2. **Exercise one report and one API call.** *Expected:* unchanged behaviour. This is a
   spot check that asset-pipeline output and resource packaging survived the Gradle move.

## Regression risk

- **The Gradle major-version bump is the whole risk.** The build file changes by one line;
  everything else is the toolchain. Plugin behaviour under Gradle 8 — asset-pipeline,
  `grails-gradle-plugin` 6.2.4, the webdriver-binaries plugin — is exercised only by the
  local build so far.
- **Gradle 9.0 incompatibility.** `clean build` warns that deprecated features are in use.
  Unidentified; it constrains Phase 3, not this merge.
- **Newly-running tests may start failing later.** `SampleServiceSpec` passes today, but it
  has never run before, so nothing guarantees it stays green as the upgrade proceeds. That
  is the point of the change, and it will feel like new breakage.
- **Not touched:** application code, dependencies, plugin versions, schema, Liquibase.
- **`integrationTest` remains `NO-SOURCE`.** `src/integration-test/` holds only
  `GebConfig.groovy`. This branch does not change that, and the tech-stack's "Geb
  functional tests" remain aspirational.

## Rollback

`git revert` the commit. The wrapper reverts with it, and Gradle re-downloads 7.6.3 on the
next invocation. No schema, no config, no deployment-side change. Note that reverting
restores a build in which `./gradlew test` silently runs nothing.
