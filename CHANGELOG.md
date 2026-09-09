# Changelog

What changed in PEGR, newest first. Dates are when a change landed on the integration
branch (`grails7`), not when the work started.

## 2026-09-09

- *(not yet merged to `grails7`)* Retired the vendored `pegr/libs/javax.mail.jar`. This is
  effectively a **JavaMail 1.5.6 → 1.6.2 upgrade**, not a file cleanup: the vendored jar
  sat third on the runtime classpath, ahead of the 1.6.2 copy the `mail` plugin already
  resolved, so 1.5.6 was the version actually in use. No dependency was added — the plugin
  supplies it. A new `JavaMailClasspathSpec` guards the result, because the API and
  implementation now come from separate jars and a change that dropped the implementation
  half would still compile, failing only when someone requests a password reset. Mail
  sending against a real SMTP host is still unverified. See
  `specs/2026-09-03-retire-javax-mail/`.

## 2026-09-03

- `./gradlew test` now runs the test suite. It previously reported success while executing
  **zero** tests: Spock 2.3 discovers specs through the JUnit Platform and
  `useJUnitPlatform()` was never set, so the one existing spec had never run. `clean build`
  now reports 12 tests. Any past claim that this project had a green test suite meant
  nothing. (PR #373)
- Gradle 7.6.3 → 8.5. Not optional: Gradle 7.6.3 cannot compile a build script on Java 21,
  so `build.gradle` could not be edited at all — the build worked only because a script
  compiled under an older JDK was still cached. The wrapper fetches 8.5 automatically on
  the next build. **Anywhere that pins Gradle 7.6.3 outside this repo needs updating.**
  A Gradle 8.5-built WAR has not yet been deployed anywhere. (PR #373)

## Before 2026-09-03

Summarised from `specs/roadmap.md`; individual dates are in git history.

- **Platform** — Grails 6.2.3 on Java 21; Liquibase baseline reset to database-as-truth
  (2026-07-15).
- **Sample status tracking** — status on samples, reports, and sequence-run status colours
  (`#355`), including batch edit.
- **Pipeline API growth** — query samples by index and genome (`#364`), delete dataset
  link, bioinformatics API help page.
- **GEO submission support** — GEO accession capture (`#356`).
- **Reference data admin** — species sorting on genome admin (`#361`), ligation module,
  user deletion.
- **Documentation** — database schema diagrams under `docs/schema/`.
