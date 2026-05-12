---
name: swe-workflow
description: >-
  REQUIRED for every code-related task. Step 1 read this skill's SKILL.md via
  the `read` tool before acting — do not proceed from this description alone.
  Step 2 emit the triage block as the first lines of your reply; it has four
  labels exactly `Workflow mode: Lightweight|Full`, `Reason:`, `Success
  criteria:`, `Plan needed: yes|no`. Step 3 for Full-mode work, write
  `plans/<task>.md` using the template at `references/plan-template.md`
  (required per-step fields `Status`, `Prerequisites`, `Deliverables`, `Plan`,
  `Quality Checklist`, `Validation Checklist`, `Test Checklist`, `Files
  Changed`, plus a top-level `Implementation Log`). Violations include freeform plans, inline
  `### Step N — title [STATUS]` headers, skipping the triage block, and
  deciding a task is "simple" without emitting triage. Applies to reads,
  edits, writes, and bash — triage comes first.
license: MIT
metadata:
  version: "1.9.0"
  author: "Evan Xu"
---

# SWE Workflow

> Every code task starts with workflow triage. Simple tasks stay lightweight; broad or ambiguous tasks use a persisted plan.

## Contract

This block is the minimum every agent must follow. If you skim only this section, you are still compliant.

1. **Triage first.** The first lines of your reply to any code-related task must be:
   ```text
   Workflow mode: Lightweight | Full
   Reason: <one sentence>
   Success criteria:
   - <what done means>
   Plan needed: yes | no
   ```
2. **Full workflow is mandatory for:** repo-wide scans/migrations/cleanup, lint/type/build/test cleanup, broad refactors touching multiple files, deleting/moving/renaming files, backend + frontend changes in one task, API/schema/route/config contract changes, source-of-truth doc updates, tasks touching >3 files, or ambiguous scope. Lightweight work that hits any trigger MUST escalate: stop, declare Full, create a plan, then resume.
3. **Pre-edit gate (Full mode).** Before any `write`/`edit`/`bash` on task target files: a `plans/<task>.md` file must exist, exactly one step must have `**Status:** IN_PROGRESS`, and the edit must map to that step. Only `plans/*.md` files may be written before this gate is satisfied.
4. **Plan file format.** Every `plans/<task>.md` copies [`references/plan-template.md`](references/plan-template.md) verbatim. Every step has all eight fields: `Status`, `Prerequisites`, `Deliverables`, `Plan`, `Quality Checklist`, `Validation Checklist`, `Test Checklist`, `Files Changed`. The plan includes a `Design Decisions` table and a top-level `Implementation Log`.
5. **Clarification gate (Full mode).** If any requirement question is known, ask it in chat before writing or finalizing `plans/<task>.md`. A valid plan has no unresolved questions; `DRAFT` means awaiting plan approval, not awaiting requirement answers, and clarification must not be turned into an implementation step.
6. **One step at a time.** Never batch. Never mark `COMPLETED` with known failures — fix or mark `BLOCKED`.

## Plan Template

Use [`references/plan-template.md`](references/plan-template.md) as the source of truth for `plans/<task>.md`. Copy it verbatim for every new plan. Do not reconstruct the structure from memory; do not invent alternative formats. Full plans capture a task-local Working Set and Verified Facts so agents use evidence instead of guessed paths, APIs, or conventions.

## Reference Loading Rules

Load reference files only when the current phase needs them.

- Planning: read [`references/create-plan.md`](references/create-plan.md) and [`references/plan-template.md`](references/plan-template.md).
- Implementation: read [`references/execute-step.md`](references/execute-step.md) and [`references/code-quality.md`](references/code-quality.md).
- Verification: read [`references/verify-step.md`](references/verify-step.md) and [`references/definition-of-done.md`](references/definition-of-done.md).
- Command discovery: read [`references/command-discovery.md`](references/command-discovery.md) before deciding validation commands.
- Risk review: read [`references/risk-classification.md`](references/risk-classification.md) for API, data, security, performance, or observability risk.
- Project setup: use [`references/project-agents-template.md`](references/project-agents-template.md) only when creating or improving a target repo's `AGENTS.md`.

## Anti-Patterns — Wrong vs Right

**Plan format — missing required fields:**
```
WRONG:  ### Step 1 — Add dependency [COMPLETED]     ← no Status field, no Prerequisites,
        ### Step 2 — Create module [COMPLETED]         no Deliverables, no Quality, no Files Changed

RIGHT:  ### Step 1: Add dependency
        **Status:** COMPLETED
        **Prerequisites:** ...
        **Deliverables:** ...
        **Quality Checklist:** ...
        **Validation Checklist:** ...
        **Files Changed:** ...
```

