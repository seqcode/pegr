# Retire `libs/javax.mail.jar` — Plan

**Roadmap item:** P0 → Phase 0, "Replace `libs/` jars with Maven dependencies"

This is the smallest of the four `libs/` branches and exists partly to establish the
pattern the other three follow. Note that the **pinned-then-bump** shape agreed for this
work does not apply here: there is no dependency to declare, because the `mail` plugin
already resolves JavaMail 1.6.2 from Maven. That shape gets its first real exercise on
`feature/retire-zxing`. What this branch does establish is the per-branch validation
discipline — capture the classpath before, delete, compare after, QA only the paths the
jar touches.

## 1. Capture the before state — **done 2026-09-03**

- [x] Baseline dependency report saved outside the repo (879 lines).
- [x] Established which JavaMail copy wins. `fileTree` entries never appear in the
      dependency report, and Gradle init scripts will not compile under 7.6.3 on Java 21
      ("Unsupported class file major version 65"), so the order came from the
      `application` plugin's generated start script: `javax.mail.jar` is entry 3 of 222,
      the 1.6.2 pair 168/169. Confirmed by probing the real `installDist` classpath.
- [x] Vendoring intent closed as moot — see requirements → open questions.

**Result:** the vendored **1.5.6 is live**, so this branch is a 1.5.6 → 1.6.2 upgrade, not
a no-op. `getTransport("smtp")` resolves to `com.sun.mail.smtp.SMTPTransport` both before
and after.

## 2. Delete the jar — **done 2026-09-03**

- [x] `git rm pegr/libs/javax.mail.jar`.
- [x] Left `fileTree(dir: 'libs', include: '*.jar')` in `build.gradle` — four jars remain
      and the glob still needs it. `feature/retire-opencsv` removes the line.
- [x] `./gradlew clean build` — BUILD SUCCESSFUL in 2m31s, `test` included.
- [x] Dependency report diffed against the baseline: identical but for the build-time
      line, as expected — `fileTree` jars never appeared in it.
- [x] WAR now ships `javax.mail-api-1.6.2.jar` and `javax.mail-1.6.2.jar` only;
      `javax.mail.jar` is gone.
- [x] Classpath re-probed on the real post-deletion `installDist` output. Entries shifted
      3→gone and 168/169→167/168; `Session` now loads from `javax.mail-api-1.6.2.jar`,
      providers from `javax.mail-1.6.2.jar`, and `getTransport("smtp")` still resolves to
      `com.sun.mail.smtp.SMTPTransport` — matching the group 1 prediction exactly.

**Done when:** `pegr/libs/javax.mail.jar` is gone from the working tree and `git ls-files`,
the build is green, and the module dependency report is byte-identical to the baseline.

## 3. Verify mail still works

- [ ] Restart `bootRun` — `UserController` holds one of the two send sites and controllers
      do not hot-reload.
- [ ] Exercise both mail paths (validation steps 4 and 5).

**Done when:** both emails send against a real SMTP host with no `NoClassDefFoundError`.

## 4. Close out

- [ ] State in the PR that this is an effective JavaMail 1.5.6 → 1.6.2 upgrade (group 1),
      so it is reviewed as a version change rather than a file cleanup.
- [ ] Leave `specs/roadmap.md` alone — the Phase 0 bullet is not complete until all four
      branches have merged into `grails7`.

**Done when:** the PR into `grails7` states the observed classpath effect, not just the
diff.
