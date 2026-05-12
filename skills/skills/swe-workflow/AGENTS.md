# Agent Instructions

This repository contains the `swe-workflow` skill. `SKILL.md` is the canonical source of truth; follow it before modifying code, docs, config, or workflow files.

## Mandatory Workflow Triage

For every code-related task, the first assistant response MUST include:

```text
Workflow mode: Lightweight | Full
Reason: <one sentence>
Success criteria:
- <what done means>
Plan needed: yes | no
```

## When Full Workflow Is Required

Use Full workflow mode before editing for:

- repo-wide scans, migrations, or cleanup
- lint/type/build/test cleanup
- broad refactors or behavior-preserving rewrites touching multiple files
- deleting, moving, or renaming files
- backend + frontend/UI changes in one task
- API/schema/route/tooling/configuration contract changes
- docs/source-of-truth updates, including `SKILL.md`, `README.md`, `CHANGELOG.md`, or this file
- any task expected to touch more than 3 files
- ambiguous scope or unclear success criteria

If a Lightweight task grows into any trigger above, stop before further edits, declare escalation to Full workflow mode, create/update the plan, and continue from the current verified state.

## Full Workflow Pre-Edit Gate

Before any write/edit/delete that changes task target files in Full workflow mode:

1. a plan file must exist under `plans/`,
2. exactly one current step must be marked `IN_PROGRESS`, and
3. the intended edit must map to that step.

Only workflow bookkeeping files (`plans/*.md`) may be created or updated before this gate is satisfied.

## Change Discipline

- Keep changes surgical and tied to the user request or current plan step.
- Verify each step with the relevant lint/type/build/test/manual check.
- Update `CHANGELOG.md` for user-visible behavior, packaging, or source-of-truth documentation changes.

## Known Pitfalls

- **SKILL.md YAML front-matter.** The `description` field (and any other scalar) must not contain bare `: ` (colon-space) sequences in plain, unquoted form — YAML parses `: ` as a map-key separator and loaders will raise `mapping values are not allowed in this context`. This is easy to hit because the description often quotes the triage labels (`Workflow mode: ...`, `Plan needed: ...`). Wrap any description that mentions `: ` in a folded block scalar (`>-`) or a double-/single-quoted string. After editing, validate by parsing the front-matter, e.g. `awk '/^---$/{c++;if(c==1)next;if(c==2)exit}c==1' SKILL.md | ruby -ryaml -e 'YAML.safe_load($stdin.read); puts "OK"'`.
- **Version bumps drift across files.** The canonical version lives in three places: `SKILL.md` front-matter `metadata.version`, `package.json` `version`, and the top `CHANGELOG.md` entry. Any bump plan must grep for the current version string and touch every hit. See `references/create-plan.md` ⨟ Common Scope Traps.

## Skill Bloat Control

- Keep `SKILL.md` focused on mandatory contract, mode routing, gates, and reference loading.
- Do not add long quality, security, performance, framework, or project-specific checklists to `SKILL.md`.
- Put phase-specific rules in `references/*.md`.
- Put optional examples in `examples/*`.
- Put target-repo-specific rules in that repo's `AGENTS.md`.
- If a rule is not required for every code task, it probably does not belong in `SKILL.md`.
