# Treasure Bank Code Review Standard

## 1. Purpose
The purpose of this document is to define the standards for conducting code reviews in Treasure Bank.

Code review is a mandatory engineering quality-control process used to ensure that changes are:

* Correct
* Secure
* Maintainable
* Testable
* Architecturally consistent
* Understandable
* Operationally safe

Code review must focus on the quality and correctness of the change, not on personal preferences or superficial stylistic differences already enforced by automated tooling.

---

## 2. Code Review Principles

All code reviews in Treasure Bank must follow these principles:

1. **Correctness before preference**
Reviews must identify defects, incorrect behavior, and meaningful design problems rather than subjective preferences.

2. **Architecture before implementation details**
Reviewers must verify that changes respect established module boundaries and architectural rules.

3. **Security by default**
Security implications must be considered for every change that handles authentication, authorization, financial data, sensitive information, external communication, or persistence.

4. **Automate what can be automated**
Formatting, linting, static analysis, and other mechanically verifiable rules should be enforced by tooling rather than reviewers.

5. **Review the change, not the person**
Feedback must remain objective, respectful, and technically focused.

6. **Every finding requires appropriate action**
Significant defects or risks must be resolved, explicitly accepted, or tracked as follow-up work.

---

## 3. Reviewer Responsibilities

A reviewer is responsible for evaluating the Pull Request sufficiently to determine whether it is safe and appropriate to merge.

The reviewer should evaluate, where applicable:

* Functional correctness
* Business logic
* Architectural consistency
* Module boundaries
* Code readability
* Maintainability
* Error handling
* Security
* Performance
* Concurrency
* Transactional behavior
* Data integrity
* Test coverage
* Observability
* Dependency impact
* API compatibility
* Database impact
* Operational implications

The reviewer must not approve a change merely because:

* The code compiles.
* CI passes.
* Tests pass.
* Another reviewer has approved it.
* The implementation appears to work in the happy path.

---

## 4. Review Scope

The reviewer must review the complete relevant change.

This includes:

* Production code
* Tests
* Configuration
* Database migrations
* Dependency changes
* CI/CD changes
* Docker configuration
* Documentation
* Infrastructure-related changes

The reviewer should also consider how the change interacts with existing functionality rather than reviewing each changed file in isolation.

---

## 5. Correctness Review

The first responsibility of a code review is determining whether the implementation is correct.

Reviewers should verify:

* Requirements are correctly implemented.
* Business rules are correctly represented.
* Edge cases are handled.
* Invalid input is handled appropriately.
* Failure paths behave correctly.
* State transitions are valid.
* Exceptions are handled appropriately.
* Existing behavior is not unintentionally broken.
* Concurrent execution does not introduce incorrect behavior where relevant.

For financial operations, reviewers must pay particular attention to:

* Transaction boundaries
* Idempotency
* Duplicate processing
* Atomicity
* Consistency
* Precision of monetary calculations
* Authorization
* Auditability
* Race conditions

---

## 6. Architectural Review

Every change must respect the established Treasure Bank architecture.

Reviewers should verify:

* The correct module owns the functionality.
* Module boundaries are preserved.
* Dependencies flow in the approved direction.
* Internal implementation details are not unnecessarily exposed.
* Cross-module communication follows approved mechanisms.
* Domain responsibilities remain appropriately separated.
* Infrastructure concerns do not leak into inappropriate modules.
* The change does not introduce unnecessary coupling.

A Pull Request that solves a problem by violating an architectural boundary should not be approved simply because the implementation works.

---

## 7. Design Review

Reviewers should evaluate whether the implementation is appropriately designed for the problem.

Consider:

* Separation of concerns
* Cohesion
* Coupling
* Abstraction
* Encapsulation
* Extensibility
* Reusability
* Complexity
* Appropriate use of design patterns

Reviewers should avoid demanding abstraction simply because abstraction is possible.

The simplest design that correctly satisfies the requirement should generally be preferred.

---

## 8. Code Readability

Code must be understandable to another engineer without requiring unnecessary investigation.

Reviewers should look for:

* Meaningful names
* Clear control flow
* Appropriate method/class size
* Reasonable complexity
* Consistent terminology
* Appropriate comments
* Avoidance of unnecessary cleverness
* Clear error handling

Comments should explain why something is necessary when the reason is not obvious from the code.

Comments should not merely restate what the code already expresses.

---

## 9. Maintainability

Reviewers must consider the long-term cost of the change.

The implementation should:

* Be easy to modify.
* Avoid unnecessary duplication.
* Minimize accidental coupling.
* Follow established project conventions.
* Avoid premature optimization.
* Avoid unnecessary abstractions.
* Avoid introducing technical debt without justification.

