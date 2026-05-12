# Reflect After Changes

## Contract — Read This First

1. Run after every 2-3 COMPLETED steps, before continuing.
2. Re-read all modified files with `read` — not from memory.
3. Check for: repeated patterns, unclear naming, growing complexity, tight coupling, workarounds.
4. Refactor if needed — extract duplication, simplify, improve names. Run tests after.
5. Verify remaining PENDING steps still make sense. If plan drifted, communicate before continuing.

## Instructions

1. **Re-read all files modified** in recent steps with `read`.

2. **Check for warning signs:**

   | Sign | Threshold | Action |
   |------|-----------|--------|
   | Same logic in multiple places | 3+ occurrences | Extract to shared function |
   | Function length | >30 lines | Break into smaller functions |
   | Nesting depth | >3 levels | Extract conditions or use guard clauses |
   | Poor names | `temp`, `data`, `result` | Rename for clarity |
   | Many imports from one file | Coupling signal | Consider interface or facade |
   | Hack comments | `TODO`, `FIXME` accumulating | Fix root cause |

3. **Refactor if needed:**
   - Extract duplication, simplify logic, improve names, reduce nesting
   - Ensure behavior unchanged (run tests)
   - No scope creep — just cleaning, not adding features
   - Update plan if structure changed

4. **Verify remaining plan:**
   - Re-read remaining PENDING steps
   - Do they still fit the current implementation?
   - Did bug fixes or adjustments change direction?
   - **If plan drifted:** summarize discrepancy to user, propose updates, get confirmation

   **Drift signals:** remaining steps reference changed code, step descriptions seem outdated, previous steps took much longer than expected.

## Mandatory Checks

- [ ] Re-read modified files with `read`
- [ ] Checked for repeated patterns, naming, complexity, coupling
- [ ] Refactored if needed (and ran tests)
- [ ] Verified remaining steps still make sense
- [ ] Updated plan if structure changed

## Next Step

→ **execute-step** (next PENDING step). If significant issues → update plan with new steps first.
