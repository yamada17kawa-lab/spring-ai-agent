# Code Quality Bar

Use this standard when planning, implementing, and verifying code changes. Apply the closest existing project convention first; this bar fills gaps and prevents avoidable regressions.

This file defines general quality standards. Do not add framework-specific or project-specific rules here. Put project-specific rules in the target repo's `AGENTS.md`.

## Contract

1. Preserve existing behavior unless explicitly changed.
2. Prefer the smallest complete change.
3. Follow local project patterns.
4. Reuse before creating.
5. Handle failure modes consistently.
6. Do not introduce security, data, or performance risk without mitigation.

## Correctness
- Preserve existing behavior unless the task explicitly changes it.
- Identify input/output contracts before editing.
- Handle null, empty, and error cases consistently with nearby code.
- Do not swallow errors unless existing project conventions do so.
- Do not introduce race conditions, stale state, or partial updates.

## Simplicity
- Prefer the smallest change that fully satisfies the task.
- Do not add abstractions until there are at least two real call sites or the existing project pattern requires it.
- Avoid speculative options, flags, frameworks, or new dependencies.

## Maintainability
- Use names that reveal domain meaning, not implementation mechanics.
- Keep functions focused; split only when it improves readability.
- Keep related logic together unless the repo has a clear layering convention.
- Do not duplicate business rules across files.

## Project Fit
- Match existing naming, file organization, error handling, logging, testing, and dependency patterns.
- Reuse existing helpers, components, hooks, services, and types before creating new ones.
- Do not copy known-bad legacy patterns unless required for compatibility; document the compatibility reason if you must.

## API / Schema / Contract Changes
- Identify all callers and downstream consumers.
- Preserve backward compatibility unless explicitly approved.
- Update tests, types, docs, fixtures, mocks, and examples together.
- Include migration and rollback notes for data shape changes.

## Security
- Do not log secrets, tokens, credentials, PII, or sensitive payloads.
- Validate and normalize untrusted input at boundaries.
- Check authorization before data access or mutation.
- Avoid SQL, command, path, and template injection.
- Use existing secret and configuration mechanisms.

## Performance
- Avoid unnecessary full scans, N+1 queries, repeated network calls, and large synchronous work.
- Consider pagination, batching, caching, and indexes when touching data access.
- Do not optimize speculatively; optimize when the code path or task requires it.

## Observability
- Preserve existing logging, metrics, and tracing conventions.
- Add useful logs only at boundaries or failure points.
- Do not add noisy debug logs.