If technical debt is intentionally introduced, the Pull Request should document the reason and, where appropriate, create a follow-up issue.

---

## 10. Security Review

Security must be considered whenever applicable.

Reviewers should look for:

* Authentication weaknesses
* Authorization failures
* Improper access control
* Sensitive-data exposure
* Insecure input handling
* Injection vulnerabilities
* Credential exposure
* Insecure configuration
* Unsafe deserialization
* Improper error disclosure
* Insecure external communication
* Logging of sensitive information

Sensitive information must never be committed to the repository.

Examples include:

* Passwords
* API keys
* Access tokens
* Private keys
* Database credentials
* Production secrets

Security-sensitive changes may require review by an appropriately designated reviewer in addition to the normal review process.

---

## 11. Financial and Data Integrity Review

Because Treasure Bank handles financial operations, code affecting financial state requires additional scrutiny.

Reviewers should verify:

* Monetary values use appropriate representations.
* Calculations do not introduce unintended precision loss.
* Transactions preserve required atomicity.
* Duplicate requests are handled appropriately.
* State transitions are valid.
* Database constraints are respected.
* Rollbacks behave correctly.
* Audit information is preserved where required.
* Concurrent operations cannot corrupt financial state.

For financial functionality, "works in the normal case" is insufficient evidence for approval.

---

## 12. Test Review

Reviewers must evaluate whether the tests adequately demonstrate the correctness of the change.

Tests should:

* Verify expected behavior.
* Cover meaningful edge cases.
* Cover failure scenarios where appropriate.
* Verify important business rules.
* Be deterministic.
* Be maintainable.
* Avoid testing implementation details unnecessarily.

The reviewer should ask:

> "What could break in this change that the current tests would fail to detect?"

Tests should provide confidence rather than merely increase coverage percentages.

---

## 13. Test Coverage Expectations

There is no requirement that every line of code receive identical test coverage.

Testing effort should be proportional to risk.

Higher-risk areas should receive stronger test coverage, including:

* Financial transactions
* Authentication
* Authorization
* Account state changes
* Persistence
* External integrations
* Concurrency
* Security-sensitive operations

Low-risk changes may require significantly less testing.

The objective is meaningful behavioral coverage, not a specific coverage number alone.

---

## 14. Error Handling Review

Reviewers should verify that errors are handled intentionally.

Consider:

* Appropriate exception types
* Meaningful error responses
* Correct transaction rollback behavior
* No accidental information disclosure
* Proper logging
* Appropriate recovery behavior
* Preservation of the original failure context

Errors should not be silently swallowed.

Broad exception handling should be justified where used.

---

## 15. Performance Review

Performance should be considered when the change could materially affect system behavior.

Reviewers should look for:

* Unnecessary database queries
* N+1 query patterns
* Excessive memory usage
* Inefficient algorithms
* Unbounded operations
* Blocking operations in inappropriate contexts
* Excessive network calls
* Missing pagination
* Unnecessary object creation

Performance optimization should be evidence-driven.

Premature optimization must not unnecessarily increase complexity.

---

## 16. Concurrency Review

Changes involving concurrent execution must be reviewed for thread safety and race conditions.

Reviewers should consider:

* Shared mutable state
* Synchronization
* Transaction isolation
* Race conditions
* Duplicate processing
* Idempotency
* Locking behavior
* Deadlocks
* Thread-pool usage
* Asynchronous execution

Concurrency concerns must receive additional scrutiny when they affect financial state or transaction processing.

---

## 17. Database and Persistence Review

Changes affecting persistence should be reviewed for:

* Schema correctness
* Migration safety
* Indexing
* Constraints
* Transaction boundaries
* Query correctness
* Data integrity
* Backward compatibility
* Migration rollback implications
* Performance implications

Database migrations must be reviewed as part of the Pull Request and not treated as separate from the application change.

---

## 18. API Review

Changes to APIs should be reviewed for:

* Request/response correctness
* Validation
* Authentication
* Authorization
* Error responses
* Backward compatibility
* Naming consistency
* HTTP semantics
* Idempotency where applicable
* Documentation

Breaking API changes must be explicitly identified and reviewed.

---

## 19. Dependency Review

When dependencies are added, removed, or upgraded, reviewers should verify:

* The dependency is necessary.
* The selected version is appropriate.
* Compatibility with Java 25+ and Spring Boot 4.1.x is maintained.
* The dependency does not introduce unnecessary functionality.
* Security implications have been considered.
* Licensing implications are acceptable.
* The dependency is appropriately scoped.

Dependency changes must comply with the project's Dependency Management standards.

---

## 20. Review Comments

Review comments must be:

* Clear
* Specific
* Constructive
* Technically justified
* Actionable when action is required

