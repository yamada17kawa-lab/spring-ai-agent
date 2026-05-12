# Plan: Add Email Validation

> Status: IN_PROGRESS
> Created: 2024-11-04
> Last Updated: 2024-11-05

## Goal

Provide a standalone `validateEmail(input)` utility that returns a structured result with a specific error code when validation fails.

## Assumptions

- TypeScript project with existing `src/utils/` directory and Vitest configured.
- No email deliverability check (DNS/MX lookup) — format validation only.
- RFC 5322 "practical" subset is acceptable (not the full grammar).
- Error shape: `{ valid: boolean; error?: EmailErrorCode }`.

## Open Questions

> Must be empty before execution begins.

_(none)_

## Context & Learnings

### Key Decisions

- **Return discriminated union with error codes, not free-form strings.** Callers need to localize messages; strings would lock us to English. Codes: `EMPTY`, `NO_AT`, `MULTIPLE_AT`, `INVALID_LOCAL`, `INVALID_DOMAIN`, `TOO_LONG`.
- **Use a regex + length checks, not a parsing library.** The project already rejects `validator` as a dependency in `docs/deps-policy.md`.
- **Treat trailing/leading whitespace as invalid, not trim-and-accept.** Matches the existing `validatePhone` behavior in `src/utils/phone.ts`.

### Gotchas & Warnings

- **Max total length is 254 chars, not 320.** RFC 5321 limit for SMTP envelope. Several sources cite 320 incorrectly.
- **Local part max is 64 chars.** Check independently — some emails have short domains but very long local parts.
- Unicode domains (IDN / Punycode) are explicitly out of scope for v1.


### Working Set

| Path | Role in this task | Evidence |
|---|---|---|
| `src/utils/email.ts` | New validator and main implementation target | Planned from user request; created in Step 1 |
| `src/utils/email.test.ts` | Vitest coverage for validator behavior | Test framework confirmed in `package.json`; created in Step 3 |
| `src/utils/index.ts` | Barrel export for utils | Existing barrel pattern verified by reading file in Step 1 |
| `src/utils/phone.ts` | Reference validator shape/style | Read before implementation; matches `{ valid, error }` pattern |
| `src/utils/phone.test.ts` | Reference test structure | Read before Step 3; uses `describe.each` |
| `docs/deps-policy.md` | Dependency policy | Read before planning; rejects `validator` package |

### Verified Facts

- Vitest is already configured — verified by reading `package.json`.
- Validators use named exports and barrel export through `src/utils/index.ts` — verified by reading existing utils.
- Tests are colocated as `*.test.ts` next to source files — verified by reading `src/utils/phone.test.ts`.
- The `validator` package should not be added — verified by reading `docs/deps-policy.md`.

### Patterns & Conventions

- Every util in `src/utils/` has a matching `*.test.ts` next to it (not in a separate `tests/` tree).
- All validators return `{ valid: boolean; error?: Code }` — see `validatePhone`, `validateZip`.
- Barrel export through `src/utils/index.ts`.

## Steps

### Step 1: Define error codes and result type

**Status:** COMPLETED

**Prerequisites:**
- `src/utils/` exists
- Existing `validatePhone` signature reviewed for consistency

**Deliverables:**
- `EmailErrorCode` union type
- `EmailValidationResult` type

**Plan:**
- [x] Add `EmailErrorCode` union in `src/utils/email.ts`
- [x] Add `EmailValidationResult` type
- [x] Export both

**Validation Checklist:**
- [x] `tsc --noEmit` clean
- [x] Types exported from barrel

**Test Checklist:**
- [x] Compile-time assertions for exhaustive error codes (via `satisfies`)

**Implementation Notes:**
- Used `satisfies readonly string[]` pattern to get the union from the array; matches `validatePhone.ts`.
- Exported types as named exports only; no default export.

**Files Changed:**
- `src/utils/email.ts` (created)
- `src/utils/index.ts` (modified — barrel export)

---

### Step 2: Implement `validateEmail` with regex + length checks

