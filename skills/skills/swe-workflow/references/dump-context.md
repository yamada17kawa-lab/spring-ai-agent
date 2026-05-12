# Dump Context

## Contract — Read This First

1. Write `plans/context.md` before any pause that might span sessions.
2. Include: current task, completed steps, current step progress, decisions, Working Set, next actions.
3. Context file is overwritten each session — plan file is the durable record.
4. Verify the file was written with `read`.

## When to Use

Before asking "Should I continue?", after 2-3 steps, when significant decisions made, before any session pause.

## Instructions

1. `mkdir -p plans/`
2. Write `plans/context.md` using the template below.
3. Verify with `read`.

## Template

```markdown
# Session Context

> Last Updated: [YYYY-MM-DD HH:MM]
> Session: [Brief description]

## Current Task
[One sentence]

## Completed Steps
| Step | Summary | Files Changed |
|------|---------|---------------|
| Step N | [Brief] | [files] |

## Current Step
**Step N: [Title]**
**Status:** IN_PROGRESS | PENDING

### What Was Done
- [Action taken]
### Decisions Made
- [Decision]: [Rationale]
### Active Files
| Path | Status | Purpose |
|------|--------|---------|
| [path] | [reading/modifying/created] | [why] |

## Key Learnings
- [Gotcha or pattern]: [context]

## Open Questions
- [ ] [Question waiting for user]

## Next Actions
1. [Ordered next steps]
```

## What NOT to Include

- Full file contents (reference paths)
- Detailed diff output (summarize)
- Temporary analysis (only decisions/findings)
- Obsolete information

## After Dumping

Print brief progress summary, ask question, mention context is preserved:
> "Completed Steps 1-2, started Step 3. Context saved. Should I continue with [next action]?"

## Next Step

→ **Ask user for input** or **execute-step**
