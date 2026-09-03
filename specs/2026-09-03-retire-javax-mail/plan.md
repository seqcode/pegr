# Retire `libs/javax.mail.jar` — Plan

**Roadmap item:** P0 → Phase 0, "Replace `libs/` jars with Maven dependencies"

This is the smallest of the four `libs/` branches and exists partly to establish the
pattern the other three follow. Note that the **pinned-then-bump** shape agreed for this
work does not apply here: there is no dependency to declare, because the `mail` plugin
already resolves JavaMail 1.6.2 from Maven. That shape gets its first real exercise on
`feature/retire-zxing`. What this branch does establish is the per-branch validation
discipline — capture the classpath before, delete, compare after, QA only the paths the
jar touches.

## 1. Capture the before state

- [ ] `./gradlew dependencies --configuration runtimeClasspath > before.txt` and keep it
      outside the repo. This is the baseline every later branch diffs against.
- [ ] Record which JavaMail copy actually wins today. `fileTree` entries do not appear in
      the dependency report, so the report alone cannot answer this — inspect the real
      classpath ordering, e.g. print `javax.mail.Session`'s code source at runtime from
      the Grails console, or check the assembled WAR's `WEB-INF/lib`.
- [ ] Ask whether the 2020 vendoring was deliberate (requirements → open questions).

**Done when:** the baseline is saved and it is known whether 1.5.6 or 1.6.2 is live, so
the effect of the deletion can be stated rather than guessed.

## 2. Delete the jar

- [ ] `git rm pegr/libs/javax.mail.jar`.
- [ ] Leave `fileTree(dir: 'libs', include: '*.jar')` in `build.gradle` — three jars
      remain and the glob still needs it. `feature/retire-opencsv` removes the line.
- [ ] `./gradlew clean build`.
- [ ] `./gradlew dependencies --configuration runtimeClasspath` and diff against
      `before.txt`. Expect **no change**: the plugin's 1.6.2 was already listed, and the
      vendored jar never appeared in the report to begin with.

**Done when:** `pegr/libs/javax.mail.jar` is gone from the working tree and `git ls-files`,
the build is green, and the module dependency report is byte-identical to the baseline.

## 3. Verify mail still works

- [ ] Restart `bootRun` — `UserController` holds one of the two send sites and controllers
      do not hot-reload.
- [ ] Exercise both mail paths (validation steps 3 and 4).

**Done when:** both emails send against a real SMTP host with no `NoClassDefFoundError`.

## 4. Close out

- [ ] Note in the PR whether the deletion was a true no-op or an effective 1.5.6 → 1.6.2
      upgrade, based on group 1's finding.
- [ ] Leave `specs/roadmap.md` alone — the Phase 0 bullet is not complete until all four
      branches have merged into `grails7`.

**Done when:** the PR into `grails7` states the observed classpath effect, not just the
diff.