**Status:** COMPLETED

**Prerequisites:**
- Step 1 complete (types available)

**Deliverables:**
- `validateEmail(input: string): EmailValidationResult` function

**Plan:**
- [x] Empty-string check → `EMPTY`
- [x] Single-`@` check → `NO_AT` / `MULTIPLE_AT`
- [x] Total length ≤254 → `TOO_LONG`
- [x] Local part length ≤64 + regex → `INVALID_LOCAL`
- [x] Domain regex (labels, TLD ≥2 chars) → `INVALID_DOMAIN`

**Validation Checklist:**
- [x] `tsc --noEmit` clean
- [x] Lint clean
- [x] No debugging statements

**Test Checklist:**
- [x] All six error branches hit
- [x] Valid address returns `{ valid: true }`

**Implementation Notes:**
- Regex split into `LOCAL_RE` and `DOMAIN_RE` constants at top of file; avoids one monster regex.
- Length check happens first after empty check — cheapest to reject pathological inputs.

**Files Changed:**
- `src/utils/email.ts` (modified)

---

### Step 3: Write comprehensive test suite

**Status:** IN_PROGRESS

**Prerequisites:**
- Step 2 complete
- Vitest available (already in `package.json`)

**Deliverables:**
- `src/utils/email.test.ts` with ≥20 cases covering all error codes and edge cases

**Plan:**
- [x] Happy-path cases: standard address, plus-addressing, subdomain, numeric TLD variants
- [x] `EMPTY`: `""`, whitespace-only
- [x] `NO_AT`: `"foo"`, `"foo.bar"`
- [ ] `MULTIPLE_AT`: `"a@b@c"`
- [ ] `TOO_LONG`: 255-char string, 254-char valid string (boundary)
- [ ] `INVALID_LOCAL`: leading dot, trailing dot, consecutive dots, 65-char local part
- [ ] `INVALID_DOMAIN`: no TLD, single-char TLD, trailing hyphen on label

**Validation Checklist:**
- [ ] `tsc --noEmit` clean
- [ ] Lint clean
- [ ] All tests pass

**Test Checklist:**
- [ ] ≥20 cases total
- [ ] Each error code triggered by ≥1 test
- [ ] Boundary cases (254, 64, empty label) covered

**Implementation Notes:**
- Using `describe.each` for parameterized cases — mirrors `phone.test.ts`.

**Files Changed:**
- `src/utils/email.test.ts` (created, in progress)

---

### Step 4: Update CHANGELOG and docs

**Status:** PENDING

**Prerequisites:**
- Step 3 complete and passing

**Deliverables:**
- CHANGELOG entry
- `docs/utils.md` section for `validateEmail`

**Plan:**
- [ ] Add entry under `## [Unreleased]` → `### Added`
- [ ] Document signature, error codes, and out-of-scope items (IDN)
- [ ] Link from utils index in docs

**Validation Checklist:**
- [ ] Markdown lint clean
- [ ] Anchor links resolve

**Test Checklist:**
- [ ] Manual: render docs locally, confirm anchor navigation works

**Implementation Notes:**

**Files Changed:**

---

## Implementation Log

| Date | Step | Summary |
|---|---|---|
| 2024-11-04 | Step 1 | Added error codes + result types; barrel export updated |
| 2024-11-05 | Step 2 | Implemented `validateEmail`; all branches reachable |
| 2024-11-05 | Step 3 | Started test suite; happy path + first 2 error codes done |

## Deviations from Original Plan

- **Original Step 2 had a single mega-regex.** Split into `LOCAL_RE` + `DOMAIN_RE` during implementation for readability and to make the `INVALID_LOCAL` vs `INVALID_DOMAIN` branching cleaner. No behavioral change.

## Blocked Steps

| Step | Reason | Date Blocked |
|---|---|---|

---

*Session context tracked in: [`context.md`](context.md).*
*Advisory project memory in [`repo-map.md`](repo-map.md) must be verified before use.*
