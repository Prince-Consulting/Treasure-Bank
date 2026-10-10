# Treasure Bank Architectural Compliance Expectations

## 1. Purpose
The purpose of this document is to define the architectural compliance expectations for Treasure Bank.

Treasure Bank adopts a modular monolith architecture to minimize unnecessary distributed-system complexity during the early stages of development while maintaining a clear path toward extracting individual modules into microservices when justified by future business or technical requirements.

Architectural compliance ensures that the implementation consistently follows the approved architecture, preserves module boundaries, controls dependencies, and prevents structural decisions that could make the system difficult to maintain or evolve.

These expectations apply to application code, infrastructure, configuration, dependencies, testing, and technical decisions that can affect the system's architecture.

---

## 2. Architectural Governance Principles

All architectural decisions and implementations must follow these principles.

1. **Architecture is an enforceable engineering constraint**. Approved architectural rules must be reflected in implementation and verified wherever practical.

2. **Modules own their responsibilities**. Each module must have a clearly defined purpose and controlled boundaries.

3. **Dependencies must be intentional**. A module must not depend on another module without an approved architectural reason.

4. **Encapsulation is mandatory**. Internal implementation details must not become accidental cross-module contracts.

5. **The simplest compliant design is preferred.** Do not introduce unnecessary abstractions, infrastructure, or distributed-system complexity.

6. **Architecture must evolve deliberately**. Structural changes require assessment, justification, and appropriate review.

7. **Compliance must be verifiable**. Architectural rules should be automated where practical rather than relying exclusively on developer discipline.

8. **Future extraction must remain feasible**. Modules should minimize unnecessary coupling so that future microservice extraction remains possible without requiring every module to operate as a separate service today.

---

## 3. Architectural Baseline

The approved architectural baseline for Treasure Bank is a modular monolith.

The baseline must define:

* The system's architectural style.
* Module responsibilities.
* Module boundaries.
* Allowed dependency directions.
* Inter-module communication rules.
* Data ownership expectations.
* Shared infrastructure responsibilities.
* External integration boundaries.
* Security boundaries.
* Architectural constraints and significant trade-offs.

The authoritative architecture documentation must reside in the repository and be discoverable through the root README.md.

Implementation decisions must be consistent with the current approved architectural baseline.

Where the baseline does not address a situation, engineers should follow established design principles and seek architectural clarification when the decision could materially affect module boundaries or long-term system structure.

---

## 4. Module Boundaries

Every module must have a clearly defined responsibility and a controlled interface.

Modules should be organized around meaningful business capabilities or cohesive technical responsibilities rather than arbitrary collections of classes.

Each module should:

* Own its internal business logic.
* Expose only the interfaces required by authorized consumers.
* Encapsulate its implementation details.
* Minimize knowledge of other modules' internals.
* Avoid duplicating another module's responsibilities.
* Maintain clear ownership of its data and rules.

A module must not access another module's internal classes, repositories, persistence entities, or implementation-specific services unless explicitly permitted by the approved architecture.

A package or directory boundary alone does not establish architectural isolation. Boundaries must be supported by dependency rules, API design, and automated verification where practical.

---

## 5. Module Dependency Rules

Dependencies between modules must follow the approved dependency direction.

Before introducing a module dependency, the developer must establish:

* Why the dependency is necessary.
* Which module owns the required capability.
* Whether the dependency creates inappropriate coupling.
* Whether an existing public module interface can be used.
* Whether the dependency introduces a cycle.
* Whether the dependency complicates future extraction.

The following rules apply:

* Modules must not create circular dependencies.
* Modules must not depend on another module's internal implementation.
* Dependencies must not be introduced merely for convenience.
* Shared abstractions must have a clearly justified purpose.
* Dependency direction must remain consistent with the approved architecture.

For example, an account module requiring transaction processing should use the transaction module's approved interface rather than directly accessing its repositories or persistence implementation.

The precise dependency graph must be documented and enforced through architecture tests where practical.

---

## 6. Inter-Module Communication

Communication between modules must follow explicit, documented contracts.

