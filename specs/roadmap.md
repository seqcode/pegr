# Roadmap

**Current priority: upgrade Grails 6.2.3 → Grails 8.**

Status: "Shipped" is derived from git history and is factual. The upgrade plan below is
grounded in this codebase's actual dependencies plus the published Apache Grails upgrade
guides; sequencing and dates are a proposal to be confirmed by the team.

Last reviewed: 2026-09-03

---

## P0 — Grails 6 → Grails 8

### The shape of the problem

This is a **two-hop migration**. Land on the latest stable 7.x first, run it in production, then
move to 8.0.0 once it reaches GA.

| Hop | What changes | Difficulty |
|-----|--------------|------------|
| **6 → 7** | `javax.*` → `jakarta.*`, Spring Boot 2 → 3.4, Spring Framework 6, Groovy 3 → 4, Gradle 7 → 8(done in Phase 0), SiteMesh 2 → 3 | **This is where nearly all the work is** |
| **7 → 8** | Spring Boot 3.5 → 4.1, Spring Framework 7, Groovy 4 → 5, Gradle 8 → 9.6, Jackson 2 → 3, Spring Security 7.1 | Smaller, but blocked on release timing |

 **Every Grails plugin must be rebuilt for 7+.** Plugins compiled against `javax.*`
   fail immediately at runtime — the JVM treats `javax.servlet` and `jakarta.servlet` as
   unrelated types. PEGR depends on eight plugins; their availability is the critical path,
   not our own code.

### Dependency audit

Each row needs a compatible-version answer before the upgrade can start. Current values
from `pegr/build.gradle`.

| Dependency | Current | Concern |
|------------|---------|---------|
| `database-migration` plugin | 3.1.0.RC1 | On a release candidate; Liquibase pinned at **3.6.3** (very old). Needs a modern Liquibase + plugin. Highest-risk item — see below. |
| `spring-security-core` | 6.1.2 | Grails 8 moves to Spring Security 7.1.x; a filter-ordering constant was removed. Our `interceptUrlMap` and SSO filter beans both touch this. |
| `quartz` plugin | 2.0.13 (+ `quartz-scheduler` 2.2.1) | Plugin must be rebuilt for Jakarta. `quartz-scheduler` 2.2.1 is from 2014. |
| `mail` plugin | 3.0.0 | Needs `jakarta.mail`. See bundled-jars below. |
| `hibernate5` plugin / `hibernate-core` | 8.1.0 / pinned 5.4.0.Final | Grails 8 keeps Hibernate 5 as default and adds Hibernate 7 support. Drop the manual `5.4.0.Final` pin and let the BOM decide. |
| `asset-pipeline` | 4.5.1 | Needs a version built for Gradle 8/9 and Jakarta. Affects all 318 GSPs' asset tags. |
| jQuery (vendored) | **1.9.1** (2013) | Unaffected by the Grails upgrade, but 12 years old and CVE-exposed. See the front-end section. |
| Bootstrap (vendored) | **3.3.6** | Blocks jQuery 3; needs 3.4.1 at minimum. |
| `excel-export` | 2.1 | Compatibility unknown — **verify early**; used for report exports. |
| `external-config` | 2.0.0 | Compatibility unknown; without it `pegr-config.properties` loading breaks in every environment. |
| `mysql-connector-java` | 5.1.29 | Long past end of life. Replace with MariaDB Connector/J — this was already on the roadmap and is now a hard blocker. |
| `javax.annotation-api` | 1.3.2 | → `jakarta.annotation-api`. |
| Gradle wrapper | 7.6.3 | → 8.x for Grails 7, → 9.6 for Grails 8, but `clean build` already warns of features incompatible with Gradle 9.0; source unidentified, not in our own scripts. |
| Groovy | 3.0.21 | → 4 (Grails 7) → 5.0.x (Grails 8). |
| Spock / Geb / Selenium | Spock (BOM), Geb, Selenium 4.19.1 | Spock moves to `2.4-groovy-5.0` under Grails 8; Geb needs a matching build. |

### Bundled jars in `libs/` — retire these first

`pegr/libs/` contains five jars added in `daefca6d` (2019-07-24) and pulled in via
`fileTree(dir: 'libs')`:

- `javax.mail.jar` — **must become `jakarta.mail`**. Direct Jakarta blocker.
- `opencsv-3.7.jar` — used by ~10 controllers/services (imports, barcode, index, qfile).
- `core-3.2.1.jar` + `javase-3.2.1.jar` (ZXing barcode) — used by `BarcodeService`.
- `grails-wrapper.jar` — obsolete, delete.

All of these should become declared Maven dependencies at current versions **before** the
framework upgrade begins. It is independent work, testable on Grails 6 today, and it
removes four unknowns from the migration.

### Browser-side libraries — do this first, and keep it scoped

Independent of Grails, but it belongs in Phase 0 because it is a security fix on its own
clock and because doing it inside the upgrade branch gives every UI regression two suspects.

