# Treasure Bank Commit Convention

## 1. Purpose

The purpose of this document is to define the commit message convention for Treasure Bank.
A consistent commit convention ensures that the Git history remains:

* Clear
* Consistent
* Searchable
* Auditable
* Easy to review
* Suitable for automation
* Useful for future maintenance and troubleshooting

Treasure Bank will use the Conventional Commits specification as the foundation of its commit convention.

---

## 2. Commit Message Structure

All commits must follow the following structure:
```text
<type>(<scope>): <description>
```
When a meaningful scope cannot be identified:
```text
docs: update development setup instructions
```
For commits requiring additional context:
```text
fix(transaction): prevent duplicate transaction processing

Transaction requests can be retried by clients when network failures
occur. Add idempotency validation before transaction persistence so
retries cannot create duplicate financial transactions.
```

---

## 3. Commit Types

The following commit types are approved for Treasure Bank.

| Type | Description |
|---|---|
| `feat` | Introduces a new capability or feature |
| `fix` | Corrects a defect or incorrect behavior |
| `docs` | Changes documentation only |
| `test` | Adds or modifies tests without changing production behavior |
| `refactor` | Changes internal structure without changing intended behavior |
| `perf` | Improves performance without changing intended behavior |
| `build` | Changes dependencies, build configuration, or build tooling |
| `ci` | Changes CI/CD configuration or automation |
| `chore` | General maintenance that does not fit another category |
| `revert` | Reverts a previous commit |

### Examples

```text
feat(account): add account creation workflow
fix(auth): reject expired access tokens
docs(architecture): document module boundaries
test(customer): add customer validation tests
refactor(transaction): extract transaction validation service
perf(account): optimize account lookup
build(dependencies): update Spring Boot dependencies
ci(github): add dependency vulnerability scanning
chore(project): update Maven Wrapper
revert: revert transaction validation change
```

---

## 4. Commit Scope

The scope identifies the part of Treasure Bank affected by the commit.

Examples include:
```text
account
customer
transaction
auth
security
database
notification
architecture
dependencies
docker
ci
```
Examples:
```text
feat(account): add account creation workflow
fix(transaction): prevent duplicate transaction processing
test(auth): add token validation tests
docs(architecture): document module boundaries
```
Scopes should represent meaningful system modules, components, or project areas.

The project should avoid unnecessarily granular scopes.

When a change affects several unrelated areas and no meaningful common scope exists, the scope may be omitted.

---

## 5. Commit Description

The description must:

* Be concise.
* Clearly describe the change.
* Use the imperative mood.
* Avoid unnecessary punctuation.
* Remain understandable without inspecting the code diff.

**Preferred**

```text
feat(account): add account creation workflow
```
```text
fix(transaction): reject duplicate transaction requests
```
**Avoid**
```text
feat(account): added some stuff
```
```text
fix(transaction): fix bug
```
```text
feat(account): made changes to account
```
The description should communicate what the commit does, rather than what the developer happened to do.

---

## 6. Commit Body

The commit body is optional.

It should be used when additional context is valuable or when the reason for a change cannot be adequately communicated by the subject.

A commit body is particularly useful for:

* Architectural decisions
* Non-obvious implementation decisions
* Complex defect fixes
* Security-related changes
* Workarounds
* Changes with important operational consequences

**Example**
```text
fix(transaction): prevent duplicate transaction processing

Transaction requests can be retried by clients when network failures
occur. Add idempotency validation before transaction persistence so
retries cannot create duplicate financial transactions.
```
The body should primarily explain why the change was necessary.

---

## 7. Breaking Changes

Breaking changes must be explicitly identified.

Treasure Bank should use the Conventional Commits ! notation where appropriate.

**Example**
```text
feat(api)!: change account response structure
```
Alternatively, the `BREAKING CHANGE` footer may be used:
```text
feat(api): change account response structure

BREAKING CHANGE: account identifiers are now returned as UUID values.
```
Breaking changes require additional review because they may affect:

* API consumers
* Module contracts
* Database compatibility
* External integrations
* Deployment procedures
* Migration requirements

Breaking changes must never be hidden inside an ordinary commit.

---

## 8. One Logical Change Per Commit

A commit should represent one coherent logical change.

Unrelated changes should not be combined into a single commit.

**Avoid**
```text
feat(account): add account creation

Also:
- update Docker configuration
- modify unrelated documentation
- rename unrelated classes
- upgrade dependencies
```
Instead, separate unrelated work into independent commits.

This improves:

* Code review
* Debugging
* git bisect
* Rollbacks
* Cherry-picking
* Release management
* Historical analysis

---

## 9. Commit Quality

A commit should leave the project in a valid and understandable state whenever practical.

Before committing, applicable validation should have passed, including:

* Formatting
* Linting
* Static analysis
* Unit tests
* Integration tests
* Build validation

Commits must not intentionally contain:

* Debug statements
* Commented-out experimental code
* Credentials
* Passwords
* API keys
* Private keys
* Unnecessary generated files
* Unrelated modifications

---

## 10. Issue Traceability

Where a commit implements or resolves a GitHub issue, the commit should provide traceability to that issue where appropriate.

**Reference an issue**
```text
feat(account): implement account creation

Refs #42
```
**Close an issue**
```text
feat(account): implement account creation

Closes #42
```
The commit message must remain meaningful without the issue number.

**Avoid**
```text
feat: #42
```
The issue reference should supplement the commit description rather than replace it.

---

## 11. Automated Validation

Commit conventions should be enforced through automation.

Treasure Bank should use:

1. Local validation for immediate developer feedback.
2. CI validation as the authoritative enforcement mechanism.

Local Git hooks may be used to detect invalid commit messages before commits are pushed.

CI must independently validate commit messages because local hooks can be bypassed.

A commit-message validation failure must cause the relevant CI quality gate to fail.

---

## 12. Pull Requests and Squashing

Pull request workflows must preserve a clean and meaningful Git history.

During development, temporary commits may occur:
```text
fix: typo
fix: tests
fix: review comments
```
These development commits do not necessarily need to remain in the final repository history.

When commits are squashed before merging, the resulting commit must comply with the Treasure Bank commit convention.

The final commit should describe the complete logical change represented by the pull request.

---

## 13. Commit Amendments and History Rewriting

Local commits may be amended or rewritten before they are shared.

Once commits have been pushed to a shared branch, history rewriting should generally be avoided unless explicitly permitted by the repository workflow.

Protected branches, particularly `main`, must not depend on force-pushed history.

---

## 14. Commit Convention Examples
**Feature**

```text
feat(account): add account creation workflow
```
**Bug Fix**
```text
fix(transaction): prevent duplicate transaction processing
```
**Documentation**
```text
docs(architecture): document modular monolith boundaries
```
**Testing**
```text
test(account): add account creation integration tests
```
**Refactoring**
```text
refactor(customer): extract customer validation service
```
**Dependency Update**
```text
build(dependencies): update Spring Boot dependencies
```
**CI**
```text
ci(github): add dependency vulnerability scanning
```
**Maintenance**
```text
chore(project): update Maven Wrapper
```
**Breaking Change**
```text
feat(api)!: change account response structure
```

---

## 15. Governing Principle

> **Every commit must clearly communicate one intentional change and provide enough context for another engineer to understand why that change exists.

A good Treasure Bank Git history should allow an engineer to determine:

* What changed
* Why it changed
* Which part of the system was affected
* What type of change was made
* Which GitHub issue motivated the change, where applicable

The commit history is considered part of the project's engineering documentation and must therefore be maintained to a professional standard.

---