Approved mechanisms may include:

* Public application-service interfaces.
* Explicit command or query interfaces.
* Domain events or application events where justified.
* Other documented mechanisms approved for the architecture.

The chosen mechanism must suit the business requirement and avoid unnecessary coupling.

Modules must not communicate by:

* Directly modifying another module's internal state.
* Accessing another module's private persistence implementation.
* Depending on undocumented implementation details.
* Introducing hidden control flow through shared mutable state.

In-process communication is the default where it satisfies the requirement. Distributed messaging or network calls must not be introduced merely to imitate a microservice architecture inside the monolith.

---

## 7. Data Ownership and Persistence Boundaries

Each module must have clearly defined ownership of the data and business rules for which it is responsible.

Architectural compliance requires that:

* A module controls its persistence behavior through its own implementation.
* Other modules use approved interfaces to request changes or retrieve information.
* Database access does not become a mechanism for bypassing module boundaries.
* Cross-module queries have an explicit architectural rationale.
* Data consistency requirements are documented.
* Transaction boundaries are designed intentionally.

A shared physical database is compatible with a modular monolith. However, sharing a database must not mean that every module is free to manipulate every other module's tables.

Where practical, persistence schemas, tables, repositories, or equivalent structures should reflect clear ownership.

Cross-module database access must be explicitly permitted by the architecture rather than emerging accidentally through convenience.

---

## 8. Domain Model Integrity

Business rules must remain within the modules responsible for those rules.

Developers must avoid:

* Duplicating business rules across modules.
* Moving domain logic into unrelated shared utilities.
* Exposing mutable domain objects unnecessarily.
* Using persistence entities as unrestricted cross-module contracts.
* Creating generic services that accumulate unrelated business responsibilities.

Shared code should be introduced only when it represents a genuine shared concept or technical capability.

A shared module must not become a dumping ground for business logic that belongs to individual domain modules.

---

## 9. Shared Components and Common Modules

Shared components must have a clearly defined scope and purpose.

A shared module may contain cross-cutting capabilities such as carefully selected common abstractions or infrastructure support, provided these responsibilities are approved by the architecture.

Before adding functionality to a shared module, developers must consider:

* Whether the functionality genuinely serves multiple modules.
* Whether it introduces unnecessary coupling.
* Whether it creates a dependency on a specific business module.
* Whether a change could affect every consuming module.
* Whether the shared component is becoming a bottleneck for independent evolution.

Business-specific behavior should remain in the module that owns it.

The existence of duplicated code alone is not sufficient justification for creating a shared abstraction.

---

## 10. Dependency Inversion and Abstraction

Dependencies must be designed to preserve clear ownership and manageable coupling.

Where appropriate, the system should use dependency inversion so that higher-level business policies are not unnecessarily coupled to infrastructure implementation details.

Examples include isolating business logic from:

* Database technologies.
* External payment or banking providers.
* Messaging infrastructure.
* Notification providers.
* Other replaceable external integrations.

Abstractions should be introduced when they provide a meaningful architectural benefit.

They must not be added mechanically to every class or interface.

The objective is to protect important boundaries without creating needless indirection.

---

## 11. Framework and Infrastructure Isolation

Spring Boot and other infrastructure technologies should support the architecture rather than determine every domain design decision.

Where appropriate:

* Domain logic should remain independent of infrastructure implementation details.
* Database concerns should remain within designated persistence components.
* External API clients should remain behind defined integration boundaries.
* Configuration should be centralized and explicit.
* Infrastructure-specific code should not leak unnecessarily across modules.

Framework annotations and dependency injection may be appropriate in application and infrastructure layers. Their use must remain consistent with the module's responsibilities and the approved design.

The architecture should avoid making ordinary business logic difficult to test or understand solely because of infrastructure coupling.

---

## 12. API and Contract Stability

Public module interfaces must be designed as explicit contracts.

Changes to a public interface should consider:

* Existing consumers.
* Backward compatibility where applicable.
* Error handling.
* Validation.
* Ownership of data and behavior.
* Potential impact on other modules.
* Future extraction into a service boundary.