**What is actually live.** `application.js` resolves `//= require jquery` to `jquery.js`,
which is **jQuery 1.9.1 (2013)** — not the `jquery-3.3.1.min.js` sitting beside it. Same
for Bootstrap: the manifest loads `bootstrap.js` = **Bootstrap 3.3.6**, while
`bootstrap.bundle.min.js` (4.1.3) is never referenced.

| File | Version | Status |
|------|---------|--------|
| `jquery.js` | 1.9.1 | **live** — manifest + 4 views |
| `jquery-3.3.1.min.js` | 3.3.1 | dead, unreferenced |
| `bootstrap.js` | 3.3.6 | **live** |
| `bootstrap.bundle.min.js` | 4.1.3 | dead, unreferenced |
| `jquery.validate.js` | 1.15.0 | live |
| `jquery.dataTables.min.js` | 1.10.13 | live |
| `select2.js` | 4.0.2 | live |

The dead files came in with the `libs/` jars in `daefca6d` (2019-07-24); the live
`jquery.js` and `bootstrap.js` date from 2015. **An upgrade attempt was abandoned without
flipping the manifest.** Meanwhile ~1.5 MB of unused assets ship in every build.

**Why this is not decoupled from Bootstrap.** jQuery 3 support landed in Bootstrap 3.4.0,
so 3.3.6 cannot stay. The minimum unit of work is jQuery + Bootstrap + the plugin set
together.

**Why it must not become a redesign.** Bootstrap 4 is already vendored and tempting, but
BS3 idioms are load-bearing across the 318 GSPs — `btn-default` (90 files), `glyphicon`
(53), `data-toggle` (36), `modal-dialog` (13) — and **107 GSPs carry inline `$(...)`**
with no Geb coverage. BS4 drops glyphicons and renames `btn-default`; that is a UI rewrite.

**Scope for Phase 0:**
- jQuery 1.9.1 → **3.7.x**, running `jquery-migrate` in warning mode first to surface
  breakage across the 107 inline blocks.
- Bootstrap 3.3.6 → **3.4.1** — a compatibility release, same markup and classes.
- Bump `jquery.validate`, DataTables, and select2 to jQuery-3-compatible versions.
- Delete the dead 3.3.1 and BS4 bundles.

Expect real work in the inline JS: 1.x → 3.x removes `.size()`, `.andSelf()`, `$.browser`,
and the `.success()`/`.error()` ajax callbacks, and changes `.attr()`/`.prop()` semantics.
Security note: jQuery <3.5.0 is exposed to CVE-2020-11022 / CVE-2020-11023 (XSS), and
1.x additionally to CVE-2015-9251.

**Bootstrap 3 → 4/5 is explicitly deferred** to front-end modernization, after Grails.

### GORM semantic change — needs a real audit

Grails 8 changes a default: **unconstrained persistent properties become nullable by
default** rather than required. With ~75 domain classes, many of them thin lookup tables,
this can silently relax validation across the model and let invalid records save.

This is not a compile error — it will pass the build and show up as bad data. Plan an
explicit pass over `grails-app/domain/pegr/` to make nullability constraints explicit
before cutting over. Also note the removed Hibernate annotations (`@Where`, `@Proxy`,
`@LazyCollection`) and the `save()` → `persist()` Session API change.

### Migrations

The Liquibase baseline was reset to database-as-truth on 2026-07-15, so the changelog is
currently clean — good timing for this. Constraints that must hold through the upgrade:

- Production applies `changelogSync`, never `update`.
- The Liquibase 3.6.3 → modern jump has its own changelog-format and checksum implications;
  validate against a **restored copy of production**, not a dev H2 database.
- Do not combine schema changes with the framework upgrade in the same release.

### Phases

**Phase 0 — De-risk on Grails 6** *(no framework change; ship incrementally)*
- **Gradle 7.6.3 → 8.5 — do this first; everything else is blocked on it.** Gradle 7.6.3
  bundles Groovy 3.0.13, which cannot compile a build script on Java 21
  (`Unsupported class file major version 65`). The build only worked because a compiled
  script cached under an older JDK was still valid, so *any* edit to `build.gradle` broke
  it — and every remaining Phase 0 item needs one. Gradle added Java 21 support in 8.5.
  Done on `feature/enable-junit-platform`.
- Enable the JUnit Platform. `./gradlew test` ran **zero tests and reported success**:
  Spock 2.3 discovers specs through the JUnit Platform and `useJUnitPlatform()` was never
  set, so the one existing spec had never executed. Same branch as the Gradle move, which
  is what unblocked it.
- Replace `libs/` jars with Maven dependencies; delete `grails-wrapper.jar`.
- Swap `mysql-connector-java` 5.1.29 for MariaDB Connector/J.
- Raise test coverage on `ApiController` + the services behind it, and on the report
  paths. This is the regression net for everything that follows — without it the upgrade
  is unverifiable.
