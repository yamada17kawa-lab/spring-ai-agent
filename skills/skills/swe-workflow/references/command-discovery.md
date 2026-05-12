# Command Discovery

Use this before choosing validation commands.

## Contract

1. Do not invent validation commands.
2. Discover commands from the current repo.
3. Prefer project-documented commands over generic commands.
4. Record discovered commands in the plan.
5. If no command exists, say so and choose the closest targeted validation.

## Where to Look

Inspect, when present:

- `AGENTS.md`
- `README.md`
- `package.json`
- `Makefile`
- `justfile`
- `Taskfile.yml`
- `pyproject.toml`
- `tox.ini`
- `setup.cfg`
- `Cargo.toml`
- `go.mod`
- `.github/workflows/*`
- `docs/*`

## Command Priority

Use this order:

1. Repo-specific instructions from `AGENTS.md`
2. README or docs
3. Package scripts / Makefile / task runner
4. CI workflow commands
5. Language default commands only as fallback

## Required Plan Entry

Record commands as:

| Purpose | Command | Source | Required? |
|---|---|---|---|
| Typecheck |  |  |  |
| Lint |  |  |  |
| Test |  |  |  |
| Build |  |  |  |

## Skipped Commands

For every skipped command, record:

- command
- reason skipped
- risk
- substitute validation, if any