Internal implementation details must not become public contracts by accident.

If a change breaks an established module contract, the impact must be documented, reviewed, and addressed through a controlled change.

---

## 13. Transaction Boundaries and Consistency

Transaction boundaries must align with business operations and data ownership.

Developers must evaluate:

* Which module owns the business operation.
* Which data changes must be atomic.
* Whether a transaction spans multiple module responsibilities.
* Whether failure can leave inconsistent state.
* Whether retries can cause duplicate effects.
* Whether concurrency introduces race conditions.

For financial operations, atomicity, consistency, idempotency, and auditability require particular attention.

A single-process architecture does not eliminate transaction design requirements.

If an operation spans multiple modules, the design must explicitly establish how coordination and consistency are maintained. Distributed-systems patterns should not be introduced unless the requirements justify them.

---

## 14. Security Architecture Compliance

Architectural decisions must preserve the system's security boundaries.

Developers must ensure that:

* Authentication and authorization responsibilities remain explicit.
* Modules do not bypass required access-control checks.
* Sensitive data is exposed only through approved interfaces.
* Secrets are managed outside source-controlled code.
* Security-critical dependencies and integrations are appropriately isolated.
* Trust boundaries are documented and respected.
* Security-sensitive architectural changes receive appropriate review.

Security controls must not be weakened to make module integration easier.

---

## 15. External Integration Boundaries

External systems must be accessed through clearly defined integration boundaries.

Examples include:

* Payment providers.
* Banking or financial data providers.
* Identity providers.
* Notification services.
* AI model providers.
* External APIs.

Integration code should isolate provider-specific protocols, data structures, errors, and configuration from unrelated business modules.

The architecture should permit a provider to be replaced or modified without forcing widespread changes throughout the application.

External integration boundaries must also address timeout behavior, failure handling, retries, idempotency, and security where applicable.

---

## 16. Configuration and Environment Separation

Configuration must follow the approved application and deployment architecture.

Developers must avoid:

* Hardcoding environment-specific values.
* Embedding secrets in application code.
* Introducing undocumented configuration dependencies.
* Allowing local development configuration to silently override production requirements.
* Coupling business logic directly to machine-specific environment details.

Configuration changes that affect module initialization, security, data access, or deployment must be assessed for architectural impact.

---

## 17. Architectural Testing

Architectural rules should be enforced automatically wherever practical.

Architecture tests should verify requirements such as:

* Allowed module dependencies.
* Prohibition of circular dependencies.
* Restrictions on internal package access.
* Separation of architectural layers.
* Module ownership boundaries.
* Prevention of forbidden framework or infrastructure dependencies.
* Compliance with package organization conventions.

Architecture tests must run as part of the automated build or CI pipeline when they protect mandatory architectural constraints.

A test failure indicating an architectural violation must be investigated and resolved before merging unless an explicitly approved exception applies.

Architectural tests complement code review; they do not replace it.

---

## 18. Dependency Management Compliance

All architectural dependencies must comply with the project's Dependency Management standards.

Developers must assess the architectural implications of:

* Adding a new library.
* Introducing a framework.
* Upgrading a foundational dependency.
* Adding a cross-module dependency.
* Introducing a new external service.
* Changing persistence or messaging technology.

A dependency must not be added solely because it offers a convenient shortcut when doing so creates disproportionate coupling or unnecessary infrastructure.

---

## 19. Architecture Decision Records

Significant architectural changes must be documented using Architecture Decision Records (ADRs).

An ADR should describe:

* Context and problem.
* Decision.
* Alternatives considered.
* Rationale.
* Consequences and trade-offs.
* Impact on existing modules.
* Implications for testing, operations, security, and future evolution.

Examples include:

* Introducing a new module.
* Changing module dependency direction.
* Allowing cross-module data access.
* Introducing asynchronous communication.
* Changing transaction coordination.
* Adding a major infrastructure technology.
* Changing the approved architectural style.

