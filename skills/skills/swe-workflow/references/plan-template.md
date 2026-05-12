# Plan Template

> Copy this file verbatim to `plans/<task>.md` for every new Full-workflow task. Do not reconstruct from memory. Do not invent your own format. All labeled sections and per-step fields below are required. See [`references/plan-example.md`](plan-example.md) for a filled-in example.

---

```markdown
# Plan: [Task Name]

> Status: DRAFT | IN_PROGRESS | COMPLETED
> Created: YYYY-MM-DD
> Last Updated: YYYY-MM-DD

## Goal
[One sentence describing what "done" looks like.]

## Assumptions
- [What we're taking for granted — verify or make explicit.]

## Open Questions
None.

## Design Decisions
> Decisions about UX, schema shape, component structure, or API contract.
> Each must be confirmed by the user before execution begins.
> Write `None — no design-sensitive changes.` if the task is purely logic/config.

| Decision | Options Considered | Chosen | Confirmed |
|----------|--------------------|--------|-----------|
| [e.g. User list layout] | [Table vs Card grid] | [Table — matches /admin pages] | [yes/no] |

## Validation Commands

| Purpose | Command | Source | Required? |
|---|---|---|---|
| Typecheck |  |  |  |
| Lint |  |  |  |
| Test |  |  |  |
| Build |  |  |  |

## Context & Learnings
### Key Decisions
- [Decision]: [Rationale]
### Gotchas & Warnings
- [Warning]: [What to watch out for]

> Append only. Never delete or rewrite existing entries below — only add new rows/facts as steps complete.
### Working Set
| Path | Role in this task | Evidence |
|------|-------------------|----------|
| [path] | [why this file matters] | [read/rg/test/config check used to verify] |
### Verified Facts
- [Fact] — verified by [tool/read/search/config], [date or step].

## Steps

### Step 1: [Title]
**Status:** PENDING | IN_PROGRESS | COMPLETED | BLOCKED
**Prerequisites:**
- [Step N completed — specific artifact exists, e.g. "`UserService` class created in Step 1"]
- [Files to modify: `src/auth/validate.ts`, `src/auth/__tests__/validate.test.ts`]
- [Design: confirm approach with user before implementing (if design-sensitive)]
**Deliverables:**
- [What this step produces, e.g. "`validateEmail()` accepts an `options` parameter"]
- [After this step: `npm test -- --filter=validate` passes, function signature updated]
**Plan:**
- [ ] `edit` src/auth/validate.ts — add `options: ValidateOptions` parameter to `validateEmail()`
- [ ] `write` src/auth/__tests__/validate.test.ts — new test covering happy path + invalid email
- [ ] `bash` npm test -- --filter=validate — expect 0 failures
**Quality Checklist:**
- [ ] Existing pattern identified:
- [ ] Contract understood:
- [ ] Reuse checked:
- [ ] Risk reviewed:
- [ ] Mitigation recorded:
**Validation Checklist:**
- [ ] `npm run build` exits 0
- [ ] `npm run lint` exits 0
**Test Checklist:**
- [ ] `npm test -- --filter=validate` — all pass (or `N/A` if no test framework)
**Implementation Notes:**
[Fill after implementation — what actually happened, surprises, deviations.]
**Files Changed:**
[List exact paths after implementation.]

<!-- Repeat the full block above for each step. -->

## Implementation Log
| Date | Step | Summary |
|------|------|---------|
```

## Required fields checklist

For `## Open Questions`, the valid content is exactly `None.`. If any known question would be listed there, do not create or finalize the plan; ask the user in chat first. `DRAFT` means plan review/approval after clarification is complete, not unresolved requirements.

Before marking any step `COMPLETED`, confirm the step has all eight fields populated:

- [ ] `Status` — one of `PENDING`, `IN_PROGRESS`, `COMPLETED`, `BLOCKED`
- [ ] `Prerequisites`
- [ ] `Deliverables`
- [ ] `Plan` (action checklist)
- [ ] `Quality Checklist`
- [ ] `Validation Checklist`
- [ ] `Test Checklist` (use `N/A` if no test framework exists)
- [ ] `Files Changed`

The top-level plan file must also have:

- [ ] Header block with `Status`, `Created`, `Last Updated`
- [ ] `Goal`, `Assumptions`, `Open Questions` (exactly `None.`), `Design Decisions` (filled or `None — no design-sensitive changes.`)
- [ ] `Validation Commands` table populated from repo evidence or marked unavailable with reason
- [ ] `Context & Learnings`, including `Working Set` and `Verified Facts`
- [ ] `Implementation Log` table (appended to as steps complete)

## Violations to avoid

- `### Step N — Title [STATUS]` headers with inline status brackets instead of a `**Status:**` field.
- Steps missing any of the eight required fields.
- Horizontal slicing (Step 1 = all types, Step 2 = all logic, Step 3 = all tests). Each step must be a thin vertical slice through the necessary layers — independently verifiable.
- Marking a step `COMPLETED` with known validation or test failures. Either fix them or mark `BLOCKED`.
- Recording guessed paths, APIs, dependencies, or conventions as facts without read/search/tool evidence.
- Creating a new component, utility, hook, or schema pattern without searching for an existing reusable equivalent. Evidence of the search must appear in Verified Facts.
- Making silent design choices (UI layout, schema shape, API contract) without confirming with the user. Surface design decisions in the Design Decisions table.
- Listing unresolved requirement/design questions under `## Open Questions` instead of asking them in chat before planning.
- Adding a step whose purpose is to resolve open questions. Clarification is a pre-plan gate, not implementation work.
- Treating `DRAFT` as permission to leave requirements unresolved. `DRAFT` is only for plan review/approval after clarification is complete.
- Batching multiple steps in a single execution pass.
