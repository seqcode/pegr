# Enable the JUnit Platform — Plan

**Roadmap item:** P0 → Phase 0, test coverage as the upgrade's regression net.

Groups 1–3 are complete; group 4 needs a human.

## 1. Establish that the suite runs nothing — **done 2026-09-03**

- [x] Confirmed `./gradlew test` reports success with `0 tests, 0 failures` in
      `build/reports/tests/test/index.html`, and writes no `TEST-*.xml`.
- [x] Traced the cause: Spock resolves to 2.3-groovy-3.0, which needs the JUnit Platform;
      `tasks.withType(Test)` did not call `useJUnitPlatform()`.

**Result:** `SampleServiceSpec` has never run.

## 2. Establish why the one-line fix is blocked — **done 2026-09-03**

- [x] Adding `useJUnitPlatform()` on Java 21 fails with `Unsupported class file major
      version 65` when Gradle recompiles the build script.
- [x] Confirmed the failure is real and not a stale cache, by clearing
      `~/.gradle/caches/7.6.3/scripts` and rerunning.
- [x] Confirmed the fix itself is sound by running it under JDK 11: 16 tests, 0 failures.

**Result:** the blocker is Gradle 7.6.3 on Java 21, not the change.

**Trap for anyone reproducing this:** Gradle caches compiled build scripts by content
hash. A first attempt reported `BUILD SUCCESSFUL` on Java 21 purely because that exact
file content had been compiled under JDK 11 minutes earlier. Clear the script cache or the
result is meaningless.

## 3. Move Gradle and verify on Java 21 — **done 2026-09-03**

- [x] `gradle-wrapper.properties` → `gradle-8.5-bin.zip`, then `./gradlew wrapper
      --gradle-version 8.5` to regenerate the wrapper jar and `gradlew` script.
- [x] `./gradlew clean build` on JDK 21.0.8 → BUILD SUCCESSFUL, **12 tests, 0 failures**,
      both WARs produced.

**Done when:** the suite executes on Java 21 with no JDK override. Met.

## 4. Confirm nothing downstream broke — **needs a human**

- [ ] Deploy the Gradle 8.5 WAR to staging and confirm the app starts and serves.
- [ ] Identify what emits the Gradle 9.0 deprecation warning (requirements → open
      questions).
- [ ] Decide whether to rename the branch, since it carries a Gradle upgrade.

**Done when:** a Gradle 8.5-built WAR has run somewhere other than a developer's machine.
