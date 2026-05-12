# Definition of Done

Use this file during the final verification phase. Do not mark a task or step complete until all applicable items are satisfied.

## Contract

1. The diff contains only changes required by the task.
2. Modified files were re-read after editing.
3. The actual git diff was reviewed.
4. Relevant validation commands were run, or skipped with reason and risk.
5. Tests were added or updated for changed behavior when applicable.
6. API, data, security, performance, and observability risks were reviewed.
7. The final response lists files changed, validation run, and residual risks.

## Scope

- No unrelated refactors.
- No formatting-only churn outside touched code.
- No dependency changes unless explicitly required.
- No public contract changes unless planned and approved.

## Correctness

- The implementation satisfies the success criteria.
- Existing behavior is preserved unless the task explicitly changes it.
- Edge cases and failure cases are handled consistently with nearby code.

## Project Fit

- The change follows local naming, layering, error handling, logging, and testing conventions.
- Existing helpers, components, services, types, and utilities were reused where appropriate.
- New abstractions are justified by current use, not speculative future use.

## Validation

Record:

| Check | Command | Result | Notes |
|---|---|---|---|
| Typecheck |  |  |  |
| Lint |  |  |  |
| Test |  |  |  |
| Build |  |  |  |

If a command is skipped, record why.

## Final Response Requirements

The final response must include:

- Summary of changes
- Files changed
- Validation run
- Known limitations or residual risks
