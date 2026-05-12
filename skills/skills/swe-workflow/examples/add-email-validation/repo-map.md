# Repo Map

> Last Updated: 2024-11-05
> Project: example-app (TypeScript + Vitest)

This file is advisory project memory for stable files, directories, and conventions discovered during the example task. It is not authoritative; verify paths and conventions in the current workspace before relying on them.

## Core Files

| Path | Purpose | Task | Last Updated |
|---|---|---|---|
| `src/utils/email.ts` | Email validation utility (`validateEmail` + error types) | add-email-validation | 2024-11-05 |
| `src/utils/email.test.ts` | Vitest suite for `validateEmail` | add-email-validation | 2024-11-05 |
| `src/utils/index.ts` | Barrel export for utils | add-email-validation | 2024-11-04 |

## Related Files

| Path | Why Relevant | Task | Last Updated |
|---|---|---|---|
| `src/utils/phone.ts` | Reference pattern for validator shape (`{ valid, error }`) and error-code approach | add-email-validation | 2024-11-04 |
| `src/utils/phone.test.ts` | Reference for `describe.each` parameterized test style | add-email-validation | 2024-11-05 |
| `src/utils/zip.ts` | Another validator following the same convention | add-email-validation | 2024-11-04 |
| `docs/deps-policy.md` | Documents that `validator` package is explicitly rejected | add-email-validation | 2024-11-04 |
| `package.json` | Confirms Vitest availability; no new deps needed | add-email-validation | 2024-11-04 |

## Key Directories

| Path | Contents | Last Updated |
|---|---|---|
| `src/` | Application source | 2024-11-04 |
| `src/utils/` | Standalone validators; colocated `*.test.ts` | 2024-11-04 |
| `docs/` | Markdown docs including policies and utils reference | 2024-11-04 |

## Architecture Notes

### Patterns
- **Colocated tests**: `*.test.ts` lives next to the source file, not under a separate `tests/` tree.
- **Discriminated result**: validators return `{ valid: boolean; error?: Code }` rather than throwing.
- **Error codes, not strings**: localization-friendly; callers map code → message.
- **Barrel exports**: `src/utils/index.ts` re-exports every util so callers import from `@/utils`.

### Conventions
- **No trim-and-accept**: whitespace in input is invalid, matching `validatePhone`.
- **Regex constants at top of file**: one regex per concept, never a mega-regex.
- **Named exports only**: no default exports anywhere in `src/utils/`.

### Key Dependencies
- **Vitest**: test runner (already installed).
- **TypeScript strict mode**: `tsc --noEmit` must stay clean.

## Task History

| Task | Files Modified | Date |
|---|---|---|
| add-email-validation | `src/utils/email.ts`, `src/utils/email.test.ts`, `src/utils/index.ts` | 2024-11-04 — 2024-11-05 |

---

*See [`../../references/maintain-repo-map.md`](../../references/maintain-repo-map.md) for advisory repo-map guidelines. Task-specific evidence belongs in `plan.md` Working Set / Verified Facts.*
