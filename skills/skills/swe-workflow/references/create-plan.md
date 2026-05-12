# Create Plan

## Contract — Read This First

1. Clarification must be complete — no unresolved questions. If any remain, stop and ask in chat.
2. Open Questions must be exactly `None.` — do not create a plan with unresolved questions.
3. Explore narrowly — read only files needed to plan; record evidence in Working Set and Verified Facts.
4. Discover design conventions — if the task touches UI, schema, or API, search for existing patterns, shared components, and reusable code before planning. Record in Verified Facts.
5. Fill the Design Decisions table — surface every UX, schema, or API choice for user confirmation. Write `None — no design-sensitive changes.` if N/A.
6. Break into vertical slices — each step is one thin slice through all layers (types, logic, tests), independently verifiable. Never horizontal.
7. Bullet quality — every plan bullet must name a tool (`edit`/`write`/`bash`), a file path, and the specific change. If a bullet doesn't reference a concrete location, it's too vague.
8. Reuse before create — search for existing shared code before planning to create new components, utilities, or patterns. Evidence in Verified Facts.
9. Write ALL steps — the plan file must contain every step for every phase. Partial plans block execution.
10. Copy [`plan-template.md`](plan-template.md) verbatim — do not invent your own format.

## Instructions

1. **Verify clarification is complete** — if any open questions remain, go back to require-clarification. Do not create, finalize, or present a plan with unresolved questions.

2. **Define the plan header:**
   - **Goal:** one sentence describing what we're building
   - **Assumptions:** what we're taking for granted
   - **Open Questions:** exactly `None.`. If anything else would go here, stop and ask the user.
   - **Design Decisions:** fill the table or write `None — no design-sensitive changes.`

3. **Explore the codebase narrowly:**
   - Search for relevant files and directories with targeted `rg`/`find`/`git` commands
   - Read only files needed to plan: likely targets, direct callers/callees, nearby tests, relevant config
   - **Design convention discovery** (when task touches UI, schema, or API):
     - Frontend: search for existing shared/reusable components, design tokens, layout patterns
     - Schema: search for naming conventions, relationship patterns, migration style
     - API: search for route naming, response shape, error format
   - **Reuse check:** before planning to create anything new, search for existing equivalents. Record evidence ("no match found" or "existing X can be extended").
   - Record all evidence in the plan's Working Set and Verified Facts
   - If `plans/repo-map.md` exists, treat it as advisory; verify entries before relying on them

4. **Break work into ordered vertical-slice steps:**
   - Each step is ONE discrete action (5-15 minutes of work)
   - Each step is a thin slice through all layers (types, logic, tests) — independently verifiable
   - Steps must be ordered by dependency
   - Each step includes: title, prerequisites, deliverables, plan bullets, quality checklist, validation checklist, test checklist
   - All steps start as **PENDING**
   - Do not add a step whose purpose is to resolve open questions

5. **Define prerequisites and deliverables for each step:**
   - **Prerequisites:** What must be true (step deps, files to modify, design confirmations needed)
   - **Deliverables:** What this step produces + "After this step: [observable outcome]"

6. **Create the plans directory:** `mkdir -p plans/`

7. **Use advisory repo-map.md only when helpful** — add only durable project conventions, not exhaustive inventories.

8. **Derive the plan filename** — kebab-case from the feature description (e.g. `plans/add-user-auth.md`).

9. **Write the plan file — ALL steps:**
   - Copy [`plan-template.md`](plan-template.md) verbatim
   - ALL phases and ALL steps before any execution begins
   - A plan file missing later phases/steps is incomplete and blocks execution

10. **Populate Working Set and Verified Facts:**
    - Add each target file with evidence used to verify it
    - Record facts affecting implementation: test style, dependency availability, import patterns, callers, config
    - Keep task-local; do not create an exhaustive repo inventory

11. **Verify the plan:**
    - `read` the file — confirm Goal, Assumptions, Open Questions (`None.`), Design Decisions, Working Set, Verified Facts, Steps
    - Verify every non-trivial assumption is answered, recorded, or evidence-backed

12. **Report:** `Plan created. Open questions: none. Ready to proceed with Step 1?` — if you cannot truthfully say this, the plan is invalid.

## Mandatory Outputs

- [ ] Plan file at `plans/<feature-name>.md`
- [ ] Goal, Assumptions, Open Questions (`None.`), Design Decisions (filled or `None`)
- [ ] Working Set with evidence, Verified Facts with tool citations
- [ ] Each step has all 8 fields: Status, Prerequisites, Deliverables, Plan, Quality Checklist, Validation Checklist, Test Checklist, Files Changed
- [ ] ALL steps for ALL phases present; steps ordered by dependency
- [ ] Every plan bullet names a tool + file path + specific change
- [ ] No code written yet

## Constraints

- **Do NOT write code** in this phase — plans only
- **Do NOT skip to implementation** — plan must exist first
- **Do NOT write a plan with unresolved questions** — ask in chat first
- **Do NOT use DRAFT as a loophole** — DRAFT requires completed clarification
- **Do NOT write a partial plan** — ALL steps for ALL phases required
- **Do NOT invent your own format** — copy plan-template.md verbatim
- **Do NOT create new code without a reuse check** — search for existing equivalents first; evidence in Verified Facts
- **Do NOT make silent design choices** — surface in Design Decisions table for user confirmation
- If you can't break it into steps, the scope is too vague — go back to clarification

### Common Scope Traps

Before writing the plan, grep for any value that must stay consistent across files:

- **Version bumps:** grep current version across SKILL.md, package.json, CHANGELOG.md, README.md
- **Renames:** grep old name; include every caller in the plan
- **Schema/column changes:** grep in migrations, models, raw SQL, type definitions
- **Config keys:** grep in `.env.example`, `config.*`, docs, tests

If grep surfaces unexpected hits, fold them into the plan or note them as out of scope in Assumptions.

## Next Step

→ Proceed to: **execute-step** (implement the first PENDING step)
