# Treasure Bank Coding Standards

## 1. Purpose

This document defines the coding standards for Treasure Bank. It establishes the principles and practices that developers must follow when writing and maintaining application code.

The objective is to keep the codebase consistent, maintainable, secure, testable, and aligned with the Treasure Bank modular-monolith architecture.

## 2. Scope

These standards apply to all Treasure Bank production and test code unless a documented architectural or technical exception has been approved.

The baseline technology standards are:

- Java 25 or later.
- Spring Boot 4.1.x or the project-approved compatible version.
- Modular-monolith architecture as defined by the system architecture documentation.
- Automated build and CI validation.

Formatting, linting, static-analysis, Git, pull-request, testing, and documentation policies are defined in their respective engineering standards and are not duplicated here.

## 3. General Principles

Treasure Bank code should prioritize:

1. Correctness.
2. Clarity.
3. Maintainability.
4. Cohesion.
5. Explicitness.
6. Testability.
7. Security.
8. Architectural integrity.
9. Minimal accidental complexity.

Code should be written for the next developer who has to understand, modify, or operate it.

Prefer simple, explicit solutions over unnecessary abstractions or patterns.

## 4. Java Standards

### 4.1 Java Version

Java 25 or later is the project baseline.

Modern Java language and platform features should be used when they improve clarity, safety, maintainability, or domain expression.

Avoid deprecated or obsolete APIs when a supported alternative exists.

### 4.2 Type Safety

Prefer compile-time type safety.

- Do not use raw types.
- Avoid unchecked casts.
- Avoid suppressing compiler warnings unless necessary and justified.
- Prefer strongly typed domain concepts over primitive values when the domain benefits from explicit types.
- Do not use generic containers to avoid defining an appropriate domain type.

### 4.3 Immutability

Prefer immutable objects whenever practical.

- Minimize mutable state.
- Prefer `final` fields.
- Use records for immutable data/value representations where appropriate.
- Avoid exposing mutable internal state.
- Do not introduce setters solely for convenience.

Mutation should be deliberate and encapsulated.

### 4.4 Classes and Objects

Classes should have a clear and cohesive responsibility.

- Follow single-responsibility principles.
- Favor composition over inheritance unless inheritance expresses a genuine type relationship.
- Keep implementation details encapsulated.
- Avoid large classes with unrelated responsibilities.
- Avoid utility classes when a meaningful domain or service abstraction is more appropriate.
- Do not introduce interfaces solely to create an abstraction without a meaningful contract.

### 4.5 Methods

Methods should perform one clear responsibility.

- Keep methods reasonably small and understandable.
- Use descriptive names.
- Avoid excessive nesting.
- Prefer guard clauses when they improve readability.
- Avoid methods with excessive numbers of parameters.
- Avoid hidden side effects.
- Make state-changing operations explicit.

### 4.6 Collections

Use the collection type that communicates the required semantics.

Examples include:

- `List` for ordered collections.
- `Set` for uniqueness.
- `Map` for key/value lookup.

Do not expose mutable collections unnecessarily.

Prefer immutable or unmodifiable collections when mutation is not part of the contract.

### 4.7 Null Handling

`null` should be used deliberately.

- Do not use `null` as an ambiguous representation of multiple states.
- Prefer explicit domain types when absence has business meaning.
- Use `Optional` appropriately for return-value semantics.
- Do not use `Optional` indiscriminately for fields, method parameters, or every nullable value.

### 4.8 Exceptions

Exceptions should represent exceptional conditions rather than ordinary control flow.

- Do not silently swallow exceptions.
- Do not catch `Exception` indiscriminately.
- Preserve meaningful context when propagating errors.
- Do not expose low-level infrastructure exceptions directly when a higher-level abstraction is required.
- Do not use exceptions as a substitute for normal domain branching.

The complete Treasure Bank error taxonomy, propagation, translation, and external representation are defined by the Error-Handling Framework.

## 5. Spring Boot Standards

### 5.1 Dependency Injection

Prefer constructor injection.

Example:

```java
@RestController
class AccountController {

    private final AccountService accountService;

    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
}
```

Avoid field injection.

### 5.2 Controllers

Controllers should remain thin.

They should primarily:

- Receive external requests.
- Validate request structure at the appropriate boundary.
- Translate external representations into application requests.
- Invoke application services/use cases.
- Translate application results into external responses.

