---
name: feature-spec
description: Start a new unit of feature work from specs/roadmap.md — pick up the next phase, cut a branch, and scaffold a dated spec directory containing plan.md, requirements.md, and validation.md. Use when the user says "start the next phase", "start a feature spec", "spec out <feature>", "what's next on the roadmap", or is beginning any work that should be planned before code is written.
---

# Feature spec

Turns the next item on `specs/roadmap.md` into a branch plus a three-document spec that
the implementation work then follows.

## Order of operations

Follow these in order. Step 4 has a hard gate.

### 1. Read the context first

Read, in this order:

- `specs/roadmap.md` — find the next unstarted phase or item. Prefer P0 over P1 over
  Later. If the roadmap marks something as in flight, that is the candidate, not the
  next unstarted one.
- `specs/mission.md` — check the feature actually serves the mission and is not a
  non-goal. If it looks like a non-goal, say so before going further.
- `specs/tech-stack.md` — for the versions, commands, and constraints the plan must
  respect (migration policy, dev-server behavior, test commands).

Also skim any existing `specs/YYYY-MM-DD-*/` directories to avoid duplicating work
already specced.

### 2. Confirm the target with the user

State which roadmap item you have selected and why, in one or two sentences. If the next
item is ambiguous, or if two are plausibly next, resolve it in the questions in step 4
rather than guessing.

### 3. Cut the branch

```bash
git fetch origin
git branch -a            # confirm what the base branch actually is
date +%F                 # never guess today's date
```

- **Base branch:** use `dev` if it exists (this repo merges `dev` → `master` via PR);
  fall back to `master` only if there is no `dev`.
- **Branch name:** `feature/<short-kebab-name>` derived from the roadmap item.
- Do not branch from a dirty tree — check `git status` first and stop if it is dirty.

Create the branch but **do not commit anything yet**.

### 4. Ask the user — REQUIRED, before any file is written

**You MUST call `AskUserQuestion` before writing anything to disk.** Do not scaffold the
directory, do not draft the files, do not write and then revise. This gate exists because
the three documents encode decisions that are expensive to unpick once written.

Ask **one** `AskUserQuestion` call with **three questions grouped together**, one per
document:

1. **Scope** (feeds `requirements.md`) — how much of the roadmap item is in this unit of
   work? Offer a genuinely narrow option, the full item, and any natural middle. Include
   what is explicitly *out* of scope in the option descriptions.
2. **Plan shape** (feeds `plan.md`) — how should the work be sequenced? Offer real
   alternatives, e.g. thin end-to-end slice first vs. layer-by-layer (domain → service →
   controller → view) vs. spike-then-build. Base the options on what the feature actually
   needs, not a generic list.
3. **Validation bar** (feeds `validation.md`) — what has to be true to merge? Offer
   options spanning e.g. unit tests only, unit + integration/Geb, or tests plus manual QA
   against a production-data restore. Reflect the risk of the specific feature.

Write options specific to the chosen feature. Generic options waste the gate.

If the roadmap item is ambiguous, use a fourth question to settle which item to target.

### 5. Write the three documents

Create `specs/$(date +%F)-<feature-name>/` and write all three files. Use the answers from
step 4 — the documents must reflect what the user chose, not your original assumptions.

Cross-reference the roadmap item by name so the connection survives.

## Document templates

### `requirements.md` — scope, decisions, context

```markdown
# <Feature name> — Requirements

**Roadmap item:** <which P0/P1/Later item, quoted>
**Branch:** <branch name>
**Started:** <YYYY-MM-DD>

## Context
Why this now. What in the codebase makes it necessary. Link to the roadmap reasoning
rather than restating it.

## In scope
Bulleted, concrete, testable.

## Out of scope
What was deliberately excluded and why — including anything the user narrowed away in
the scope question. This section prevents scope creep later.

## Decisions
| Decision | Choice | Rationale |
|---|---|---|
Record the answers from the questions here, plus any decision made while drafting.

## Open questions
Anything unresolved, with who needs to answer it.

## Constraints
From tech-stack.md — migration policy, plugin versions, anything the implementation
must not violate.
```

### `plan.md` — numbered task groups

```markdown
# <Feature name> — Plan

Task groups run in order unless marked parallel. Each group should be independently
reviewable and leave the build green.

## 1. <Group name>
- [ ] Task
- [ ] Task
**Done when:** <observable condition>

## 2. <Group name>
...
```

Rules for a good plan:
- Number the **groups**, not every task — groups are the review and commit unit.
- Every group carries a **Done when** that is observable, not "works correctly".
- Order so the build stays green at each group boundary.
- Call out which groups can run in parallel and which are hard-blocked.
- If a group cannot be specified without information you do not have, say so in the group
  rather than inventing steps.

### `validation.md` — how to know it worked

```markdown
# <Feature name> — Validation

## Merge criteria
Checklist that must all be true to merge. Derived from the validation-bar answer.

## Automated
| Check | Command | Expected |
|---|---|---|
Use the real commands from tech-stack.md (`./gradlew test`, `./gradlew integrationTest`).

## Manual QA
Numbered steps a human follows, with expected result per step. Name the environment
(dev H2 vs. production-data restore) — some checks are meaningless on an empty database.

## Regression risk
What this change could break that is not obviously related. For this codebase, consider:
the API contract used by external pipelines, Quartz jobs, report rendering, role-based
access, and Liquibase migration state.

## Rollback
How to undo it if it fails in production.
```

## Rules

- **Never write files before the `AskUserQuestion` call.** This is the one hard rule.
- Use `date +%F` for the directory name. Do not type a date from memory.
- Never commit schema changes and feature code in the same group; migrations are
  Liquibase changesets, and production applies `changelogSync`, not `update`.
- Keep the three documents in sync with each other — a task group with no matching
  validation check, or a validation check for something not in scope, is a defect.
- If the roadmap item turns out to be much larger than one unit of work, say so and
  propose splitting it rather than writing a plan you do not believe.
