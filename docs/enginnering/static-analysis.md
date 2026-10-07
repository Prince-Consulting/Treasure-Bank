# Treasure Bank Static Analysis Standards

## 1. Purpose

This document defines the static-analysis standards for Treasure Bank.

Static analysis provides automated examination of application code without requiring the application to execute.

Its purpose is to identify potentially:

- Incorrect code.
- Defective code.
- Unsafe code.
- Security-sensitive code patterns.
- Error-prone constructs.
- Resource-handling problems.
- Maintainability concerns that can be detected automatically.

Static analysis is one layer of the Treasure Bank quality system.

It complements:

- Coding Standards.
- Naming Conventions.
- Formatting Standards.
- Linting.
- Type Safety.
- Testing.
- Code Review.
- Architectural Compliance.

---

## 2. Scope

Static analysis applies to:

- Production Java code.
- Business modules.
- Shared code.
- Infrastructure code.
- Interface/adaptor code.
- Configuration code where analyzable.
- Test-support code where appropriate.

Test code may use a separate severity profile where justified, but tests must not be excluded from analysis without a technical reason.

---

## 3. Static-Analysis Tooling

Treasure Bank uses:

- **SpotBugs** as the primary Java static-analysis engine.
- **FindSecBugs** as the security-focused SpotBugs plugin.

The selected versions must be compatible with the project's Java 25+ baseline and build system.

The exact versions belong in the project's dependency/build configuration and must be centrally managed.

The static-analysis configuration must be version-controlled.

---

## 4. Relationship to Linting

Static analysis and linting are separate quality controls.

### Linting

Linting primarily checks source-level conventions.

Examples:

- Naming.
- Imports.
- Modifiers.
- Braces.
- Source structure.
- Basic coding conventions.

### Static Analysis

Static analysis examines code for potentially incorrect or dangerous behavior.

Examples:

- Incorrect equality comparisons.
- Resource leaks.
- Null-related defects detectable by the analyzer.
- Incorrect exception handling.
- Suspicious API usage.
- Concurrency-related problems.
- Security-sensitive coding patterns.

Neither tool should be configured to unnecessarily duplicate the other.

---

## 5. Static-Analysis Principles

Static analysis must be:

- Automated.
- Repeatable.
- Deterministic.
- Version-controlled.
- Executable locally.
- Executed in CI.
- Appropriate for Java 25+.
- Actionable.
- Reviewed periodically.

The objective is high-value defect detection rather than maximizing the number of warnings.

A warning that consistently produces false positives should be investigated and either:

- Corrected in the code.
- Properly suppressed with justification.
- Reconfigured when the rule is demonstrably inappropriate.

Warnings must not simply be ignored.

---

## 6. Analysis Categories

The initial static-analysis configuration should focus on the following areas.

### 6.1 Correctness

Detect patterns that may indicate incorrect behavior.

Examples include:

- Incorrect comparison operations.
- Suspicious conditions.
- Unreachable or ineffective code.
- Incorrect use of APIs.
- Defective control flow.
- Mistakes involving object equality.

Static analysis should identify code that deserves developer investigation.

---

### 6.2 Null and Reference Safety

Static analysis should identify detectable risks involving:

- Null dereferences.
- Incorrect null checks.
- Unsafe reference handling.
- Suspicious nullable values.

Static analysis does not replace Java's compiler or the project's type-safety strategy.

Where a problem can be prevented through stronger types, the type system should be preferred over relying solely on static-analysis warnings.

---

### 6.3 Resource Management

Static analysis should detect potentially unsafe resource handling.

Examples include:

- Resources not properly closed.
- Streams that may leak.
- Connections or handles that may not be released.
- Incorrect lifecycle handling.

Where Java's language features provide a safer mechanism, developers should prefer those mechanisms.

For example:

```java
try (InputStream inputStream = resource.openStream()) {
    process(inputStream);
}
```

---

## 6.4 Exception Handling

Static analysis should identify suspicious exception-handling patterns where supported.

Examples include:

* Ignored exceptions.
* Exceptions swallowed without meaningful handling.
* Suspicious catch blocks.
* Incorrect exception propagation.
* Loss of useful diagnostic context.

Static analysis does not define the complete Treasure Bank exception taxonomy.

That responsibility belongs to the Error-Handling Framework.

---

## 6.5 Concurrency

Static analysis should identify detectable concurrency-related defects where supported.

Examples include:

* Unsafe synchronization patterns.
* Incorrect synchronization.
* Potential race-related constructs.
* Shared mutable state patterns that are statically detectable.

