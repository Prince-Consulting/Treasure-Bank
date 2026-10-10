# Treasure Bank Branching Conventions

## 1. Purpose

The purpose of this document is to define the branching strategy and branch naming conventions for Treasure Bank.

A consistent branching strategy ensures that development work remains:

* Organized
* Isolated
* Traceable
* Reviewable
* Safe to integrate
* Suitable for CI/CD automation

Treasure Bank will use a short-lived feature branch workflow built around the protected `main` branch.

---

## 2. Main Branch

The `main` branch is the primary and protected branch of the repository.

It represents the most stable and integration-ready state of Treasure Bank.

The following rules apply:

* Direct commits to `main` are prohibited.
* Changes must be introduced through Pull Requests.
* Required CI checks must pass before merging.
* Required reviews must be completed before merging.
* Force pushes to `main` are prohibited.
* Branch deletion or history rewriting must not compromise the integrity of `main`.
* `main` should remain in a buildable state.

The exact branch protection rules are defined by the repository's GitHub configuration and CI/CD requirements.

---

## 3. Branch Naming Convention
Branch names must follow:
```text
<type>/<short-description>
```
Where appropriate, the GitHub issue number should be included:
```text
<type>/<issue-number>-<short-description>
```
**Examples**
```text
feature/42-account-creation
fix/57-duplicate-transactions
refactor/63-transaction-service
docs/18-architecture-documentation
test/71-account-validation
ci/35-dependency-scanning
build/29-update-spring-boot
chore/44-update-maven-wrapper
```
The preferred format for work associated with a GitHub issue is:
```text
feature/42-account-creation
```
This provides immediate traceability between the branch and the issue.

---

## 4. Branch Naming Rules

Branch names must:

* Use lowercase characters.
* Use hyphens (`-`) to separate words.
* Use forward slash (`/`) to separate the branch type from its description.
* Avoid spaces.
* Avoid special characters.
* Avoid unnecessarily long names.
* Clearly communicate the purpose of the branch.
* Include the GitHub issue number where applicable.

**Preferred**
```text
feature/42-account-creation
fix/57-duplicate-transactions
docs/18-architecture-documentation
```
Avoid
```text
Feature/AddAccountCreation
feature/add_account_creation
feature/Add Account Creation
feature/accountCreation
my-branch
john-feature
temp
test123
```
Branch names should describe the work rather than the developer performing it.

---

## 5. Feature Branches

New functionality must be developed in a dedicated feature branch.

**Format:**
```text
feature/<issue-number>-<description>
```
**Example:**
```text
feature/42-account-creation
```
Feature branches must be created from the latest appropriate `main` branch.

Feature branches should remain focused on the functionality represented by their associated issue.

Unrelated work must not be added to the branch.

---

## 6. Fix and Bugfix Branches

Defect corrections should use either fix or bugfix according to the repository's chosen classification.

**Recommended format:**
```text
fix/<issue-number>-<description>
```
Example:
```text
fix/57-duplicate-transactions
```
The project should prefer `fix` for normal defect corrections to avoid unnecessary duplication between `fix` and `bugfix`.

If `bugfix` is retained, its usage must be clearly distinguished from `fix`.

---

## 7. Hotfix Branches

Hotfix branches are reserved for urgent corrections to critical issues affecting a released or production system.

Format:
```text
hotfix/<issue-number>-<description>
```
Example:
```text
hotfix/91-critical-payment-failure
```
Hotfixes must follow the same review and CI requirements as other changes unless an explicitly documented emergency procedure applies.

Emergency procedures must not become a substitute for normal engineering controls.

---

## 8. Documentation Branches

Documentation-only changes should use:
```text
docs/<issue-number>-<description>
```
Example:
```text
docs/18-architecture-documentation
```
Documentation branches must not contain unrelated production-code changes.

---

## 9. Refactoring Branches

Refactoring work that does not intentionally change externally observable behavior should use:
```text
refactor/<issue-number>-<description>
```
Example:
```text
refactor/63-transaction-service
```
Refactoring branches should avoid combining unrelated features or defect fixes with the refactoring work.

---

## 10. CI and Build Branches

Changes to CI/CD automation should use:
```text
ci/<issue-number>-<description>
```
Example:
```text
ci/35-dependency-scanning
```
Build-system or dependency-related changes should use:
```text
build/<issue-number>-<description>
```
Example:
```text
build/29-update-spring-boot
```
---

## 11. Branch Lifetime

Development branches should be short-lived.

Once the work represented by a branch is complete and the Pull Request has been merged, the branch should be deleted.

Long-lived branches increase the likelihood of:

* Merge conflicts
* Stale dependencies
* Integration problems
* Difficult code reviews
* Divergence from `main`

The preferred workflow is:
```text
main
  │
  └── feature/42-account-creation
          │
          ├── commits
          └── Pull Request
                    │
                    ▼
                  main
                    │
                    └── feature branch deleted
```
---

## 12. Keeping Branches Updated

Developers should regularly synchronize their development branch with main when the branch has remained open long enough for significant changes to accumulate.

The preferred strategy for integrating changes should follow the repository's Git workflow.

Before merging a Pull Request:

* The branch should be based on a sufficiently recent main.
* Merge conflicts should be resolved by the branch owner.
* CI must pass against the final proposed state.
* The Pull Request must remain reviewable.

---

## 13. Branch Isolation

A branch should represent one logical piece of work.

Developers must avoid using one branch for multiple unrelated GitHub issues.

**Avoid**
```text
feature/42-account-creation
```
containing:

* Account creation
* Transaction processing
* Docker changes
* Documentation restructuring
* Dependency upgrades

Instead, create separate branches for independent work.

This improves:

* Reviewability
* Traceability
* Rollback capability
* Release management
* Parallel development

---

## 14. Pull Request Integration

All changes intended for main must be introduced through a Pull Request unless an explicitly documented emergency procedure applies.

A Pull Request should:

* Reference the relevant GitHub issue.
* Contain only the intended changes.
* Pass required CI checks.
* Receive required reviews.
* Satisfy repository quality gates.
* Use the project's approved merge strategy.

The branch must not be merged merely because the code compiles. All applicable project quality requirements must be satisfied.

---

## 15. Branch Protection

The `main` branch must be protected through GitHub repository settings.

At minimum, protection should enforce:

* Pull Request-based changes.
* Required CI checks.
* Required reviews where configured.
* No force pushes.
* No direct pushes by default.

Additional branch protection rules may be introduced as the project matures.

---

## 16. Branch Deletion

Merged development branches should be deleted after successful integration.

Branches that are no longer required should not remain indefinitely in the repository.

The `main` branch must never be deleted as part of normal development.

If a branch must be retained after merging for a specific operational reason, that reason should be documented.

---

## 17. Governing Principle

> **Branches exist to isolate a single logical unit of work temporarily; main exists to represent the stable, integrated state of Treasure Bank.

The branching strategy should minimize long-lived divergence while maximizing:

* Traceability
* Reviewability
* Integration safety
* Development velocity
* Repository maintainability

---

