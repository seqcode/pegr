# PEGR - Platform for Epigenomic and Genomic Research

A Grails 6.2.3 web application for logging sample and sequencing experiment metadata, managing data processing workflows, and providing reporting/visualization. Links together people, samples, protocols, sequencers, and bioinformatics computation.

## Tech Stack

- **Framework**: Grails 6.2.3 / Groovy / Spring Boot
- **ORM**: GORM with Hibernate 5 (`grails-app/domain/`)
- **Security**: Spring Security Core 6.1.2 with role groups; optional SSO via request headers/attributes
- **DB**: MariaDB/MySQL in production, H2 for dev/test; Liquibase migrations (`grails-app/migrations/`)
- **Jobs**: Quartz 2.0.13 (`grails-app/jobs/`)
- **Views**: GSP (Groovy Server Pages) with `g:`, `sec:`, and `asset:` taglibs
- **Testing**: Spock + Geb
- **Build**: Gradle (see `build.gradle`, `gradle.properties`)

## Key Directories

| Path | Purpose |
|------|---------|
| `grails-app/controllers/pegr/` | Request handlers; `*Interceptor.groovy` files handle per-action authorization |
| `grails-app/services/pegr/` | Business logic; all transactional work lives here |
| `grails-app/domain/pegr/` | GORM domain classes (~75); includes many single-field lookup/reference tables |
| `grails-app/views/` | GSP templates; `layouts/main.gsp` wraps most pages |
| `grails-app/conf/application.groovy` | Spring Security URL map, app-wide settings |
| `grails-app/conf/spring/resources.groovy` | SSO filter bean definitions |
| `grails-app/migrations/` | Liquibase changelogs — always add new changesets here for schema changes |
| `grails-app/init/pegr/BootStrap.groovy` | App startup: creates default admin user, configures SSO |

## Build & Run Commands

```bash
./gradlew bootRun                          # Start dev server (localhost:8080/pegr)
./gradlew bootRun -Dspring.profiles.active=prod  # Run with a specific profile
./gradlew test                             # Unit tests (Spock)
./gradlew integrationTest                  # Integration tests (Geb)
./gradlew war                              # Build WAR for deployment
./gradlew clean build                      # Full clean build
```

Default dev admin credentials (created by BootStrap if no admin exists): `labadmin / labadmin`

External config is loaded from `pegr-config.properties` via:
```bash
export SPRING_CONFIG_ADDITIONAL_LOCATION=/path/to/pegr-config.properties
```

## Roles

- `ROLE_ADMIN` — full system access
- `ROLE_MEMBER` — can create/manage projects and samples
- `ROLE_USER` — can only view their own projects (via `ProjectUser` membership)

URL-level access rules are defined in `grails-app/conf/application.groovy` under `grails.plugin.springsecurity.interceptUrlMap`.

## Additional Documentation

- [.claude/docs/architectural_patterns.md](.claude/docs/architectural_patterns.md) — Controller/service/domain conventions, data flow, API patterns, AJAX patterns, security interceptors; check when adding new features or modifying existing ones.
