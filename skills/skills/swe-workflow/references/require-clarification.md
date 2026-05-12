# Require Clarification

## Contract — Read This First

1. Check for existing plans first — if an active plan exists, ask user before starting new work.
2. Read the codebase before asking — don't ask questions you can answer by exploring code.
3. If ANY ambiguity exists, ask — one question at a time, with your recommended answer.
4. Analyze every answer — does it resolve fully? Does it introduce new requirements? New ambiguity?
5. Do NOT proceed with unanswered questions — re-ask explicitly.
6. Summarize and get explicit confirmation before moving to create-plan.
7. Surface design decisions — if the task involves UI, schema, or API choices, identify them now.

## When to Use

- New task or feature request
- Vague or underspecified request
- Multiple interpretations possible
- Tempted to start coding immediately

## Check for Active Plans

```bash
ls plans/*.md 2>/dev/null
```

If a plan exists with IN_PROGRESS steps: remind user, ask whether to continue existing or switch. Do NOT assume abandonment.

## Instructions

1. **Analyze the request** for completeness:
   - Scope defined? (included and excluded)
   - Inputs, outputs clear?
   - Constraints specified? (performance, compatibility, dependencies)
   - Success criteria stated?

2. **Read the codebase first:**
   - Check existing patterns and conventions
   - Look for related functionality and reusable code
   - Understand architecture context
   - Only ask questions you CAN'T answer by reading

3. **Identify design decisions** (when task involves UI, schema, or API):
   - What UX/layout/component choices need to be made?
   - What schema shape, naming, or relationship decisions exist?
   - What API contract choices exist?
   - Present options with your recommendation for each

4. **If ANY ambiguity exists, grill relentlessly:**
   - Walk down each branch of the design tree, resolving dependencies one by one
   - Ask specific, targeted questions — prefer multiple-choice
   - Ask ONE question at a time with your recommended answer
   - WAIT for response before proceeding
   - Keep probing until every branch is resolved

5. **Analyze every answer:**
   - Does it fully resolve the question?
   - Did it introduce new requirements or change scope?
   - Did it add details that need clarification themselves?
   - If new ambiguity → ask follow-up. Only proceed to step 6 when complete.

6. **Summarize and confirm:**
   - State your complete understanding: scope, inputs/outputs, success criteria, key decisions
   - Explicitly state assumptions
   - Include identified design decisions and chosen approaches
   - Ask: "Is this understanding correct? Ready to proceed to planning?"
   - WAIT for explicit confirmation

## Handling Partial Answers

When you ask multiple questions and the user only answers some:

```
Agent: "1. What happens to Channel Digest? 2. Same behavior for Slack and Chat? 3. Task Listener?"
User:  "Channel Digest removed. Auto-replier same behavior."

Agent: "Got it — 1. remove ✓  2. same ✓
        You didn't mention Task Listener — what should happen?
        a) Remove  b) Move  c) Keep as-is"
```

**Do NOT proceed until ALL questions are answered.** Do NOT assume answers for skipped questions.

## Mandatory Checklist

- [ ] All scope boundaries defined
- [ ] Inputs and outputs specified
- [ ] Success criteria clear and testable
- [ ] Design decisions identified and options presented (if applicable)
- [ ] No assumptions about unclear requirements
- [ ] All answers analyzed for scope changes
- [ ] User confirmed understanding explicitly

## Constraints

- **Never assume missing requirements** — ask
- **Never skip to planning with open questions**
- **Read the codebase first** — don't ask what you can discover
- **Analyze every answer** for new requirements or scope changes
- **Must get explicit confirmation** — no confirmation = no plan
- If user says "just do it": acknowledge urgency, flag top 1-2 ambiguity risks
- One question at a time — don't overwhelm

## Next Step

→ **create-plan** — only when all questions resolved and user confirmed.