Concurrency rules should be applied carefully because static analysis cannot prove the correctness of every concurrent design.

---

## 6.6 Security

FindSecBugs should be used to identify recognized security-sensitive coding patterns.

Examples include potential:

* Injection vulnerabilities.
* Unsafe deserialization.
* Weak cryptographic usage.
* Hard-coded credentials.
* Path traversal risks.
* Unsafe output handling.
* Dangerous API usage.

Static analysis is a preventive security control.

It does not replace:

* Dependency vulnerability scanning.
* Penetration testing.
* Threat modeling.
* Security review.
* Runtime security controls.

---

7. Security-Sensitive Data

Static analysis should help identify accidental exposure of sensitive values where the selected analyzers support such detection.

Treasure Bank code must never intentionally embed:

* Passwords.
* Authentication credentials.
* API secrets.
* Private keys.
* Access tokens.
* Encryption keys.
* Production credentials.

Static analysis is an additional safeguard and must not be treated as the primary secret-management mechanism.

Secret management belongs to the Configuration & Environment Management standard.

---

## 8. Severity Classification

Static-analysis findings should be classified according to their potential impact.

The project should distinguish at least:

* High.
* Medium.
* Low.
* Informational.

High-confidence, high-impact findings should block the build.

Lower-severity findings may initially be advisory when necessary, but they must remain visible and tracked.

The severity policy should be reviewed as the project matures.

---

## 9. Build Failure Policy

The default expectation is:
```text
Blocking static-analysis violation
        ↓
Build failure
        ↓
Pull request cannot be merged
```
However, not every analyzer warning should automatically block development.

The project should prioritize:

1. High-confidence defects.
2. Security-sensitive findings.
3. High-impact correctness problems.
4. Resource leaks.
5. Other clearly actionable findings.

This prevents developers from becoming accustomed to ignoring large quantities of low-value warnings.

---

## 10. Baseline Policy

Treasure Bank should not establish a permanent baseline that hides existing static-analysis violations.

If existing code produces findings when static analysis is introduced:

1. Generate the initial report.
2. Review and classify findings.
3. Fix genuine defects.
4. Determine whether any findings are false positives.
5. Introduce narrowly scoped suppressions where justified.
6. Establish enforcement.
7. Prevent new violations.

A temporary baseline may be used only when remediation cannot reasonably be completed immediately.

Any temporary baseline must have:

* A documented reason.
* An owner.
* A remediation strategy.
* A target for removal.

New findings must not increase technical debt.

---

## 11. Suppression Policy

Static-analysis suppressions must be exceptional.

A suppression should:

* Apply to the smallest possible scope.
* Identify the affected rule.
* Have a technical justification.
* Be understandable to future maintainers.
* Avoid hiding unrelated findings.

Prefer fixing the underlying code over suppressing a warning.

Broad suppressions at package or project level require explicit technical justification.

The following is not an acceptable reason:

"The analyzer complains about this code."

A suppression should explain why the analyzer's finding does not apply to the actual design.

---

## 12. False Positives

False positives must be investigated rather than ignored.

When a finding is determined to be a false positive:

* Verify the actual code behavior.
* Confirm why the analyzer cannot correctly infer the behavior.
* Determine whether the code can be rewritten to make the intent clearer.
* If not, use the narrowest appropriate suppression.
* Document the reason where necessary.

The goal is to preserve trust in the analyzer.

---

## 13. Java 25+ Compatibility

Static-analysis tooling must support the project's Java baseline.

The configuration must be validated against:

* Java 25.
* Approved future Java versions where applicable.
* Modern language constructs used by Treasure Bank.

The analyzer must not incorrectly report valid Java 25+ constructs merely because a rule was designed for an older Java version.

Tool upgrades should be evaluated when the Java baseline changes.

---

## 14. Spring Boot Analysis

Static analysis should analyze Spring Boot application code where the analyzer can provide meaningful findings.

It may identify issues involving:

* Unsafe API usage.
* Exception handling.
* Resource management.
* Security-sensitive operations.
* Suspicious code patterns.

However, static analysis must not be treated as the authority for Spring architecture.

For example, static analysis should not attempt to decide whether business logic belongs in:
```text
Controller
    ↓
Application Service
    ↓
Domain
    ↓
Infrastructure
```
Architectural compliance is handled separately.

---

## 15. Modular-Monolith Boundary

Static analysis must not become the primary mechanism for enforcing Treasure Bank module boundaries.

Module-boundary rules belong to the Architectural Compliance requirement.

