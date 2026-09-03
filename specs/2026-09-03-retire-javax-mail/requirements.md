# Retire `libs/javax.mail.jar` — Requirements

**Roadmap item:** P0 → Phase 0, "Replace `libs/` jars with Maven dependencies" —
the `javax.mail.jar` row of the bundled-jars table
**Branch:** `feature/retire-javax-mail`, off `grails7`
**Started:** 2026-09-03

## Context

`pegr/libs/` holds five jars dropped in on 2020-05-26, pulled onto the classpath by a
blanket `implementation fileTree(dir: 'libs', include: '*.jar')`. This is the first of
four branches retiring them, one library per branch.

**The dependency tree changes what this branch is.** Running
`./gradlew dependencies --configuration runtimeClasspath` on `master` shows the `mail`
plugin 3.0.0 already resolving JavaMail from Maven:

```
+--- org.grails.plugins:mail:3.0.0
|    +--- javax.mail:javax.mail-api:1.6.2
|    +--- com.sun.mail:javax.mail:1.6.2
|    |    \--- javax.activation:activation:1.1
```

The vendored jar is `com.sun.mail:javax.mail` **1.5.6** (from its manifest:
`Bundle-SymbolicName: com.sun.mail.javax.mail`, `Implementation-Version: 1.5.6`, built
Aug 2016). It is the full implementation jar, not just the API — 202 `com/sun/mail/*`
entries.

So the classpath currently carries **two copies of JavaMail at different versions**, and
which one wins is decided by classpath ordering. Nothing in `grails-app/` or `src/`
imports `javax.mail` directly, so no code depends on either.

This branch therefore adds **no dependency**. It deletes a duplicate.

**Group 1 finding (2026-09-03): it is not a redundant duplicate — the vendored 1.5.6 is
the copy that wins.** The `application` plugin's generated start script gives the resolved
classpath in order: `javax.mail.jar` is entry **3 of 222**, `javax.mail-api-1.6.2.jar` is
**168**, `javax.mail-1.6.2.jar` is **169**. Probed on the real `installDist` classpath:

| | `javax.mail.Session` loads from | `getTransport("smtp")` |
|---|---|---|
| Today | `javax.mail.jar` (**1.5.6**) | `com.sun.mail.smtp.SMTPTransport` |
| After deletion | `javax.mail-api-1.6.2.jar` | `com.sun.mail.smtp.SMTPTransport` |

So this branch is **an effective JavaMail 1.5.6 → 1.6.2 upgrade arriving as a file
deletion**, not a no-op. It must be reviewed and released as a version change.

The provider registry survives: `javax.mail-api-1.6.2.jar` carries `javax/mail/*` but no
`com/sun/mail/*` and no `META-INF/javamail.default.providers`. After deletion the API
classes come from the api jar and the implementation plus provider registry from
`javax.mail-1.6.2.jar` — both at 1.6.2, and `getResources` finds the registry classpath-wide.
This was verified by running the probe, not inferred.

## In scope

- `git rm pegr/libs/javax.mail.jar`.

That is the entire change.

## Out of scope

- **Adding a Maven coordinate for JavaMail.** Unnecessary — the `mail` plugin already
  brings 1.6.2 transitively. Declaring it directly would pin a version we do not need to
  own and would have to be revisited in Phase 1 anyway.
- **Moving to the `jakarta.mail` package.** The `mail` plugin 3.0.0 compiles against
  `javax.mail`. The package rename is Phase 1 work, gated on a plugin build that expects
  it. This branch does not bring it closer or push it further away.
- **Removing `fileTree(dir: 'libs')`.** Three other jars still live there. The line comes
  out on `feature/retire-opencsv`, which deletes the last one.
- **Deleting `grails-wrapper.jar`** — that rides along on `feature/retire-zxing`.
- Any mail configuration change. `grails.mail.*` keys live in the external
  `pegr-config.properties` and are untouched.

## Decisions

| Decision | Choice | Rationale |
|---|---|---|
| Add a Maven dep? | **No** | Already resolved transitively at 1.6.2 by the `mail` plugin. Verified in the runtime classpath tree, not assumed. |
| Delete or pin to 1.5.6? | Delete | 1.5.6 is older than what the plugin already provides and predates it by six years. Confirmed in group 1 that it is not merely masked — it is the copy actually in use, so deleting it is the upgrade. |
| Branch base | `grails7` | Per the agreed structure: `grails7` is the integration line, features merge into it. |
| Splitting per library | One jar per branch | Each jar has a different blast radius; bundling them was the reason the first attempt at this spec was too large. |

## Open questions

- ~~**Which copy is winning today?**~~ **Answered 2026-09-03: 1.5.6, the vendored jar.**
  See the context section. The deletion is a real 1.5.6 → 1.6.2 upgrade.
- **Do dev and production already disagree?** The measurement above used the flat
  `application`-plugin classpath, which is what `bootRun` uses. Production deploys the
  **WAR**, and the servlet spec does not define `WEB-INF/lib` ordering — the container
  decides. All three jars ship in `WEB-INF/lib`. If Tomcat orders that directory by
  filename, `javax.mail-1.6.2.jar` sorts ahead of `javax.mail.jar`, which would mean
  production is *already* on 1.6.2 while dev runs 1.5.6. Unverified. It changes who is
  affected by this branch, so confirm against staging before release.
- **Was the vendoring deliberate?** The jars share a date with the abandoned jQuery/
  Bootstrap bundles (2020-05-26), which the roadmap reads as a stalled upgrade attempt.
  If anyone recalls a reason JavaMail was vendored over the plugin's own copy, that reason
  may still apply. Cheap to ask; cheap to revert if it turns out to matter.

## Constraints

From `specs/tech-stack.md`:

- No schema changes, no Liquibase changesets on this branch.
- `./gradlew clean build` from `pegr/` is the honest build check (`minifyJs`/`minifyCss`
  are on).
- The dev server does not hot-reload controllers. One of the two mail call sites is in
  `UserController` — restart `bootRun` before QA.