**Step decomposition — horizontal vs vertical slices:**
```
WRONG (horizontal):              RIGHT (vertical):
  Step 1: Define all types         Step 1: Implement + test feature A
  Step 2: Write all functions       Step 2: Implement + test feature B
  Step 3: Write all tests           Step 3: Implement + test feature C
```
Each step is a thin vertical slice through all layers (types, logic, tests) — independently verifiable.

**Triage skipped because "the task seemed simple":** the triage block decides what is simple. Emit it even for one-line changes; Lightweight is a valid outcome, but the declaration is not optional.

**Open questions parked in the plan:**
```
WRONG:  ## Open Questions
        - Which UI should change?
        ### Step 1: Resolve open questions

RIGHT:  Ask the question in chat before creating/finalizing the plan,
        then write ## Open Questions as `None.`
```

**Silent design assumptions:**
```
WRONG:  Agent picks modal vs page, dropdown vs radio, join table vs JSON column
        without asking — implements whatever seems reasonable

RIGHT:  Surface the choice in Design Decisions table during planning,
        confirm with user, then implement the confirmed approach
```

Status values: `PENDING` | `IN_PROGRESS` | `COMPLETED` | `BLOCKED`

## Behavioral Guards — All Code Tasks

These guards are active for the **entire session**, not just the first response. Do not drift.

1. **Evidence first** — read relevant files before editing; verify paths/imports/dependencies; search callers/usages before changing shared behavior.
2. **Simplicity** — minimum code for the problem; no unrequested features, abstractions, dependencies, or defensive handling.
3. **Surgical changes** — touch only needed files/lines; match formatting, naming, and import conventions; do not copy degraded correctness patterns.
4. **Reuse before create** — before writing a new component, utility, hook, type, or schema pattern, search for existing equivalents; evidence of the search must appear in Verified Facts. Extract duplication when extraction is small and in scope; mention unrelated issues but don't fix them.
5. **Design discipline** — do not make silent design choices about UI layout, schema shape, component structure, or API contracts. Surface design decisions for user confirmation during clarification. During execution, verify approach matches existing conventions or the Design Decisions table.
6. **Goal-driven** — define success before coding; verify via tests/lint/format/build/typecheck; add focused tests for new code and bug fixes when a test framework exists; run quality gates including pre-commit hooks; fix introduced issues or report blockers.

## Full Workflow

### File Structure

```
plans/
├── repo-map.md      # Advisory project memory; verify before relying on it
├── context.md       # Session state (overwritten each pause)
└── <task>.md        # Task plan, Working Set, Verified Facts, progress
```

### Phases

1. **Clarify** — analyze scope, ask targeted questions, get explicit confirmation. See [require-clarification](references/require-clarification.md).
2. **Plan** — break into small ordered steps, copy [`references/plan-template.md`](references/plan-template.md) to `plans/<task>.md` and fill it in. See [create-plan](references/create-plan.md).
3. **Execute** — one step at a time: [execute-step](references/execute-step.md) → [verify-step](references/verify-step.md) → [persist-plan](references/persist-plan.md). Update [maintain-repo-map](references/maintain-repo-map.md) only for durable project discoveries. Every 2–3 steps: [reflect](references/reflect-after-changes.md). Before pausing: [dump-context](references/dump-context.md).
4. **Reflect** — after all steps done: [global-reflection](references/global-reflection.md).

### Resume Protocol

New session on existing work → read in order: `plans/context.md` → `plans/<task>.md`; read `plans/repo-map.md` only if present/useful and verify entries against the current workspace. See [resume-workflow](references/resume-workflow.md).

## Core Constraints

- **One step at a time** — never batch multiple steps.
- **Stay in scope** — only touch files in the step's plan.
- **Persist reality** — keep the plan file accurate at all times.
- **Record evidence** — keep the plan's Working Set, Verified Facts, Implementation Notes, and Files Changed accurate.
- **Never mark COMPLETED with known issues** — fix or mark BLOCKED.

## Reference Guide

| Reference | Purpose |
|---|---|
| [plan-template](references/plan-template.md) | Canonical `plans/<task>.md` skeleton — copy verbatim |
| [require-clarification](references/require-clarification.md) | Clarify ambiguous requests before planning |
| [create-plan](references/create-plan.md) | Exploration, step sizing, plan creation procedure |
| [execute-step](references/execute-step.md) | Code protection, implementation, scope management |
| [verify-step](references/verify-step.md) | Validate + test + review diff (single gate) |
| [maintain-repo-map](references/maintain-repo-map.md) | Maintain advisory project memory; verify before relying on it |
| [persist-plan](references/persist-plan.md) | Update plan status and context |
| [dump-context](references/dump-context.md) | Save session state before pausing |
| [reflect-after-changes](references/reflect-after-changes.md) | Catch complexity every 2–3 steps |
| [global-reflection](references/global-reflection.md) | Final review when all steps done |
| [resume-workflow](references/resume-workflow.md) | Resume existing work in a new session |
