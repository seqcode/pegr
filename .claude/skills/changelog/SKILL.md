---
name: changelog
description: Maintain CHANGELOG.md in the project root — a dated record of what changed in PEGR, written for people rather than a mirror of git log. Use before merging a branch, when the user says "update the changelog", "changelog this", "log these changes", or when CHANGELOG.md is missing and needs bootstrapping from git history.
---

# Changelog

`CHANGELOG.md` lives in the project root. One `## YYYY-MM-DD` heading per date, newest
first, bullets underneath. It records **what changed in the project**, not what commits
were made.

The usual trigger is "we are about to merge this branch." The unit of work is the branch,
and the entries land under today's date.

## Order of operations

### 1. Get today's date

```bash
date +%F
```

Never type a date from memory. Use the *merge* date, not the date the work started — a
reader wants to know when it landed.

### 2. Does `CHANGELOG.md` exist?

- **No** → go to [Bootstrapping](#bootstrapping-from-git-history), then come back.
- **Yes** → read the top of it. The newest heading is what has already been recorded, and
  tells you where to start.

### 3. Work out what is actually new

```bash
git branch --show-current
git log --date=short --format='%ad %h %s' <base>..HEAD    # base: grails7, else master
git diff --stat <base>...HEAD
```

Read **both**. The log tells you what the author thought they did; the diff tells you what
changed. When they disagree, the diff wins — a commit message can describe an intention
that got reverted three commits later.

If a date heading for today already exists, add to it rather than opening a second one.

### 4. Write the entries

One bullet per change a reader would care about. Not one bullet per commit.

A good entry answers "what is different now?" in a sentence. Lead with the change, and add
the consequence only when it is not obvious:

```markdown
- `./gradlew test` now runs the test suite. It previously reported success while
  executing zero tests, so the one existing spec had never run.
- Gradle 7.6.3 → 8.5. Required because 7.6.3 cannot compile a build script on Java 21,
  which made `build.gradle` uneditable.
- Retired the vendored `libs/javax.mail.jar`. Effectively upgrades JavaMail 1.5.6 → 1.6.2,
  since the vendored copy was winning the classpath.
```

Not this:

```markdown
- Add useJUnitPlatform()          <- restates the diff, not the effect
- Fix tests                       <- says nothing
- Spec review: fix factual errors <- process noise, not a project change
- Merge branch 'grails7'          <- never log a merge commit
```

**Include:** behaviour changes, dependency and version moves, build and tooling changes
that affect how someone works, schema and migration changes, API changes, anything that
alters how the app is deployed or configured.

**Leave out:** merge commits, commits that only fix an earlier commit in the same branch
(log the net result), spec and planning documents, changes to the changelog itself,
formatting and typo fixes. If a branch's entire content is spec documents, it gets no
entry — say so rather than inventing one.

**Flag deployment-side consequences explicitly.** If a change requires someone to edit a
config file, run a migration, or change a URL, that belongs in the changelog in bold —
it is the single thing most likely to be missed at release time.

### 5. Check the entries against the diff

Re-read `git diff --stat <base>...HEAD` against what you wrote. Every non-trivial file in
the diff should be explained by some bullet, or be deliberately excluded per the rules
above. A file changed and not accounted for is either a missing entry or a change nobody
intended.

## Format

```markdown
# Changelog

What changed in PEGR, newest first. Dates are when the change landed on the
integration branch.

## 2026-09-09

- Entry.
- Entry. **Deployment: requires X.**

## 2026-09-03

- Entry.
```

- `## YYYY-MM-DD`, ISO, always. No "Sept 9" or "09/09/26".
- Newest date at the top. A reader opening the file wants the most recent state first.
- No version numbers unless the project starts tagging releases. PEGR ships from a
  branch, so dates are the honest unit.
- Plain bullets. Do not add Added/Changed/Fixed subheadings unless the volume on a single
  date genuinely needs them — at PEGR's rate it will not.

## Bootstrapping from git history

Only when `CHANGELOG.md` does not exist.

**Do not walk the whole history.** As of 2026-09 this repo has ~1288 commits across **579
distinct dates** reaching back to 2015-11-25. A heading per date produces an unreadable
document that no one will maintain, and the early history is not reconstructible into
honest entries at this remove.

Instead:

1. Propose a cutoff and **confirm it with the user before writing anything**. Sensible
   candidates: the current Grails 6 platform baseline, the last year, or the start of the
   Grails 8 upgrade work. State how many dates each choice produces:
   ```bash
   git log --since=<date> --date=short --format='%ad' | sort -u | wc -l
   ```
2. For dates before the cutoff, write a single summarising entry rather than headings —
   e.g. `## Before 2026-01-01` with a few bullets naming the major milestones. Get those
   from `specs/roadmap.md`'s "Shipped" section, which is already curated, rather than
   re-deriving them from commit subjects.
3. For dates after the cutoff, apply the step 4 rules per date. Collapse a date's commits
   into the changes they add up to. Many dates will produce one bullet; some will produce
   none, and a date with nothing worth recording gets no heading.

Bootstrapping is a judgement-heavy, one-time job. Prefer fewer, truer entries over
completeness.

## Rules

- **The changelog is not a git log.** If an entry could be produced by `git log --oneline`
  and nothing else, it is not worth writing.
- **Use `date +%F`.** Never a remembered date. This project has already shipped one
  documentation error from trusting filesystem mtimes over git.
- **Write for someone who did not do the work** — a teammate returning in six months, or
  whoever runs the next deploy.
- **Record what landed, not what is planned.** Roadmap intentions belong in
  `specs/roadmap.md`. If a branch is merged but the feature is behind a flag or unverified
  in production, say so in the entry.
- **Do not mark something as landed on a date it was not merged.** If the branch is not
  merged yet, the entry is provisional — write it, and correct the date if the merge slips.
- **Never edit an entry under a past date to make it read better.** Past entries are a
  record. Correct them only when they are factually wrong, and say what changed.
- Keep entries in the changelog and detail in the spec directories. Link to a spec
  (`specs/YYYY-MM-DD-<name>/`) rather than reproducing its reasoning.