Where the selected static-analysis tooling can provide useful supporting checks, those checks may be enabled.

However:
```text
Static Analysis ≠ Architectural Enforcement
```
Architecture-specific dependency rules must have an explicit architectural enforcement mechanism.

---

## 16. Test Code

Test code should be included in static analysis where practical.

Analysis should cover:

* Unit tests.
* Integration tests.
* Architecture tests.
* Test fixtures.
* Test-support utilities.

Some analyzer rules may legitimately have different relevance in tests.

Such differences should be explicit rather than disabling analysis for test code entirely.

---

## 17. Generated Code

Generated code may be excluded from static analysis when:

* It is generated automatically.
* Developers are not expected to modify it manually.
* The generator is the authoritative source.

Generated-code exclusions must be explicit and documented.

Hand-written code must remain subject to static analysis.

Generated code must not be used to circumvent quality controls.

---

## 18. Local Execution

Developers must be able to execute static analysis locally.

The project should provide a documented build command that runs the configured static-analysis checks.

The local analysis should use the same:

* Source.
* Configuration.
* Analyzer version.
* Java version.
* Build configuration.

used by CI.

IDE-specific analysis may provide additional feedback, but IDE tooling must not replace repository-level enforcement.

---

## 19. CI Enforcement

Static analysis must execute automatically in CI.

The CI environment must use the repository-controlled configuration.

At minimum, CI should:

* Compile the project.
* Execute static analysis.
* Produce an analysis report.
* Fail when mandatory blocking findings are detected.

CI must not use a weaker configuration than local development.

---

## 20. Pull-Request Integration

Static-analysis results should be visible during pull-request validation.

A developer should be able to determine:

* Whether analysis passed.
* Which findings were introduced.
* Which findings are blocking.
* Why a finding is blocking.
* Whether an exception or suppression is involved.

Static analysis should therefore become a normal part of the development feedback loop.

---

## 21. Dependency and Vulnerability Scanning

Static analysis must not be confused with dependency vulnerability scanning.

SpotBugs and FindSecBugs analyze application code.

Dependency scanning evaluates third-party components for known vulnerabilities.

Dependency management and dependency vulnerability controls will be addressed separately.

The project should eventually include both controls.

---

22. Code Coverage

Static analysis does not measure whether code is adequately tested.

Coverage tools and testing standards belong to the Testing Standards requirement.

A project must not consider:
```text
Static Analysis Passed
```
equivalent to:
```text
Code Is Correctly Tested
```
Both controls serve different purposes.

---

## 23. Developer Responsibilities

Developers are responsible for:

* Reviewing static-analysis findings.
* Fixing genuine defects.
* Avoiding unjustified suppressions.
* Keeping suppressions narrowly scoped.
* Understanding why a finding exists.
* Running the project's verification workflow before submitting changes.

Developers must not modify analyzer configuration merely to avoid fixing a defect.

---

## 24. Configuration Ownership

Static-analysis configuration is engineering infrastructure.

Changes to analyzer versions, rules, severity thresholds, exclusions, or suppressions should be reviewed as code.

Configuration changes should explain:

* What problem is being addressed.
* Why the change is necessary.
* What code is affected.
* Whether enforcement becomes stronger or weaker.

Reducing analysis coverage requires stronger justification than adding useful checks.

---

## 25. Rule Evolution

Static-analysis rules should evolve as Treasure Bank evolves.

When introducing a new rule:

* Identify the engineering problem.
* Confirm that static analysis is the correct control.
* Evaluate false-positive behavior.
* Determine the appropriate severity.
* Update configuration.
* Update documentation.
* Remediate affected code.
* Validate locally.
* Validate in CI.

Rules should be periodically reviewed for usefulness.

---

## 26. What Static Analysis Must Not Replace

Static analysis must not replace:

* Compiler type checking.
* Unit tests.
* Integration tests.
* Architecture tests.
* Code review.
* Security review.
* Dependency vulnerability scanning.
* Runtime monitoring.
* Penetration testing.
* Developer judgment.

It is one layer in a defense-in-depth engineering system.

---

27. Quality-Gate Philosophy

Treasure Bank should use a quality-gate approach.

The desired progression is:
```text
Developer
    ↓
Format
    ↓
Lint
    ↓
Compile
    ↓
Static Analysis
    ↓
Tests
    ↓
Architecture Validation
    ↓
Pull Request Review
    ↓
Merge
```
Each stage should detect a different class of problem.

The goal is to identify inexpensive-to-fix problems as early as possible.

---