Business rules should not be implemented directly in controllers.

### 5.3 Services

Application services should coordinate application use cases rather than becoming general-purpose containers for unrelated business logic.

Business rules that belong to the domain should remain in the domain model.

Persistence concerns should not be embedded directly into controllers.

### 5.4 Configuration

Configuration concerns should remain separate from business logic.

Spring configuration and infrastructure wiring should not leak unnecessary framework concerns into the domain model.

## 6. Modular Architecture Standards

Treasure Bank is implemented as a modular monolith. Coding decisions must preserve module boundaries.

### 6.1 Module Boundaries

Each business module owns its internal implementation.

Developers must not bypass module boundaries merely because Java technically permits access.

Cross-module dependencies must use approved contracts and architectural boundaries.

### 6.2 Domain Layer

The domain layer owns business concepts and business rules.

Domain code should remain independent of infrastructure concerns wherever the architecture permits.

### 6.3 Application Layer

The application layer coordinates use cases and application workflows.

It should not become a dumping ground for business rules that belong in the domain.

### 6.4 Infrastructure Layer

Infrastructure implements technical concerns such as persistence, messaging, external integrations, and framework-specific adapters.

Infrastructure details must not unnecessarily leak into the domain model.

### 6.5 Interfaces

Interface/adaptor layers translate between external systems and internal application contracts.

External representations should not dictate internal domain models unnecessarily.

### 6.6 Cross-Module Access

A module must not directly access another module's internal infrastructure or implementation details.

Examples of prohibited coupling include:

- Direct access to another module's infrastructure classes.
- Direct access to another module's persistence implementation.
- Bypassing an application's approved public contract.
- Introducing dependencies that violate the architectural dependency direction.

Architectural rules must progressively be enforced through automated architectural tests and CI.

## 7. Naming Principles

Names must be descriptive and consistent with the domain.

Naming rules are defined in the Naming Conventions document.

A concept should have one canonical name across code, APIs, persistence, tests, and documentation unless different names represent genuinely different concepts.

## 8. Code Quality Principles

Production code should not contain:

- Dead code.
- Commented-out implementation code.
- Unexplained magic numbers.
- Unexplained magic strings.
- Unnecessary duplication.
- Unnecessary abstraction layers.
- Hidden side effects.
- Broad exception swallowing.
- Business logic embedded in inappropriate architectural layers.

Prefer expressive code over excessive comments.

Comments should explain why a non-obvious decision exists rather than restating what the code already expresses.

## 9. Logging and Sensitive Data

Application code must never expose sensitive information through logs.

Never log:

- Passwords.
- Authentication credentials.
- Access or refresh tokens.
- Secret keys.
- Full payment credentials.
- Sensitive banking information.
- Other confidential values prohibited by the security requirements.

Do not use `System.out` or `System.err` as the application's logging mechanism.

The complete structured logging, correlation, observability, and sensitive-data handling strategy is defined by the Logging & Observability Foundation.

## 10. Anti-Patterns

The following should be treated as warning signs requiring justification or refactoring:

- God classes.
- God methods.
- Anemic domain models where meaningful business behavior belongs in the domain.
- Controller-heavy business logic.
- Service classes containing unrelated responsibilities.
- Excessive inheritance.
- Unnecessary interfaces.
- Generic `Utils`, `Helper`, or `Manager` classes.
- Hidden global state.
- Direct cross-module implementation access.
- Catch-and-ignore exception handling.
- Framework dependencies unnecessarily embedded in domain logic.

## 11. Enforcement

These standards are part of the Treasure Bank engineering baseline.

Enforcement will progressively be provided through:

- Automated formatting.
- Linting.
- Static analysis.
- Dependency checks.
- Automated tests.
- Architectural tests.
- CI validation.
- Pull-request review.
- Code-review standards.

Where a rule can be mechanically enforced, automation should be preferred over relying solely on human review.

## 12. Exceptions and Future Standards

A deliberate exception to these standards must have a clear technical reason and should be documented where the exception affects architectural or long-term maintenance decisions.

This document establishes coding principles only.

The following concerns are governed by separate engineering standards:

- Naming conventions.
- Formatting.
- Linting.
- Static analysis.
- Dependency management.
- Commit conventions.
- Branching conventions.
- Pull-request standards.
- Code-review standards.
- Testing standards.
- Documentation standards.
- Architectural compliance.
- Error handling.
- Logging and observability.
