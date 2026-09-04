# Tech Stack

Versions below are the tested stack. Authoritative sources: `pegr/build.gradle`,
`pegr/gradle.properties`, and the README prerequisites section.

## Runtime

| Layer | Choice | Version |
|-------|--------|---------|
| Language | Groovy | 3.0.21 |
| Framework | Grails (on Spring Boot) | 6.2.3 |
| JVM | Java (Zulu) | 21.0.6 |
| Servlet container | Embedded Tomcat (`spring-boot-starter-tomcat`) | — |
| Build | Gradle 8.5 + `grails-gradle-plugin` 6.2.4 | — |
| Packaging | WAR (`pegr-0.1.war`), also runnable via `java -jar` | — |

## Persistence

| Concern | Choice | Notes |
|---------|--------|-------|
| ORM | GORM / Hibernate 5 | `5.4.0.Final`, `hibernate5` plugin 8.1.0 |
| Production DB | MariaDB | 11.7.2, `utf8` / `utf8_general_ci` |
| Driver | `mysql:mysql-connector-java` | 5.1.29 |
| Dev/test DB | H2 | in-memory |
| Migrations | Liquibase via `database-migration` plugin | Liquibase 3.6.3, plugin 3.1.0.RC1 |
| Caching | `grails-plugin-cache` + hibernate-ehcache | second-level cache |

**Migration policy** — the database is the source of truth. Schema changes go in
`pegr/grails-app/migrations/` as new changesets. Production applies `changelogSync`,
not `update`; changeset ids are not required to be globally unique. Never run ad-hoc
DDL against a deployed database.

## Web layer

| Concern | Choice |
|---------|--------|
| Views | GSP with `g:`, `sec:`, `asset:` taglibs; `layouts/main.gsp` wraps most pages |
| Assets | asset-pipeline 4.5.1 (`minifyJs` / `minifyCss` on) |
| REST API | `ApiController` + `UrlMappings`; JSON in/out for pipeline integration |
| Authorization | Per-controller `*Interceptor.groovy` for action-level checks |

## Security

- **Spring Security Core** plugin 6.1.2.
- Roles: `ROLE_ADMIN`, `ROLE_MEMBER`, `ROLE_USER`; grouped via `RoleGroup`.
- Project-scoped visibility through `ProjectUser` membership.
- URL rules in `pegr/grails-app/conf/application.groovy` (`interceptUrlMap`).
- Optional SSO through request headers/attributes; filter beans in
  `pegr/grails-app/conf/spring/resources.groovy`, configured in `BootStrap.groovy`.

## Background work & integration

| Concern | Choice |
|---------|--------|
| Scheduling | Quartz plugin 2.0.13 — `NgsRepoJob`, `ProcessAnalysisJob` |
| Async | `grails-plugin-async`, `grails-plugin-events` |
| Mail | `grails-plugins:mail` 3.0.0 |
| Spreadsheets | Apache POI 5.2.3 + `excel-export` 2.1 |
| Images | jai-imageio-core 1.4.0 |

## Testing

| Kind | Tooling | Command |
|------|---------|---------|
| Unit | Spock 2.3 (JUnit Platform), `grails-gorm-testing-support`, `grails-web-testing-support` | `./gradlew test` |
| Integration / functional | Geb + Selenium 4.19.1, **configured but unused** — `src/integration-test/` holds only `GebConfig.groovy`, so the task is `NO-SOURCE` | `./gradlew integrationTest` |

## Configuration

External config via the `external-config` plugin 2.0.0, loaded from a
`pegr-config.properties` file outside the repo:

```bash
export SPRING_CONFIG_ADDITIONAL_LOCATION=/path/to/pegr-config.properties
```

Minimum keys: `dataSource.url`, `dataSource.username`, `dataSource.password`,
`sso.url`, `sso.type`, `sso.principle`, `filesroot`. Sample file in `sample_files/`.

## Common commands

```bash
cd pegr
./gradlew bootRun          # dev server at http://localhost:8080/pegr
./gradlew test             # unit tests
./gradlew integrationTest  # Geb functional tests (none exist yet)
./gradlew clean build      # WAR in build/libs/
java -Dgrails.env=prod -jar pegr.war   # production run
```

Dev notes:
- The dev server does **not** hot-reload controllers — restart `bootRun` after editing a
  controller. Services reload fine.
- Bootstrap creates a default `labadmin / labadmin` admin if none exists; change it
  immediately on any real deployment.

## Layout

```
pegr/grails-app/
  domain/pegr/         ~75 GORM classes, incl. many lookup tables
  controllers/pegr/    request handling + *Interceptor authorization
  services/pegr/       all business logic; transactional
  views/               GSP templates
  jobs/pegr/           Quartz jobs
  migrations/          Liquibase changelogs
  conf/                application.groovy, spring/resources.groovy
  init/pegr/           BootStrap.groovy
docs/schema/           database schema diagrams
sample_files/          baseline SQL, sample config, protocol PDFs
"API sample scripts"/  example API clients
```
