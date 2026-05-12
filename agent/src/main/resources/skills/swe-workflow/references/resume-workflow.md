# Resume Workflow

## Contract — Read This First

1. Memory is unreliable. Files + current-workspace verification are truth.
2. Read in order: `plans/context.md` → `plans/<task>.md` → `plans/repo-map.md` (if exists).
3. Confirm `Open Questions` is `None.` — if not, ask in chat before resuming.
4. Find resume point: IN_PROGRESS → continue after verifying state; PENDING → next step; BLOCKED → note reason; all COMPLETED → global-reflection.
5. Re-verify target files and prerequisites in the current workspace before editing.
6. Orient the user with a brief summary before continuing.

## Instructions

### Step 1: Discover Existing Plans

```bash
ls -la plans/ 2>/dev/null || echo "No plans directory"
```

If no plans exist, start from require-clarification.

### Step 2: Read Context File

If `plans/context.md` exists, read it completely. Capture: current task/step, completed work, decisions, active files, Working Set, open questions, next actions.

### Step 3: Read the Plan File Completely

Use `read` on `plans/<task-name>.md`. Read all sections: Goal, Assumptions, Open Questions, Design Decisions, Context & Learnings, all steps, Implementation Log.

If `Open Questions` is not `None.`, stop and ask in chat before resuming. If multiple plan files exist, ask user which to resume.

### Step 4: Optionally Read Repo Map

Read `plans/repo-map.md` only if it exists and would help. Verify paths before editing. Trust current workspace over repo-map entries.

### Step 5: Find Resume Point

| Status | Action |
|--------|--------|
| IN_PROGRESS | Read Implementation Notes, verify current state, continue |
| PENDING | This is the next step |
| BLOCKED | Note reason, try next unblocked step if safe |
| All COMPLETED | Proceed to global-reflection |

Cross-check with context file. If they disagree significantly, ask the user.

### Step 6: Verify Prerequisites and Evidence

1. Read the step's Prerequisites.
2. Confirm required files/facts in Working Set or Verified Facts.
3. Re-verify stale or branch-sensitive facts in current workspace.
4. Read target files before editing.

### Step 7: Orient User

```markdown
**Resuming:** [task name]
**Read:** context.md [✓/absent], plans/[task].md [✓], repo-map.md [✓/absent]
**Progress:** Completed [X, Y, Z]. Current: Step N — [title]. Remaining: [N steps].
**Key facts:** [verified decision or fact]
Continue with Step N, or adjust plan?
```

### Step 8: Resume Execution

Continue with execute-step. One step IN_PROGRESS, stay within plan, verify before editing.

## Mandatory Checklist

| Check | Why |
|-------|-----|
| Context read or confirmed absent | Know where work stopped |
| Plan read completely | Know full scope |
| Open Questions is `None.` | No unresolved requirements |
| Design Decisions reviewed | Know confirmed choices |
| Resume point identified | Know what's next |
| Working Set and Verified Facts reviewed | No hallucinated paths/APIs |
| Prerequisites verified in current workspace | No stale assumptions |
| User oriented | No drift |

## Edge Cases

| Situation | Action |
|---|---|
| Context missing, plan exists | Read plan; treat as fresh resume |
| Context and plan disagree | Trust plan for status, context for recent work; ask if ambiguous |
| Repo map disagrees with files | Trust current workspace; update Verified Facts |
| Multiple plan files | Ask user which to resume |
| Plan looks stale | Ask: continue, update, or start fresh? |

## Next Step

→ **execute-step** (next PENDING or IN_PROGRESS step), or **require-clarification** if no plan exists.
