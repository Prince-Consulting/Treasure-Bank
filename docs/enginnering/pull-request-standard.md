# Treasure Bank Pull Request Standard

## 1. Purpose
The purpose of this document is to define the standards for creating, reviewing, approving, and merging Pull Requests (PRs) in Treasure Bank.

Pull Requests are the primary mechanism for introducing changes into protected branches. They provide a controlled process for:

* Code review
* Automated validation
* Architectural oversight
* Issue traceability
* Knowledge sharing
* Quality assurance
* Safe integration

Every Pull Request must provide sufficient information for another engineer to understand what changed, why it changed, and how the change was validated.

---

## 2. Pull Request Requirements

All changes targeting `main` must be introduced through a Pull Request.

A Pull Request must:

* Originate from an appropriate development branch.
* Represent one logical unit of work.
* Reference the relevant GitHub issue where applicable.
* Have a clear and descriptive title.
* Provide sufficient context in its description.
* Pass all required CI checks.
* Satisfy required review requirements.
* Contain only changes relevant to the stated objective.
* Be reviewed before merging.
* Use the approved repository merge strategy.

Direct changes to `main` are prohibited unless an explicitly documented emergency procedure applies.

---

## 3. Pull Request Title

Pull Request titles must follow the same Conventional Commits convention used by the repository.

Format:
```text
<type>(<scope>): <description>
```
Examples:
```text
feat(account): add account creation workflow
fix(transaction): prevent duplicate transaction processing
refactor(customer): extract customer validation service
docs(architecture): document module boundaries
ci(github): add dependency vulnerability scanning
```
The title should be concise and accurately describe the overall change represented by the Pull Request.

The Pull Request title should not simply repeat the GitHub issue title if that title does not accurately describe the implementation.

---

## 4. Pull Request Description

Every Pull Request must contain a meaningful description.

The description should communicate:

* What was changed.
* Why the change was required.
* How the change was implemented at a useful level.
* How the change was tested or validated.
* Any relevant risks, limitations, or follow-up work.

A Pull Request description should provide enough context for a reviewer to understand the change without having to reconstruct the entire development process from the commit history.

---

## 5. Pull Request Description Structure

Treasure Bank Pull Requests should use a consistent structure.

Recommended structure:
```text
## Summary

Briefly describe what this Pull Request changes and why.

## Changes

- Change 1
- Change 2
- Change 3

## Testing

- Test or validation performed
- Test or validation performed

## Related Issue

Closes #123

## Notes

Additional information, risks, limitations, or follow-up work.
```

Not every section must contain extensive information. The description should remain proportional to the complexity of the change.

---

## 6. Issue Traceability

Every Pull Request should be associated with the GitHub issue it implements whenever the work originates from an issue.

Where appropriate, use GitHub closing keywords such as:

```text
Closes #123
```
or:
```text
Fixes #123
```
This allows GitHub to automatically associate the Pull Request with the issue and close the issue after successful merging.

The issue reference must not replace a meaningful Pull Request description.

---

## 7. Scope of a Pull Request

A Pull Request should represent one logical unit of work.

A Pull Request should not combine unrelated changes merely because they happen to be ready at the same time.

Avoid

A Pull Request titled:
```text
feat(account): add account creation workflow
```
containing:

* Account creation
* Transaction processing
* Docker restructuring
* Dependency upgrades
* Unrelated documentation changes

Instead, unrelated changes should be submitted through separate Pull Requests.

Focused Pull Requests are easier to:

* Review
* Test
* Approve
* Revert
* Debug
* Merge

---

## 8. Pull Request Size

Pull Requests should be kept reasonably small and focused.

Large Pull Requests are permitted when the nature of the change requires them, particularly for:

* Major architectural changes
* Large migrations
* Significant framework upgrades
* Foundational infrastructure work

However, large Pull Requests should not result from combining unrelated work.

When a large change can be safely divided into smaller independently useful changes, it should be.

The objective is not to enforce an arbitrary line-count limit but to maximize review quality and change comprehension.

---

## 9. Code Quality Requirements

Before requesting review, the author must ensure that the Pull Request satisfies applicable project quality requirements.

Depending on the change, these may include:

* Formatting
* Linting
* Static analysis
* Unit tests
* Integration tests
* Architecture tests
* Build verification
* Dependency validation
* Security scanning
* Documentation updates

The Pull Request author is responsible for ensuring that the branch is ready for review.

---

## 10. CI Requirements

Required CI checks must pass before a Pull Request can be merged.

CI should validate, as applicable:

* Project compilation
* Unit tests
* Integration tests
* Code formatting
* Linting
* Static analysis
* Dependency security
* Architecture rules
* Build integrity
* Container/image validation where applicable

A failed required CI check must prevent merging until the failure has been resolved or explicitly handled according to the project's documented exception process.

Passing CI does not replace human code review.

---

## 11. Reviewer Requirements

Reviewers are responsible for evaluating more than whether the code compiles.

A review should consider, where applicable:

* Correctness
* Readability
* Maintainability
* Design quality
* Architectural consistency
* Security
* Performance
* Error handling
* Test coverage
* Observability
* Dependency impact
* Backward compatibility
* Operational implications