Good:
```text
This operation can be executed concurrently and may create
duplicate transactions. Please make the operation idempotent
or enforce uniqueness at the persistence boundary.
```
Poor:
```text
This looks wrong.
```
Reviewers should explain why a change is required when the reason is not obvious.

---

## 21. Comment Severity

Review comments should communicate their importance clearly.

Treasure Bank should distinguish between:

* Blocking — The issue must be resolved before merge.
* Important — The issue should normally be resolved before merge but may be discussed.
* Suggestion — An improvement that is not required for correctness.
* Question — Clarification is required before the reviewer can confidently evaluate the implementation.

Reviewers should not mark minor preferences as blocking issues.

---

## 22. Avoiding Nitpicking

Reviewers should not block a Pull Request over issues that are:

* Already enforced automatically.
* Purely subjective.
* Irrelevant to the change.
* Inconsequential to maintainability.
* Outside the scope of the Pull Request.

If a concern is genuinely useful but does not need to block the current change, it should be expressed as a suggestion or converted into a follow-up issue.

---

## 23. Review Resolution

Every blocking or substantive review comment must be resolved before merging.

Resolution may occur through:

* Implementing the requested change.
* Providing a technically justified alternative.
* Reaching agreement that the original concern does not apply.
* Creating a follow-up issue when explicitly agreed that the work can be deferred.

A reviewer must not approve a Pull Request while intentionally leaving a known critical defect unresolved.

---

## 24. Reviewer Independence

Code review should provide independent engineering judgment.

Where practical:

* The author should not be the sole approver.
* Reviewers should not approve code they have not meaningfully reviewed.
* Approval should be based on the final relevant state.
* Significant changes after approval may require another review.

For high-risk changes, additional reviewers should be assigned where appropriate.

---

## 25. Review Order

Reviewers should generally evaluate a change in the following order:

1. Intent — What problem is being solved?
2. Architecture — Is the change located in the correct place?
3. Correctness — Does it actually solve the problem?
4. Security — Does it introduce vulnerabilities or exposure?
5. Data integrity — Can it corrupt or incorrectly modify state?
6. Testing — Is the behavior adequately verified?
7. Maintainability — Can the implementation be understood and changed safely?
8. Performance — Does it introduce meaningful performance risks?
9. Style — Are there remaining human-readable consistency concerns?

This order prevents reviewers from spending significant time on minor details before discovering a fundamental architectural or correctness problem.

---

## 26. Review of Large Pull Requests

Large Pull Requests should receive additional scrutiny.

For large changes, reviewers should:

* Understand the overall objective first.
* Review architectural changes before implementation details.
* Break the review into logical sections.
* Verify migration and compatibility implications.
* Request decomposition when the change can safely be split.

If a Pull Request is too large to review effectively, the preferred solution is to divide the work rather than reduce review quality.

---

## 27. Review Time and Responsiveness

Code reviews should be performed in a timely manner appropriate to the priority of the work.

Reviewers should avoid unnecessary delays once a Pull Request is ready for review.

Authors should also respond to review comments in a reasonable timeframe.

The objective is to maintain development flow without sacrificing review quality.

---

## 28. Self-Review Before Requesting Review

Before requesting review, the author must perform a self-review.

The author should:

* Read the complete diff.
* Remove unrelated changes.
* Verify tests.
* Check for accidental configuration changes.
* Check for secrets.
* Review error handling.
* Confirm documentation requirements.
* Confirm issue traceability.
* Verify that the implementation matches the stated objective.

A self-review is not a substitute for independent review.

---

## 29. Review Checklist

Reviewers should use the following checklist where applicable:

```text
- [ ] I understand the purpose of the change.
- [ ] The implementation satisfies the stated requirement.
- [ ] The correct module owns the functionality.
- [ ] Architectural boundaries are preserved.
- [ ] Business logic is correct.
- [ ] Edge cases are handled.
- [ ] Error handling is appropriate.
- [ ] Security implications have been considered.
- [ ] Financial/data integrity has been considered where applicable.
- [ ] Tests adequately verify the change.
- [ ] Database changes are safe where applicable.
- [ ] API compatibility has been considered where applicable.
- [ ] Dependency changes are justified where applicable.
- [ ] Performance implications have been considered where applicable.
- [ ] No sensitive information is exposed.
- [ ] The implementation is maintainable.
- [ ] Blocking review comments have been resolved.
- [ ] I am confident the Pull Request is safe to merge.
```

---

## 30. Governing Principle

> Every code review must provide independent, technically justified confidence that a change is correct, secure, maintainable, architecturally sound, and safe to integrate into Treasure Bank.

Code review is not a ceremony performed to satisfy a process requirement. It is an engineering control that protects the quality and long-term integrity of the system.

---