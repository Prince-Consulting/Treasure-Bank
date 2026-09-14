# Treasure Bank Linting Standards

## 1. Purpose

This document defines the linting standards for Treasure Bank.

Linting provides automated source-level checks that identify coding practices which violate established engineering conventions or reduce code quality.

The objective is to make common coding mistakes and style violations detectable before code reaches code review or production.

Linting complements, but does not replace:

- Coding Standards.
- Formatting Standards.
- Static Analysis.
- Type Safety.
- Testing.
- Code Review.
- Architectural Compliance.

---

## 2. Scope

These linting standards apply to:

- Production Java code.
- Test Java code.
- Shared code.
- Business modules.
- Infrastructure code.
- Configuration classes.
- Interface/adaptor code.
- Test-support code.

The standard applies consistently across all Treasure Bank modules.

Linting rules must not be selectively weakened merely because code belongs to a particular module.

Where a technical exception is genuinely required, it must be narrowly scoped and documented.

---

## 3. Linting Tool

Treasure Bank uses:

**Checkstyle**

as its primary Java linting tool.

Checkstyle is responsible for deterministic source-level checks such as:

- Naming conventions.
- Import rules.
- Modifier usage.
- Declaration structure.
- Whitespace-related source conventions.
- Documentation requirements where explicitly configured.
- Basic source-code style rules.

The Checkstyle configuration must be version-controlled within the repository.

Developers must not rely on personal Checkstyle configurations.

---

## 4. Relationship to Formatting

Formatting and linting are separate concerns.

The approved formatter is responsible for automatically formatting source code.

Checkstyle is responsible for identifying source-level violations that require developer attention.

The project must avoid configuring Checkstyle to duplicate formatter responsibilities unnecessarily.

For example:

- Formatting determines line wrapping.
- Formatting determines indentation.
- Formatting determines whitespace layout.
- Checkstyle verifies source-level conventions that are not adequately handled by formatting.

The two tools should work together rather than compete.

---

## 5. Linting Principles

Treasure Bank linting rules should be:

- Deterministic.
- Objective.
- Understandable.
- Consistent.
- Automatically enforceable.
- Appropriate for Java 25+.
- Appropriate for Spring Boot applications.
- Compatible with the modular-monolith architecture.
- Valuable enough to justify developer attention.

Linting should identify meaningful problems rather than create excessive noise.

A rule should not be introduced merely because it is technically possible to enforce it.

---

## 6. Rule Categories

The initial linting configuration should cover the following categories.

### 6.1 Naming

Enforce established naming conventions for:

- Classes.
- Methods.
- Variables.
- Parameters.
- Constants.
- Packages.
- Type parameters where applicable.

The authoritative naming rules remain documented in:

`docs/engineering/naming-conventions.md`

Checkstyle provides automated enforcement of those rules where practical.

---

### 6.2 Imports

Linting should detect:

- Unused imports.
- Duplicate imports.
- Illegal import patterns where explicitly defined.
- Wildcard imports where prohibited.
- Incorrect import organization where supported.

Imports should remain explicit and predictable.

---

### 6.3 Modifiers

Linting should identify inappropriate or inconsistent modifiers.

Examples include:

- Incorrect modifier ordering.
- Redundant modifiers.
- Missing modifiers where the project convention requires them.
- Incorrect visibility.

Particular care should be taken with:

- `final`.
- `public`.
- `protected`.
- `private`.
- `static`.

Linting must not enforce `final` indiscriminately where doing so would reduce readability or conflict with legitimate framework requirements.

---

### 6.4 Declaration Structure

Linting should enforce basic structural consistency for declarations.

Examples include:

- One declaration per line where appropriate.
- Consistent declaration formatting.
- Appropriate visibility.
- Consistent class/member structure.

The purpose is consistency rather than prescribing implementation design.

---

### 6.5 Braces and Control Structures

Linting should ensure that control structures follow project conventions.

Braces should be required for:

- `if`.
- `else`.
- `for`.
- `while`.
- `do`.
- Other applicable control structures.

