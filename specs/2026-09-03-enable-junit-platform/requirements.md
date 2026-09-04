# Enable the JUnit Platform — Requirements

**Roadmap item:** P0 → Phase 0, "Raise test coverage … This is the regression net for
everything that follows — without it the upgrade is unverifiable." Prerequisite: the net
has to be connected before coverage is worth adding. Also pulls Phase 1's
"Gradle 7.6.3 → 8" forward — see Decisions.
**Branch:** `feature/enable-junit-platform`, off `grails7`
**Started:** 2026-09-03

## Context

`./gradlew test` ran **zero tests and reported success**. Spock 2.3 discovers specs
through the JUnit Platform; `tasks.withType(Test)` never called `useJUnitPlatform()`, so
Gradle used the JUnit 4 engine and found nothing. `SampleServiceSpec` — the repo's only
spec — has never executed. Every "green test suite" claim about this project was vacuous,
including the Phase 0 exit criterion.

The one-line fix is blocked by a second problem. Editing `build.gradle` invalidates
Gradle's compiled-script cache, and Gradle 7.6.3 (bundled Groovy 3.0.13) cannot recompile
a build script on Java 21:

```
BUG! exception in phase 'semantic analysis' in source unit '_BuildScript_'
Unsupported class file major version 65
```

The build worked only because a cached script compiled under an older JDK was still
valid. **`build.gradle` was effectively uneditable on Java 21**, which blocks every
remaining Phase 0 branch — retiring the ZXing and opencsv jars and swapping the DB driver
all need build-file edits.

## In scope

- `useJUnitPlatform()` in `tasks.withType(Test)`.
- Gradle wrapper 7.6.3 → 8.5, regenerated with `./gradlew wrapper` so the wrapper jar and
  `gradlew` script move too, not just `gradle-wrapper.properties`.

## Out of scope

- **New tests.** This branch makes the runner work; it adds no coverage.
- **Fixing the Gradle 9.0 deprecation warning** that `clean build` now emits. Needed
  before Phase 3's Gradle 9.6, not before this merges.
- **Any Grails, Groovy, or Spring Boot version change.** Only the Gradle wrapper moves.
- **Integration tests.** `src/integration-test/` holds only `GebConfig.groovy` — there are
  no Geb specs, so `integrationTest` is `NO-SOURCE` before and after.

## Decisions

| Decision | Choice | Rationale |
|---|---|---|
| How to reach Java 21 | Wrapper → Gradle 8.5 | Gradle added Java 21 support in 8.5. Nothing below it can recompile the build script on this JDK. |
| Alternative rejected | `org.gradle.java.home` → JDK 11, keep Gradle 7.6.3 | Works — `gradle.properties` is not a Groovy script, so no recompilation — and was verified at 16 tests passing. Rejected because it pins development to JDK 11 rather than the Java 21 the project targets. |
| Gradle version | 8.5, not latest 8.x | Lowest version that supports Java 21, so the jump is as small as it can be while solving the problem. |
| Scope of the Gradle move | Accepted into this branch | The roadmap has Gradle → 8 in Phase 1, but the test runner cannot be fixed without it. Arriving early is a consequence, not a choice. |

## Open questions

- **What emits the Gradle 9.0 deprecation warning?** `clean build` reports deprecated
  features incompatible with Gradle 9.0. `--warning-mode all` on `test` and `war` shows
  nothing, so it comes from elsewhere in the assemble path — likely the Grails or
  asset-pipeline plugin. Phase 3 targets Gradle 9.6, so it needs an answer by then.
- **Does Gradle 8.5 change anything at runtime?** The WAR builds and is the expected size,
  but nothing has been deployed from it. Confirm on staging before release.
- **Should the branch be renamed?** It now carries a Gradle major-version upgrade, which
  the name does not suggest.

## Constraints

- No schema changes, no Liquibase changesets.
- Java 21 is the target JVM; a fix that requires an older JDK for development does not
  count as a fix.
- Verification must clear `~/.gradle/caches/<version>/scripts` first — see validation.
