# Execute Step

## Contract — Read This First

1. Read the plan file. Confirm `Open Questions` is `None.` — if not, STOP and ask in chat.
2. Select the first PENDING step. Mark it `IN_PROGRESS`. Update `Last Updated`.
3. Re-read target files before editing — do not rely on memory. Verify imports, callers, dependencies.
4. If step is design-sensitive (UI, schema, API shape): confirm the approach matches existing conventions or the Design Decisions table. If ambiguous, ask the user before implementing.
5. Protect code: `git commit` or `git stash` before changes.
6. Use `edit` for existing files, `write` for new files. Never `write` over an existing file.
7. Implement ONLY what this step requires. Do not touch unrelated files.
8. Reuse before create: if about to create a new component/utility/pattern, search for existing equivalents first. Record evidence.
9. Update Working Set, Verified Facts, Implementation Notes, Files Changed.
10. If scope needs to expand: STOP, update the plan, notify user. Never silently expand.

## Instructions

1. **Read the plan file** — use `read` on `plans/<name>.md`. Confirm correct plan.

2. **Open Questions gate** — confirm `## Open Questions` is `None.`. If unresolved, STOP and ask the user.

3. **Select first PENDING step** — skip COMPLETED, note BLOCKED.

4. **Mark IN_PROGRESS** — `edit` the step status, update `Last Updated`, persist.

5. **Read the step's Plan section** — understand every bullet, identify target files, note dependencies.

6. **Verify Working Set before editing:**
   - Re-read target files or targeted sections; do not rely on memory
   - Check imports/exports and nearby code for local conventions
   - Search callers/usages before changing shared functions, types, routes, schemas, or config
   - Verify dependencies/packages/APIs exist before importing
   - Record new evidence in Working Set and Verified Facts

7. **Quality Gate Before Editing:**
   - Identify and record the local pattern this change should follow
   - Identify the contract: inputs, outputs, errors, side effects
   - Identify the reuse target, or record evidence that none exists
   - Classify risk with [`references/risk-classification.md`](risk-classification.md) and record mitigation for every non-none risk
   - Before implementation, discover validation commands with [`references/command-discovery.md`](command-discovery.md) when the task will require tests, build, lint, typecheck, or CI-equivalent validation
   - Identify the exact behavior this step will prove with tests or manual verification
   - If any item is unknown, inspect the repo before editing. Do not guess.

8. **Design confirmation gate** (when step touches UI, schema, or API surface):
   - Verify approach matches existing patterns (component library, naming, spacing, response shape)
   - Check the Design Decisions table — is this choice already confirmed?
   - If no pattern exists or two reasonable approaches exist, ask the user before implementing
   - Before creating new: search for existing reusable code, record evidence

9. **Protect code before changes:**
   - **Git (preferred):** `git add -A && git commit -m "WIP: before step N"`
   - **Copy snapshot:** if no git, copy files to `snapshots/step-N/`
   - **Tool rules:** 1-20 lines → `edit`; 21+ lines → `edit` with multiple entries; new file → `write`; delete → verify in plan first
   - **Never `write` to "edit" an existing file** — you'll lose content

10. **Implement ONLY what this step requires:**
   - Stay within scope — do not touch unrelated files
   - Use `edit` for surgical changes, `write` for new files
   - Follow existing formatting, naming, import, and comment conventions
   - Handle edge cases and errors consistently with the project
   - If you need a file outside the Working Set, verify it first and add evidence

11. **During Implementation:**
   - Apply [`references/code-quality.md`](code-quality.md)
   - Do not complete the step with unverified imports, packages, or APIs
   - Do not duplicate business logic
   - Do not add a new dependency without approval
   - Do not make a silent contract change
   - Do not perform broad refactors unrelated to the step
   - Do not omit error handling for fallible operations
   - Do not rely on tests that cover only the happy path
   - Reclassify risk if implementation touches new files or contracts

12. **Update evidence:**
    - Add modified files to Working Set with role and evidence
    - Record facts in Verified Facts with tool/read/search proof
    - Do not record guesses as facts

13. **Document:**
    - **Implementation Notes** — what, why, decisions, deviations
    - **Files Changed** — exact paths
    - Persist plan

## Implementation Guidelines

Apply [`references/code-quality.md`](code-quality.md) throughout implementation.

**DO:** surgical changes, follow existing conventions, clear names, avoid duplication when extraction is small and in scope, handle errors consistently, update docs when contracts change.

**DO NOT:** refactor outside scope, add unrequested features/abstractions/dependencies, delete without understanding, formatting-only changes to unrelated files, leave debug statements, silence failures without documenting, copy degraded patterns, guess paths/APIs/callers.

## Scope Changes During Execution

### Out-of-scope finding
| Type | Action |
|------|--------|
| **Direct dependency** (step can't complete without it) | Small + coupled → include, document in Files Changed. Significant → STOP, add prerequisite step, notify user. |
| **Optional improvement** | Note in Implementation Notes as "Future consideration." Do NOT implement. |
| **Separate concern** | Add as new step after current one. Complete current step first. |

### Unplanned work (bug fixes, user adjustments)
After any unplanned change: (1) re-read remaining PENDING steps, (2) check if they're still valid, (3) summarize to user with "Steps completed: X/Y. Remaining: [list]. Still valid?" — do NOT silently continue.

**Plan drift signals:** bug fix took 30+ min, user adjusted 3+ times, remaining steps seem wrong, step took 3× longer than expected → pause, re-assess, communicate.

### Minor vs major findings
- **Minor** (different param name, extra import, file location differs): update step Plan, document in Notes, continue.
- **Major** (library missing feature, API works differently, architecture won't scale): STOP, update Assumptions, review earlier steps, get user confirmation, document deviations.

### Blocker
Mark step `BLOCKED`, document reason + unblock path, proceed to next safe PENDING step or report to user.

## Mandatory Checklist

- [ ] Code changes complete
- [ ] Only files in scope modified
- [ ] Quality Gate Before Editing recorded in plan evidence or notes
- [ ] Working Set and Verified Facts updated with evidence
- [ ] Implementation Notes filled in
- [ ] Files Changed list complete
- [ ] Deviations documented
- [ ] Design choices confirmed (if design-sensitive step)

## Next Steps

1. → **verify-step** (validate + test + review-diff)
2. → **persist-plan** (mark COMPLETED)
3. → **execute-step** (next PENDING) or **global-reflection** (all done)

After `verify-step` passes, compare diff against snapshot from step 8. If unrelated code was modified or deleted, restore and re-implement with smaller edits.
