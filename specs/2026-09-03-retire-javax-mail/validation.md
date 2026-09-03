# Retire `libs/javax.mail.jar` — Validation

**Bar:** build + boot + QA of the paths this jar touches. Scaled to the change — this
branch deletes one file and adds nothing, so the CSV, barcode, and database paths are not
in question and are not tested here.

## Merge criteria

- [ ] `./gradlew clean build` green from `pegr/`.
- [ ] `./gradlew test` green. *(One spec exists in `pegr/src/test/`; this proves the build
      compiles, not that mail works. Steps 3–4 are the real check.)*
- [ ] `./gradlew bootRun` starts and serves the login page.
- [ ] `git ls-files pegr/libs` no longer lists `javax.mail.jar`.
- [ ] `build.gradle` is **unchanged** — no dependency added, `fileTree` line still present.
- [ ] Both mail paths send successfully (steps 3–4).
- [ ] The PR states whether this was a no-op or an effective 1.5.6 → 1.6.2 upgrade.

## Automated

| Check | Command | Expected |
|---|---|---|
| Compile + assemble | `cd pegr && ./gradlew clean build` | BUILD SUCCESSFUL; `build/libs/pegr-0.1.war` produced |
| Unit tests | `cd pegr && ./gradlew test` | BUILD SUCCESSFUL |
| JavaMail still resolves | `cd pegr && ./gradlew dependencies --configuration runtimeClasspath \| grep -A2 'plugins:mail'` | `com.sun.mail:javax.mail:1.6.2` and `javax.activation:activation:1.1` still present |
| Dependency report unchanged | diff the report against the pre-deletion baseline | No difference — `fileTree` jars never appeared in it |
| Jar gone from the WAR | `unzip -l pegr/build/libs/pegr-0.1.war \| grep -i mail` | Exactly one JavaMail jar, at 1.6.2 |
| Nothing imports it | `grep -rn 'javax\.mail' pegr/grails-app pegr/src` | No matches |

The WAR check is the one that actually proves the point. The module dependency report
cannot see file dependencies, so it will look identical whether or not the deletion
worked — only the assembled artifact shows the duplicate is gone.

## Manual QA

Environment: `./gradlew bootRun` on dev. Mail needs real SMTP settings — `grails.mail.host`,
`grails.mail.port`, `grails.mail.username`, `grails.mail.password` in the external
`pegr-config.properties` (they are in `sample_files/pegr-config.properties` as empty keys).
**A dev config with blank mail settings makes steps 3–4 vacuous**; point them at a real or
catcher SMTP host before starting.

Restart `bootRun` first — `UserController` does not hot-reload.

1. **Establish which copy is live** *(before deleting)* — from the Grails console or a
   scratch action, print
   `javax.mail.Session.class.protectionDomain.codeSource.location`. *Expected:* a path
   naming either the vendored `javax.mail.jar` or the Maven `javax.mail-1.6.2.jar`. Record
   which. This determines whether the branch is a no-op or a version upgrade.
2. **Repeat after deleting.** *Expected:* now unambiguously `javax.mail-1.6.2.jar`.
3. **Password-reset email** — `UserController.sendResetPasswordEmail`: use the forgot-
   password flow for a test user. *Expected:* the `[PEGR] Reset password` email arrives
   with a working reset link, and following the link reaches the reset form.
4. **Account-creation email** — `UserService`, the `[PEGR] Account Information` mail: create
   a new user as an admin. *Expected:* the email arrives with a working registration link.
   Note this path wraps the send in `catch (Exception e)` and rethrows
   `UserException("Error sending the email!")`, so an SMTP misconfiguration shows as that
   friendly message — but a classpath break raises `NoClassDefFoundError`, which is an
   `Error`, not an `Exception`, and so escapes that catch as a raw 500. **The two failure
   modes look different; do not accept "Error sending the email!" as evidence the classpath
   is fine.**

## Regression risk

- **Low overall.** One deleted file, nothing added, no source change, no config change.
- **The real risk is the inverse of the usual one:** if 1.5.6 was winning the classpath,
  this branch silently moves mail from 1.5.6 to 1.6.2. That is a six-year version jump
  arriving as a *deletion*, which is exactly the kind of change that gets reviewed as
  trivial. Step 1 exists to catch it. 1.5.6 → 1.6.2 is a maintenance range with no
  intentional API break, but the SMTP/TLS defaults did move over that span — if step 3 or 4
  fails against a host that worked before, suspect the TLS handshake first.
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