Example:

```java
if (account.isActive()) {
    processAccount(account);
}
```

---

## 6.6 Empty Blocks

Unexpected empty blocks should be reported.

Examples include:
```text
try {
    processAccount();
} catch (Exception exception) {
}
```
and:
```text
if (condition) {
}
```
Legitimate empty blocks may be permitted when the language or framework explicitly requires them, but such exceptions should be narrowly scoped.

---

## 7. Java-Specific Linting

The linting configuration must remain compatible with Java 25+.

Rules must not unnecessarily restrict legitimate modern Java constructs.

The project should permit appropriate use of:

* Records.
* Sealed classes.
* Pattern matching.
* Switch expressions.
* Text blocks.
* Modern collection APIs.
* Other approved Java language features.

Lint rules must evolve when Java language features evolve.

A rule should not be retained merely because it was appropriate for an older Java version.

---

## 8. Spring Boot Linting

Checkstyle should enforce source-level conventions around Spring code where practical.

Examples include:

* Consistent component naming.
* Annotation placement.
* Naming conventions for configuration classes.
* Consistent declaration style.

However, Checkstyle should not attempt to determine whether a Spring component is architecturally correct.

For example, Checkstyle should not attempt to determine whether:
```text
@RestController
public class AccountController {
}
```
contains inappropriate business logic.

That belongs to architectural rules, static analysis, or code review.

---

## 9. Test-Code Linting

Test code must also be linted.

Linting should apply consistently to:

* Unit tests.
* Integration tests.
* Architecture tests.
* Test fixtures.
* Test-support classes.

Production and test code should not have completely separate quality expectations.

Limited test-specific exceptions may be introduced when a rule creates genuine testing friction.

Such exceptions must be explicit rather than allowing test code to bypass linting entirely.

---

## 10. Generated Code

Generated source code should be excluded from linting where manual modification is not expected.

Generated-code exclusions must be:

* Explicit.
* Narrowly scoped.
* Documented.
* Reproducible.

Generated code must not become a mechanism for bypassing linting.

Hand-written source code must remain fully subject to the linting standard.

---

## 11. Suppressions

Lint warnings must not be suppressed casually.

Where Checkstyle supports suppression, suppression should:

* Be narrowly scoped.
* Apply only to the necessary rule.
* Include a technical justification.
* Avoid suppressing an entire class or package unless unavoidable.
* Not become a substitute for fixing the underlying issue.

For example, a developer should not disable an entire lint category simply because one legitimate exception exists.

Broad suppressions require explicit architectural or technical justification.

---

## 12. Severity

Linting violations should be treated as build failures when they represent established project requirements.

The default expectation is:
```text
Lint violation → Build failure
```
This prevents known violations from accumulating in the repository.

Informational or advisory rules may exist where immediate enforcement would create excessive noise, but such rules should be deliberately classified rather than silently ignored.

---

## 13. Baseline Policy

Treasure Bank should not introduce a permanent lint baseline that allows existing violations to remain indefinitely.

If linting is introduced into an existing codebase containing violations, the preferred approach is to:

* Identify the violations.
* Categorize them.
* Fix the violations.
* Introduce the rules.
* Enforce the rules consistently.

A temporary baseline may be considered only when the remediation effort is substantial and the baseline has:

* A documented reason.
* Explicit ownership.
* A defined remediation plan.
* A clear removal target.

New violations must not be allowed to increase the existing technical debt.

---

## 14. Build Integration

Checkstyle must be integrated into the project's build lifecycle.

The intended flow is:
```text
Source Code
     ↓
Formatter Check
     ↓
Checkstyle
     ↓
Compilation
     ↓
Tests
     ↓
Static Analysis
```
The exact Maven/Gradle implementation belongs to the repository build configuration.

The important requirement is that linting must be executable locally and automatically in CI.

---

## 15. Local Developer Workflow

Developers should be able to run linting locally before creating a pull request.

The project should provide a single documented build or verification command that executes the configured linting checks.

