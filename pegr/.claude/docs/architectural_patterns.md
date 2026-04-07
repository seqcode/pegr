# Architectural Patterns

Patterns observed consistently across the PEGR codebase.

## Controller Conventions

**Service injection** — services are declared as untyped properties; Grails autowires them:
```
// grails-app/controllers/pegr/ProjectController.groovy:8-11
def projectService
def springSecurityService
def utilityService
```

**AJAX actions** use an `Ajax` suffix and return rendered templates or JSON, not full pages:
```
// grails-app/controllers/pegr/ProjectController.groovy ~line 300+
def addCheckedProjectAjax(...) { render template: "userTable", model: [...] }
def saveNotesAjax(...)         { render text: result as JSON, contentType: "text/json" }
```

**Standard action flow** — validate inputs → delegate to service → set flash message → redirect/render:
- `grails-app/controllers/pegr/ProjectController.groovy:43-68` (create action)
- `grails-app/controllers/pegr/SampleController.groovy:41-48` (edit action)

**HTTP method guarding** in `ApiController` uses `static allowedMethods`:
- `grails-app/controllers/pegr/ApiController.groovy:22-33`

**404/error views** are rendered directly: `render(view: "/404")` — no exception thrown.

## Interceptors (Action-Level Authorization)

Every sensitive controller has a paired `*Interceptor.groovy` that runs `boolean before()`. It:
1. Checks `actionName` to decide whether to enforce
2. Calls a service auth method (e.g., `projectService.projectEditAuth(project)`) or queries `ProjectUser` directly
3. Returns `false` and renders `login/denied` on failure; `true` to proceed

Key examples:
- `grails-app/controllers/pegr/ProjectInterceptor.groovy:1-82`
- `grails-app/controllers/pegr/SampleInterceptor.groovy:1-48`

## Service Conventions

**Transactional boundaries** — `@Transactional` on individual methods (not the class), so non-mutating queries run without a transaction:
- `grails-app/services/pegr/ProjectService.groovy:16,31,47,63`

**Custom exceptions per service** — each service defines its own `RuntimeException` subclass at the top of the file:
```
class ProjectException extends RuntimeException { String message }
class SampleException  extends RuntimeException { String message }
```
Controllers catch these and set `request.message` or `flash.message`.

**Service dependency injection** — same untyped property pattern as controllers:
```
// grails-app/services/pegr/ProjectService.groovy:11-13
def springSecurityService
def utilityService
def dataSource
```

## Domain Model Conventions

**Many-to-many relationships** use explicit join-table domain classes with additional data (never Grails `hasMany` for M:M):
- `ProjectUser` (project + user + projectRole) — `grails-app/domain/pegr/ProjectUser.groovy`
- `ProjectSamples`, `ProjectFunding`, `SampleTreatments` follow the same pattern

**Computed association getters** on domain classes query via `where` or `findAllBy`:
```
// grails-app/domain/pegr/Project.groovy
List getSamples() { ProjectSamples.where{ project == this }.collect{ it.sample } }
List getFundings() { ProjectFunding.findAllByProject(this).collect{ it.funding } }
```

**Constraints and mapping block conventions:**
- `dynamicUpdate: true` on frequently-updated classes (only writes changed columns)
- `sqlType: "longtext"` for large text fields (notes, descriptions)
- Composite unique via `unique: "otherField"` in constraints: `project unique: "user"` in `ProjectUser`
- `grails-app/domain/pegr/Project.groovy:25-40`

**Lookup/reference tables** are tiny single-field domain classes (e.g., `AbHost`, `IgType`, `Sex`, `Tissue`). They are populated via Liquibase and referenced as associations rather than enums.

## GORM Query Patterns

**Criteria builder** for complex/optional filters (avoids HQL string concatenation):
```
// grails-app/services/pegr/SampleService.groovy:20-113
def c = Sample.createCriteria()
def results = c.list(max: n, offset: o) {
    and {
        if (query.speciesId) { cellSource { strain { species { eq "id", query.speciesId } } } }
    }
}
```

**Dynamic finders** for simple lookups: `User.findByEmailAndApiKey(email, key)`, `ProjectUser.findByProjectAndUser(p, u)`.

**`Domain.get(id)`** for PK lookups; always null-check the result before use.

## REST API Patterns (`ApiController`)

- Authentication: API key passed as URL parameter, validated via `User.findByEmailAndApiKey(email, apiKey)` — returns HTTP 401 JSON on failure
- Request bodies bound to `Validateable` command objects (defined at bottom of `ApiController.groovy:832-915`)
- Standardized response: `render text: [response_code: int, message: String] as JSON, contentType: "text/json", status: code`
- Async processing: save data, then trigger Quartz job: `ProcessAnalysisJob.triggerNow([id: analysis.id])`
- `grails-app/controllers/pegr/ApiController.groovy:43-77`

## GSP View Patterns

**Layout hierarchy**: `base.gsp` → `main.gsp` (adds navbar + auth guards) → individual view.
Views declare their layout with `<meta name="layout" content="main"/>`.

**Conditional rendering** uses model-passed auth flags, not inline role checks:
```gsp
<g:if test="${projectEditAuth}">...</g:if>
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">...</sec:ifAnyGranted>
```

**AJAX partial updates** — controller renders a template fragment; JS replaces a DOM element:
```javascript
$.ajax({ url: "/pegr/sample/fetchDataForSampleAjax/${sample.id}",
         success: function(r) { $("#details").html(r) } })
```

**Flash messages** are displayed at the top of views via a standard block:
```gsp
<g:if test="${flash.message}"><div class="message">${flash.message}</div></g:if>
```

## Security / Authentication

**Spring Security roles** (defined in `application.groovy:interceptUrlMap`):
- URL-level rules are in `grails-app/conf/application.groovy:41-100`
- Action-level rules live in `*Interceptor.groovy` files (see above)
- `springSecurityService.currentUser` is the standard way to get the authenticated user in controllers and services

**SSO** is configured at startup in `BootStrap.groovy` based on `sso.type` config property (`Attribute` or `Header`). Filter beans are defined in `grails-app/conf/spring/resources.groovy`.

## Database Migrations

All schema changes must be added as new Liquibase changesets in `grails-app/migrations/`. Never modify existing changesets. The plugin applies pending changesets automatically on startup.
