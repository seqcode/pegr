# Retire `libs/javax.mail.jar` — Validation

**Bar:** build + boot + QA of the paths this jar touches. Scaled to the change — this
branch deletes one file and adds nothing, so the CSV, barcode, and database paths are not
in question and are not tested here.

## Merge criteria

- [ ] `./gradlew clean build` green from `pegr/`.
- [ ] `./gradlew test` green. *(One spec exists in `pegr/src/test/`; this proves the build
      compiles, not that mail works. Steps 4–5 are the real check.)*
- [ ] `./gradlew bootRun` starts and serves the login page.
- [ ] `git ls-files pegr/libs` no longer lists `javax.mail.jar`.
- [ ] `build.gradle` is **unchanged** — no dependency added, `fileTree` line still present.
- [ ] Both mail paths send successfully (steps 4–5).
- [ ] The PR states this is an effective JavaMail 1.5.6 → 1.6.2 upgrade (established in
      group 1) and not a redundant-file cleanup.
- [ ] Staging's pre-deletion JavaMail version is recorded, so it is known whether
      production changes version at all.

## Automated

| Check | Command | Expected |
|---|---|---|
| Compile + assemble | `cd pegr && ./gradlew clean build` | BUILD SUCCESSFUL; `build/libs/pegr-0.1.war` produced |
| Unit tests | `cd pegr && ./gradlew test` | BUILD SUCCESSFUL |
| JavaMail still resolves | `cd pegr && ./gradlew dependencies --configuration runtimeClasspath \| grep -A2 'plugins:mail'` | `com.sun.mail:javax.mail:1.6.2` and `javax.activation:activation:1.1` still present |
| Dependency report unchanged | diff the report against the pre-deletion baseline | No difference — `fileTree` jars never appeared in it |
| Jar gone from the WAR | `unzip -l pegr/build/libs/pegr-0.1.war \| grep -i mail` | `javax.mail.jar` absent; `javax.mail-api-1.6.2.jar` and `javax.mail-1.6.2.jar` remain (both expected) |
| Classpath order | `./gradlew installDist` then `grep -o 'CLASSPATH=.*' build/scripts/pegr \| tr ':' '\n' \| grep -n mail` | No `javax.mail.jar` entry; the 1.6.2 pair unchanged in position |
| Nothing imports it | `grep -rn 'javax\.mail' pegr/grails-app pegr/src` | No matches |

The WAR check is the one that actually proves the point. The module dependency report
cannot see file dependencies, so it will look identical whether or not the deletion
worked — only the assembled artifact shows the duplicate is gone.

## Manual QA

Environment: `./gradlew bootRun` on dev. Mail needs real SMTP settings — `grails.mail.host`,
`grails.mail.port`, `grails.mail.username`, `grails.mail.password` in the external
`pegr-config.properties` (they are in `sample_files/pegr-config.properties` as empty keys).
**A dev config with blank mail settings makes steps 4–5 vacuous**; point them at a real or
catcher SMTP host before starting.

Restart `bootRun` first — `UserController` does not hot-reload.

1. ~~**Establish which copy is live**~~ — **done in group 1.** On the `bootRun` classpath
   the vendored 1.5.6 wins (entry 3 of 222, versus 168/169 for the 1.6.2 pair). Confirm in
   the running app if you want belt and braces: print
   `javax.mail.Session.class.protectionDomain.codeSource.location` from the Grails console.
2. **Repeat after deleting.** *Expected:* `javax.mail-api-1.6.2.jar` — the **api** jar, not
   the impl. That is correct and not a defect: `javax.mail.*` comes from the api jar,
   `com.sun.mail.*` and the provider registry from `javax.mail-1.6.2.jar`, both at 1.6.2.
   The probe confirmed `getTransport("smtp")` still resolves to
   `com.sun.mail.smtp.SMTPTransport`.
3. **Check the deployed WAR, not just `bootRun`.** All three jars ship in `WEB-INF/lib`
   and the servlet spec leaves that directory's ordering to the container, so production
   may already be on 1.6.2 while dev runs 1.5.6. Print the same code-source location on
   **staging** before and after. If staging already reports 1.6.2, this branch is a no-op
   there and a real upgrade only for developers.
4. **Password-reset email** — `UserController.sendResetPasswordEmail`: use the forgot-
   password flow for a test user. *Expected:* the `[PEGR] Reset password` email arrives
   with a working reset link, and following the link reaches the reset form.
5. **Account-creation email** — `UserService`, the `[PEGR] Account Information` mail: create
   a new user as an admin. *Expected:* the email arrives with a working registration link.
   Note this path wraps the send in `catch (Exception e)` and rethrows
   `UserException("Error sending the email!")`, so an SMTP misconfiguration shows as that
   friendly message — but a classpath break raises `NoClassDefFoundError`, which is an
   `Error`, not an `Exception`, and so escapes that catch as a raw 500. **The two failure
   modes look different; do not accept "Error sending the email!" as evidence the classpath
   is fine.**

## Regression risk

- **Low overall.** One deleted file, nothing added, no source change, no config change.
- **The real risk is the inverse of the usual one, and it is confirmed.** 1.5.6 *is*
  winning the classpath, so this branch moves mail from 1.5.6 to 1.6.2 — a two-year version
  jump (2016 → 2018) arriving as a *deletion*, exactly the change that gets reviewed as
  trivial. Review it as an upgrade. This is a maintenance range with no intentional API
  break, but the SMTP/TLS defaults moved over that span; if step 4 or 5 fails against a
  host that worked before, suspect the TLS handshake first.
- **Split packages across two jars.** After the deletion `javax.mail.*` resolves from the
  api jar and `com.sun.mail.*` from the impl jar. Verified working for SMTP, but it is a
  more fragile arrangement than one self-contained jar — a future dependency change that
  drops or reorders either half breaks mail in a way that looks unrelated. Worth revisiting
  when the `mail` plugin is rebuilt for Jakarta in Phase 1.
- **`javax.activation` on Java 21.** JAF was removed from the JDK in Java 11, and the
  vendored jar does not bundle it. The plugin supplies `javax.activation:activation:1.1`
  transitively, which is why mail works today. Deleting the mail jar does not touch that,
  but if attachments or MIME typing misbehave, this is where to look.
- **Not touched by this branch:** CSV import, barcode rendering, the database driver,
  reports, the API, Quartz jobs, Liquibase state. No changesets are added.

## Rollback

`git revert` the single commit, or restore the jar from git history. No schema, no config,
no deployment-side change — a code-only revert is complete, and there is no point of no
return.