Developers should not need to manually reproduce the CI environment to determine whether their code violates linting rules.

IDE integration may be provided where practical, but IDE linting must not replace build-level enforcement.

---

## 16. CI Enforcement

CI must execute the same linting configuration used by developers locally.

The CI environment must use:

* The repository's Checkstyle configuration.
* The project-approved Checkstyle version.
* The project's Java version.
* The project's build configuration.

CI must fail when mandatory linting checks fail.

A developer must not be able to merge code that fails mandatory linting checks unless an explicitly approved exception process is used.

---

## 17. Configuration Ownership

The Checkstyle configuration must be treated as source-controlled engineering infrastructure.

Changes to linting rules should be reviewed like code.

Changes should consider:

* Existing coding standards.
* Naming conventions.
* Formatting standards.
* Developer experience.
* Build performance.
* False-positive rates.
* Existing codebase compliance.
* Future maintainability.

A lint rule should not be introduced without understanding its impact on the entire repository.

---

## 18. Rule Evolution

Linting rules may evolve as Treasure Bank evolves.

Changes should be introduced deliberately.

When adding or changing a rule:

* Define the engineering problem.
* Determine whether the problem belongs to linting.
* Evaluate false positives.
* Update the documentation.
* Update the configuration.
* Fix affected code.
* Validate locally.
* Validate in CI.

Rules should not be added reactively without understanding their long-term maintenance cost.

---

## 19. What Linting Must Not Enforce

Checkstyle must not become the enforcement mechanism for concerns that belong elsewhere.

Linting should not be responsible for:

* Business correctness.
* Security vulnerability detection.
* Dependency vulnerability detection.
* Complex bug detection.
* Architectural dependency analysis.
* Module-boundary verification.
* Test coverage requirements.
* Runtime behavior.
* Database correctness.
* API contract correctness.

These concerns belong to other engineering controls.

---

## 20. Relationship to Static Analysis

Linting is intentionally narrower than static analysis.

**Linting**

Primarily answers:

> "Does this source code follow our established coding conventions?"

**Static Analysis**

Primarily answers:

> "Does this code contain potentially incorrect, dangerous, insecure, or maintainability-related behavior?"

Examples of static-analysis concerns include:

* Definite bugs.
* Suspicious conditions.
* Nullability problems.
* Security issues.
* Error-prone constructs.
* Code smells.
* Complex maintainability problems.

The Static Analysis requirement will define these controls separately.

---

## 21. Relationship to Type Safety

Linting may identify some type-related source patterns, but it must not replace the Java compiler or type system.

For example, linting may identify undesirable constructs, while compilation provides authoritative type checking.

The Type Safety requirement will define how Treasure Bank uses Java's type system to prevent invalid states and unsafe operations.

---

## 22. Pull-Request Expectations

Every pull request containing Java source changes should pass the mandatory linting checks.

A pull request should not introduce:

* New lint violations.
* New suppressions without justification.
* Changes that weaken linting rules solely to make the build pass.
* Unrelated modifications to lint configuration.

Changes to linting configuration should be reviewed explicitly.

---

## 23. Developer Responsibilities

Developers are responsible for:

* Running the project's verification workflow before submitting changes.
* Fixing lint violations.
* Avoiding unjustified suppressions.
* Keeping lint configuration changes focused.
* Following the documented coding and naming standards.
* Updating standards when a deliberate engineering decision changes a convention.

Linting is an automated safeguard, not a replacement for engineering judgment.

---

## 24. Enforcement Responsibilities

The engineering system is responsible for enforcing mandatory linting through:

* Local build validation.
* Pull-request checks.
* CI.
* Code review.

The same project configuration should be used consistently across these environments.

---

## 25. Exceptions

A linting exception is acceptable only when there is a legitimate technical reason.

The exception must:

* Be narrowly scoped.
* Be understandable to future maintainers.
* Avoid weakening unrelated rules.
* Be reviewed where appropriate.

Developer preference, convenience, or avoiding a small refactoring effort are not sufficient reasons to weaken a lint rule.