Reviewers should focus on meaningful engineering concerns rather than personal stylistic preferences that are already addressed by automated tooling.

---

## 12. Review Comments

Review comments should be:

* Specific
* Constructive
* Technically justified
* Relevant to the change
* Actionable where an action is required

A reviewer should explain the reasoning behind significant requested changes.

For example:
```text
This should be moved behind the transaction module boundary because
the current implementation exposes transaction persistence concerns
to the account module.
```
rather than:
```text
I don't like this.
```

---

## 13. Review Resolution

All review comments that require action must be addressed before the Pull Request is merged.

A comment may be resolved when:

* The requested change has been implemented.
* The concern has been technically addressed in another way.
* The reviewer and author agree that no change is necessary.
* The concern has been converted into a tracked follow-up issue where appropriate.

Unresolved substantive review concerns must not be ignored merely to obtain approval.

---

## 14. Approval Requirements

The repository must define the minimum number of required approvals through GitHub branch protection.

At minimum:

* The Pull Request must receive the required approvals.
* The author must not approve their own Pull Request as a substitute for independent review.
* Required reviewers must review the final relevant state of the Pull Request.
* Significant changes made after approval may require re-review.

For architectural or security-sensitive changes, additional review may be required even when the minimum branch-protection approval requirement has already been satisfied.

---

## 15. Author Responsibilities

The Pull Request author is responsible for:

* Creating a focused Pull Request.
* Providing a clear title.
* Providing a complete description.
* Linking the relevant issue.
* Ensuring applicable tests are present.
* Running appropriate local validation.
* Responding to review comments.
* Keeping the branch reasonably up to date.
* Resolving merge conflicts.
* Ensuring CI passes.
* Updating documentation where necessary.
* Ensuring the final Pull Request accurately represents the intended change.

Opening a Pull Request does not mean the work is automatically ready for review. The author must ensure it is reviewable.

---

## 16. Reviewer Responsibilities

The reviewer is responsible for:

* Understanding the purpose of the change.
* Reviewing the complete relevant diff.
* Evaluating correctness and design.
* Identifying significant risks.
* Verifying that tests adequately validate the change.
* Checking architectural boundaries where relevant.
* Reviewing security implications where applicable.
* Clearly communicating requested changes.
* Approving only when the Pull Request satisfies the required standards.

Reviewers should not approve changes they do not understand sufficiently.

---

## 17. Draft Pull Requests

Draft Pull Requests should be used when work is not yet ready for formal review but early feedback is valuable.

Draft Pull Requests may be used for:

* Early architectural feedback
* Large changes
* Complex implementations
* Collaboration
* Identifying potential problems early

A Draft Pull Request must be converted to a regular Pull Request before it can be merged.

The author must ensure that all normal Pull Request requirements are satisfied before requesting final review.

---

## 18. Pull Request Updates

When substantial changes are made after review, the author should provide a concise summary of what changed.

For example:
```text
Updated transaction validation based on review feedback.
Added integration coverage for duplicate transaction requests.
```
Reviewers should review the updated changes rather than relying solely on their previous review.

---

## 19. Merge Conflicts

The Pull Request author is responsible for resolving merge conflicts before merging.

Conflict resolution must preserve the intended behavior of both the Pull Request and the target branch.

After resolving conflicts, applicable tests and validation must be executed again.

A Pull Request must not be merged merely because the conflict markers have been removed.

---

## 20. Merge Strategy

Treasure Bank should use a controlled merge strategy that preserves a clean and meaningful repository history.

The repository's configured merge strategy should ensure that:

* The resulting history remains understandable.
* The final commit follows the commit convention.
* Temporary development commits do not unnecessarily pollute the primary branch.
* The Pull Request remains traceable to its associated issue.

Where squash merging is used, the resulting commit must comply with the project's Commit Convention.

---

## 21. Pull Request Review Checklist

Before requesting final review, the author should verify:
```text
- [ ] The Pull Request represents one logical change.
- [ ] The title follows the commit convention.
- [ ] The relevant GitHub issue is referenced.
- [ ] The description explains what and why.
- [ ] Appropriate tests have been added or updated.
- [ ] Local validation has been completed.
- [ ] CI checks are passing.
- [ ] Documentation has been updated where necessary.
- [ ] No unrelated changes are included.
- [ ] No secrets or sensitive configuration are included.
- [ ] Review comments have been addressed.
- [ ] Merge conflicts have been resolved.
```

---

## 22. Emergency Pull Requests

Emergency changes may follow an expedited review process when required to address a critical production or security issue.

An emergency Pull Request must still:

* Clearly document the problem.
* Identify the reason for expedited handling.
* Pass all practical automated validation.
* Receive appropriate review as quickly as possible.
* Include follow-up work where normal engineering practices could not be completed during the emergency.

Emergency procedures must not become the normal development workflow.

---

## 23. Governing Principle

> **Every Pull Request must make it easy for another engineer to understand, validate, review, and safely integrate one intentional change into Treasure Bank.

The Pull Request process is a quality-control mechanism, not merely a mechanism for merging code.

---

