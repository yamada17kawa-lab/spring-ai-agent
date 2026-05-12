# Persist Plan

## Contract — Read This First

1. The plan file is the source of truth — update it as soon as reality changes.
2. Never mark COMPLETED with known introduced issues — fix or mark BLOCKED.
3. Every COMPLETED step must have all 8 fields populated with evidence-backed entries.
4. Never leave IN_PROGRESS across sessions — complete, block, or dump context.
5. Use `edit` for status changes — don't rewrite the entire plan file.
6. After persisting, orient the user with progress summary.

## When to Use

After marking IN_PROGRESS, after implementation, after verification, after COMPLETED/BLOCKED, before asking user to continue.

## After Starting a Step

1. Change `**Status:** PENDING` → `**Status:** IN_PROGRESS`.
2. Update `Last Updated`.

## After Completing Implementation

Update the current step with:
- **Implementation Notes:** what changed, why, decisions, deviations
- **Files Changed:** exact paths created/modified/deleted
- **Working Set / Verified Facts:** updated with evidence (not guesses)

## After Verification Passes

1. Confirm all 8 fields populated.
2. Check off Quality, Validation, and Test Checklist items.
3. Confirm introduced issues fixed, or mark BLOCKED.
4. Update Context & Learnings with durable decisions/gotchas.
5. Update advisory `plans/repo-map.md` only for durable project discoveries.
6. Change `**Status:** IN_PROGRESS` → `**Status:** COMPLETED`.
7. Update `Last Updated`.
8. Add Implementation Log row.

## After Blocking a Step

1. Change status to `BLOCKED`.
2. Document blocker, evidence, and unblock path.
3. Update `Last Updated`.
4. Proceed to next safe PENDING step or report to user.

## When Scope Changes

1. Stop implementation.
2. Read the entire plan.
3. Update steps in dependency order.
4. Document in Implementation Notes or Deviations.
5. Ensure exactly one step is IN_PROGRESS before resuming.

## Before Asking User "Should I Continue?"

1. Write current state to `plans/context.md` (see [dump-context](dump-context.md)).
2. Then ask the user.

## Mandatory Updates When Marking COMPLETED

- [ ] Status → COMPLETED
- [ ] Implementation Notes filled (what/why/decisions)
- [ ] Files Changed lists exact paths
- [ ] Working Set and Verified Facts accurate and evidence-backed
- [ ] Context & Learnings updated
- [ ] Quality, Validation, and Test Checklists checked or documented
- [ ] Introduced failures fixed or blocker documented
- [ ] Last Updated changed
- [ ] Implementation Log entry added

## Orient the User

**After step:** "Step X complete. Checks passed: [list]. Remaining: [steps]."
**Blocked:** "Step X blocked: [reason]. Evidence: [checked]."
**Pausing:** "Progress saved. Continue with [next action]?"

## Next Step

- More PENDING → **execute-step**
- All COMPLETED → **global-reflection**
- BLOCKED → next safe step or report
- Pausing → context dumped, ask user