- Confirm plugin availability for 7.x: `excel-export` and `external-config` first, since
  no substitute is obvious for either.
- Browser-side library bump (jQuery 3.7.x + Bootstrap 3.4.1 + plugins) per the section
  above. Safe to run on a **parallel branch** — it touches `assets/` and GSPs, not the
  build's framework wiring — so it need not block the Grails work if Phase 0 slips. The
  requirement is that it not land *inside* the Grails branch.
- **Exit:** Gradle 8.5 building on Java 21, a test suite that actually executes specs
  (a non-zero test count, not merely `BUILD SUCCESSFUL`), clean `libs/`, current DB driver,
  written go/no-go on every plugin, jQuery 3 + Bootstrap 3.4.1 live with the dead bundles
  removed.

**Phase 1 — Grails 6 → 7 (latest stable 7.x)**
- Branch. Groovy 3 → 4, Spring Boot 2 → 3.4. (Gradle 8.5 already landed in Phase 0.)
- Jakarta namespace migration (consider the Nebula `jakartaeeMigration` Gradle plugin for
  any dependency that resists).
- Upgrade each plugin to its 7.x build; SiteMesh 2 → 3 affects GSP layouts.
- Verify: login + SSO, all Quartz jobs, mail, the full API surface, Excel export,
  file upload/`filesroot`, and report rendering.
- **Exit:** `integrationTest` green, manual QA on a production-data restore, deployed to
  staging.

**Phase 2 — Run 7.x in production**
- Deploy, watch, fix. Do not start Phase 3 from an unstable base.

**Phase 3 — Grails 7 → 8** *(gated on 8.0.0 GA)*
- Gradle → 9.6, Groovy → 5, Spring Boot → 4.1, Spring Security → 7.1, Jackson 2 → 3.
- Domain-class nullability audit (above).
- Relocated packages: `org.springframework.orm.hibernate5` → `org.grails.orm.hibernate.support.hibernate5/7`;
  starter renames (`spring-boot-starter-web` → `spring-boot-starter-webmvc`).
- **Exit:** same bar as Phase 1.

---

## P1 — Deferred until the upgrade lands

Previously roadmapped; paused so as not to conflict with the migration branch. Two former
items (MySQL driver, dependency currency) were absorbed into Phase 0, and API/service test
coverage was promoted into Phase 0 as the upgrade's safety net.

1. **API surface consolidation.** `ApiController` is ~150 methods with logic largely
   inline; extract into services (samples, runs, analyses, datasets) behind one documented
   contract. Best done *after* the upgrade — but note that thinner controllers would make
   the upgrade easier, so revisit if Phase 1 stalls.
2. **API authentication.** Scoped tokens for pipeline clients so compute nodes don't hold
   user logins.
3. **Sample status end-to-end.** Consistent status across sample, replicate, run, and
   report views, with transitions defined in one place.
4. **Reporting performance.** Profile the object graphs assembled by project/run reports;
   add projections and indexes.
5. **Contributor docs.** Document the "new changeset, never ad-hoc prod DDL" flow in
   `CONTRIBUTING.md`.

## Later

6. **Front-end modernization.** The Bootstrap 3 → 4/5 migration deferred out of Phase 0:
   ~155 GSPs use BS3-only classes and 107 carry inline jQuery. Needs Geb coverage on the
   interactive pages first. If revisited, pick one interactive layer rather than growing
   per-page JavaScript.
7. **Multi-lab tenancy.** Would mean scoping projects, protocols, and inventory by
   organization.
8. **Data export / archival.** One-shot export of a project's full provenance chain.
9. **Schema simplification.** ~75 domain classes, many single-field lookup tables; a
   consolidation pass would cut a lot of boilerplate. Pairs naturally with the Phase 3
   nullability audit.

---

## Shipped (recent, from `master`)

- **Sample status tracking** — status on samples, reports, sequence-run status colors
  (`#355`, sample status LITE, batch edit).
- **Pipeline API growth** — query samples by index and genome (`#364`), delete dataset
  link, bioinformatics API help page.
- **GEO submission support** — GEO accession capture (`#356`).
- **Reference data admin** — species sorting on genome admin (`#361`), ligation module,
  user deletion.
- **Documentation** — database schema diagrams under `docs/schema/`.
- **Platform** — Grails 6.2.3 / Java 21; Liquibase baseline reset to database-as-truth
  (2026-07-15).

**Sources for the version claims above:**
[Grails 8 feature set](https://github.com/apache/grails-core/issues/13757) ·
[Apache Grails releases](https://github.com/apache/grails-core/releases) ·
[Grails 8 upgrade guide](https://grails.apache.org/docs/latest/guide/upgrading.html) ·
[Grails 7.0.0-M1 announcement](https://grails.apache.org/blog/2024-12-23-grails-7-m1.html)