Routine implementation details do not require an ADR unless they materially affect architectural constraints or future design decisions.

Approved ADRs must be discoverable through the repository's architecture documentation.

---

## 20. Architectural Compliance During Pull Requests

Every Pull Request must be evaluated for architectural impact.

The author must consider whether the change:

* Introduces or changes a module dependency.
* Alters a public module interface.
* Changes data ownership.
* Changes transaction boundaries.
* Introduces infrastructure or an external integration.
* Modifies security boundaries.
* Changes a documented architectural decision.
* Requires architecture tests or documentation updates.

Reviewers must verify compliance with relevant architecture rules.

A Pull Request must not be approved solely because its tests pass if it violates an approved architectural constraint.

---

## 21. Architectural Exceptions

Exceptions to mandatory architectural rules require explicit approval.

An exception request must document:

* The architectural rule affected.
* The reason the rule cannot reasonably be followed.
* The modules and components affected.
* The risks and trade-offs.
* Any compensating controls.
* The responsible owner.
* Whether the exception is temporary or permanent.
* A remediation plan and target date, if temporary.

Temporary exceptions should be tracked through GitHub issues where appropriate.

Exceptions must not become an informal mechanism for bypassing the architecture.

A permanent architectural change should normally be treated as an architecture decision and reflected in the approved documentation.

---

## 22. Architectural Drift

Architectural drift occurs when the implementation gradually diverges from its approved architectural design.

Examples include:

* Increasing cross-module dependencies.
* Circular dependencies.
* Shared modules accumulating unrelated responsibilities.
* Direct access to another module's persistence layer.
* Undocumented exceptions.
* Business logic moving into infrastructure components.
* Public interfaces exposing internal implementation details.

Architectural drift must be addressed deliberately.

When drift is identified, the responsible engineers should determine whether to:

* Correct the implementation.
* Introduce automated enforcement.
* Update the architecture because the original design is no longer appropriate.
* Document and approve an exception.

Documentation must reflect approved architectural changes, not silently legitimize unreviewed drift.

---

## 23. Future Microservice Extraction Readiness

Treasure Bank's modular monolith must be designed to preserve the option of extracting modules into microservices when there is a justified business or technical need.

This does not mean that each module must immediately implement distributed-system infrastructure.

Instead, modules should aim for:

* Clear responsibility.
* Controlled interfaces.
* Explicit data ownership.
* Minimal internal coupling to other modules.
* Defined business operations.
* Documented consistency requirements.
* Isolated external integrations.
* Automated boundary verification.

Future extraction may require changes to communication, transactions, deployment, observability, and failure handling. These changes should be recognized as real migration work rather than assumed to be automatic.

The immediate priority is a well-structured modular monolith, not premature distribution.

---

## 24. Technology Stack Compliance

Implementation must remain consistent with the approved technology baseline unless an exception or architectural decision authorizes a change.

Treasure Bank's established baseline includes:

* Java 25 or later.
* Spring Boot 4.1.x.
* Maven.
* Docker for containerization where applicable.
* Automated CI validation.

The exact versions and supported combinations must be managed through the project's dependency and build configuration.

New frameworks, libraries, infrastructure technologies, or architectural tooling must have a clear justification and undergo appropriate compatibility and security assessment.

---

## 24. Technology Stack Compliance

Implementation must remain consistent with the approved technology baseline unless an exception or architectural decision authorizes a change.

Treasure Bank's established baseline includes:

Java 25 or later.

Spring Boot 4.1.x.

Maven.

Docker for containerization where applicable.

Automated CI validation.

The exact versions and supported combinations must be managed through the project's dependency and build configuration.

New frameworks, libraries, infrastructure technologies, or architectural tooling must have a clear justification and undergo appropriate compatibility and security assessment.

---

## 25. Governing Principle

> Every change to Treasure Bank must preserve the approved architectural boundaries or introduce an explicitly reviewed and documented architectural evolution.

Architectural compliance is not about preventing change. It is about ensuring that change remains deliberate, controlled, and consistent with the long-term integrity of the system.

---

