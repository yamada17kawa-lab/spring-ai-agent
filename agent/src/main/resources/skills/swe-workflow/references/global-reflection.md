# Global Reflection

## Contract — Read This First

1. Run once when ALL steps are COMPLETED — this is the last gate before delivery.
2. Re-read the entire plan file AND every modified/created file with `read`.
3. Verify goal achieved, evidence accurate, architecture clean, no artifacts.
4. Fix issues found. Minor → fix directly. Major → create new steps. Critical → STOP and flag.
5. Would you hand this to another developer to maintain? If not, fix the uncomfortable parts.

## Instructions

1. **Re-read the plan file** — verify all steps COMPLETED, review Implementation Log, check deviations, review Working Set/Verified Facts for evidence gaps, review Context & Learnings for completeness.

2. **Re-read ALL modified/created files** with `read` — in the order changes were made.

3. **Evaluate:**

   | Area | Check |
   |------|-------|
   | **Goal** | Achieved? All requirements met? Solution complete? |
   | **Evidence** | Working Set accurate? Verified Facts still true? Files Changed complete? |
   | **Architecture** | Clean design? Could be simpler? Appropriate abstractions? Sensible file organization? |
   | **Code quality** | Technical debt? Error handling sufficient? Edge cases covered? Clear naming? |
   | **Tests** | All passing? Adequate coverage? Edge cases covered? |
   | **Artifacts** | Commented-out code? TODO/FIXME? Debug statements? Hardcoded values? |
   | **Security** | Input validation? Auth checks? Injection risks? |
   | **Performance** | N+1 queries? Unnecessary loops? Memory leaks? |

4. **Fix what you find:**

   | Severity | Action |
   |----------|--------|
   | Minor (typos, small improvements) | Fix directly, update plan notes |
   | Major (architecture, missing features) | Create new plan steps, execute through full workflow |
   | Critical (security, data loss risk) | STOP, flag to user, do not proceed |

## Mandatory Checklist

- [ ] Re-read entire plan file
- [ ] Re-read ALL modified/created files
- [ ] Goal achieved with evidence
- [ ] Context & Learnings complete (decisions, gotchas, patterns)
- [ ] Working Set, Verified Facts, Files Changed accurate
- [ ] Architecture clean, no unnecessary complexity
- [ ] No artifacts (debug statements, commented code, stale TODOs)
- [ ] All tests passing
- [ ] Error handling sufficient
- [ ] Comfortable handing to another developer

## After Reflection

If all checks pass:
1. Feature is **DONE**.
2. Consider: `git add -A && git commit -m "feat: [description]"`

If issues found and fixed: update Implementation Log, re-run affected tests.

## This is the End

No more references to load. The workflow is finished.
