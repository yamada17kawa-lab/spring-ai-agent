# swe-workflow

A lightweight, structured development workflow for AI coding agents. Compatible with **Pi**, **Claude Code**, **Cursor**, **Codex**, **Gemini**, and other agents supporting the Agent Skills spec.

> Every code task starts with workflow triage. Simple tasks stay lightweight; broad, ambiguous, or multi-file tasks use a persisted plan.

## What It Does

Enforces visible workflow selection plus a disciplined process for any coding task complex enough to warrant it:

1. **Triage** — declare `Lightweight` or `Full` mode before edits
2. **Clarify** — understand the request before acting; surface design decisions
3. **Plan** — break Full workflow work into small, ordered steps with a Design Decisions table
4. **Execute** — one step at a time, documented as you go; verify design against conventions
5. **Verify** — re-read, test, review diff (single gate)
6. **Reflect** — catch complexity before it compounds

Every reference file front-loads a **Contract block** (first 5-10 rules) so even low-thinking agents absorb the critical gates.

In Full workflow mode, task state lives in `plans/<task>.md` plus `plans/context.md`; optional `plans/repo-map.md` stores advisory project memory that must be verified against the current workspace.

## Why

Without structure, coding agents tend to:
- Jump straight to code without understanding the request
- Make sweeping changes that break unrelated code
- Skip testing and self-review
- Lose context across sessions

This workflow addresses all of that with a single skill, mandatory mode declaration, and persistent plan files when needed. See [`SKILL.md`](SKILL.md) for the full agent-facing specification.

## Expected Agent Behavior

For every code-related task, the agent must first declare:

```text
Workflow mode: Lightweight | Full
Reason: ...
Success criteria:
- ...
Plan needed: yes | no
```

Full workflow mode is mandatory for broad cleanup/refactor/lint tasks, deletes/moves, backend + UI changes, API/schema/route/tooling/config changes, source-of-truth docs, ambiguous work, or anything expected to touch more than 3 files.

In Full workflow mode, unresolved questions must be asked in chat before plan creation/finalization; valid plans record `Open Questions` as `None.` and surface design choices in a `Design Decisions` table for user confirmation. Implementation edits must wait until a valid plan exists, the current step is marked `IN_PROGRESS`, and the edit maps to that step.

## Installation

Via the [Agent Skills CLI](https://skills.sh):

```bash
# Install globally (all supported agents)
npx skills add ex-git/swe-workflow -g

# Install for a specific agent
npx skills add ex-git/swe-workflow -g --agent claude
npx skills add ex-git/swe-workflow -g --agent cursor
npx skills add ex-git/swe-workflow -g --agent pi
npx skills add ex-git/swe-workflow -g --agent codex
npx skills add ex-git/swe-workflow -g --agent gemini
```

### Manual install

Copy the skill directory to your agent's skills location:

```bash
# Pi
cp -r swe-workflow ~/.pi/agent/skills/

# Claude Code
cp -r swe-workflow ~/.claude/skills/

# Cursor
cp -r swe-workflow ~/.cursor/skills/

# Codex
cp -r swe-workflow ~/.codex/skills/

# Gemini
cp -r swe-workflow ~/.gemini/skills/
```

## Structure

```
swe-workflow/
├── SKILL.md                     # Agent-facing entry point (mandatory router/contract)
├── references/                  # Supplementary detail for each workflow phase
│   ├── plan-template.md         # Canonical plan skeleton — copy verbatim
│   ├── plan-example.md          # Filled-in 3-step example plan
│   ├── code-quality.md          # Reusable code quality bar
│   ├── definition-of-done.md     # Final completion gate
│   ├── command-discovery.md      # Validation command discovery
│   ├── risk-classification.md    # Compact risk routing rules
│   ├── project-agents-template.md # Template for target repo AGENTS.md
│   ├── require-clarification.md
│   ├── create-plan.md
│   ├── resume-workflow.md
│   ├── execute-step.md
│   ├── verify-step.md
│   ├── maintain-repo-map.md
│   ├── persist-plan.md
│   ├── dump-context.md
│   ├── reflect-after-changes.md
│   └── global-reflection.md
├── examples/                    # Worked examples and optional evaluation aids
│   └── evaluation-prompts.md     # Prompts for workflow evaluation
├── AGENTS.md                    # Repo-level agent instructions / template
├── CHANGELOG.md
├── LICENSE
├── package.json
└── README.md
```

## Production Code Quality Setup

This skill provides the workflow contract. For production codebases, pair it with a project-level `AGENTS.md`.

Recommended setup:

1. Install `swe-workflow`.
2. Copy [`references/project-agents-template.md`](references/project-agents-template.md) into the target repo as `AGENTS.md`.
3. Fill in required commands, architecture rules, API rules, data rules, security rules, testing rules, and do-not-touch areas.
4. Test the setup with [`examples/evaluation-prompts.md`](examples/evaluation-prompts.md).

Keep `SKILL.md` small. Put project-specific rules in the target repo's `AGENTS.md`, not in this skill.

## Verification

After installing, test with a vague feature request:

```
Add a function that validates email addresses
```

**Expected behavior:** the agent declares Full workflow mode, explains why the request is ambiguous, and asks clarifying questions (scope, input format, error shape) rather than jumping to code.

## Multi-Agent Handoffs

The task plan and context files are portable across agents. Agent A can complete steps 1–3 and dump context; Agent B reads `plans/context.md` + `plans/<task>.md`, optionally checks `plans/repo-map.md` for advisory project memory, verifies relevant entries in the current workspace, and picks up at step 4.

## Documentation

- [`SKILL.md`](SKILL.md) — the full agent-facing workflow
- [`CHANGELOG.md`](CHANGELOG.md) — release history
- [`examples/`](examples) — worked example plans

## License

MIT — see [LICENSE](LICENSE).
