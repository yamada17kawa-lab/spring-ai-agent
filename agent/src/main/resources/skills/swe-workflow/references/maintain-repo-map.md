# Maintain Repo Map

## Contract — Read This First

1. `plans/repo-map.md` is advisory project memory, not authoritative inventory.
2. Verify every entry against the current workspace before relying on it.
3. Keep entries sparse and durable — only conventions, stable paths, gotchas worth preserving.
4. Task-specific evidence belongs in the plan's Working Set/Verified Facts, not here.
5. Do not create exhaustive file inventories or symbol maps.

## When to Use

In Full workflow when a durable discovery helps future agents:
- Important directories or entry points likely to stay valid
- Durable architecture patterns or conventions
- Recurring gotchas not obvious from file names
- Cross-cutting files repeatedly relevant across tasks

Do NOT use for Lightweight mode or one-off file listings.

## Before Using Any Entry

1. Verify path exists: `ls`, `find`, `rg`, `git ls-files`, or `read`.
2. Re-read the file before editing.
3. If stale, update or remove.
4. Never cite a repo-map entry as a Verified Fact without current-workspace verification.

## What to Include

- **Key Files:** main entry points, core config, routing, schema, shared utilities
- **Key Directories:** project layout (`src/`, `tests/`, `packages/`)
- **Architecture Notes:** error handling style, dependency direction, module boundaries, test conventions
- **Gotchas:** generated files that look authoritative, branch-specific behavior, post-change commands

## What to Skip

- Every file read or modified during a task
- Symbol-level maps, line ranges, function inventories
- Build output (`node_modules/`, `dist/`, `build/`, `.next/`)
- Facts that only apply to the current task
- Unverified paths or guesses

## Suggested Structure

```markdown
# Repo Map

> Last Updated: YYYY-MM-DD
> Project: [name]
> Advisory: verify entries before use.

## Key Files
| Path | Why It Matters | Last Verified |
|------|----------------|---------------|

## Key Directories
| Path | Contents | Last Verified |
|------|----------|---------------|

## Architecture Notes
### Patterns
- [Pattern]: [where it applies]
### Conventions
- [Convention]: [evidence]
### Gotchas
- [Warning]: [how to avoid]
```

## How to Update

1. Read current `plans/repo-map.md` if it exists.
2. Verify the path/fact in the current workspace.
3. Add or update the smallest useful entry.
4. Record when last verified.

## Where Evidence Belongs

| Need | Location |
|------|----------|
| Files changed in this task | Step `Files Changed` |
| Evidence for current implementation | Plan `Working Set` / `Verified Facts` |
| Session handoff | `plans/context.md` |
| Durable project conventions | `plans/repo-map.md` |
