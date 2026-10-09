# Treasure Bank Documentation standard

## 1. Purpose
The purpose of this document is to define the standards for creating, organizing, maintaining, reviewing, and governing documentation within Treasure Bank.

Documentation is an essential engineering asset. It communicates the system's architecture, design decisions, operational procedures, development conventions, and expected behavior to current and future contributors.

Treasure Bank documentation must make it possible for an engineer to understand the system, contribute safely, operate it correctly, and maintain it without depending entirely on undocumented knowledge from another person.

Documentation must evolve alongside the system rather than become an outdated description of how the system used to work.

---

## 2. Documentation Principles

All Treasure Bank documentation must follow these principles.

1. Accuracy — Documentation must reflect the actual implementation and approved engineering decisions.
2. Clarity — Content must be understandable to its intended audience.
3. Conciseness — Document what is necessary without introducing unnecessary length or repetition.
4. Discoverability — Engineers must be able to locate relevant documentation quickly.
5. Maintainability — Documentation must be easy to update when the system changes.
6. Consistency — Documents must follow common naming, formatting, and organizational conventions.
7. Traceability — Important requirements, architectural decisions, and operational procedures should be traceable to their relevant issues, decisions, or implementations.
8. Single source of truth — Information should have one authoritative location, with links from other documents where necessary.
9. Security — Documentation must not expose secrets, credentials, sensitive customer information, or confidential operational details.
10. Documentation as code — Repository documentation must be version-controlled, reviewed, and maintained alongside the code it describes.

---

## 3. Documentation Format

Markdown (.md) is the default format for documentation stored in the Treasure Bank repository.

Markdown is preferred because it:

* Is readable in source form.
* Is supported natively by GitHub.
* Supports headings, lists, tables, links, and code blocks.
* Works well with version control and code review.
* Is suitable for architecture, development, and operational documentation.

Other formats may be used when justified by the nature of the content.

Examples include:

* YAML or JSON for machine-readable configuration and examples.
* OpenAPI specifications for API contracts.
* PlantUML or Mermaid source for diagrams.
* PDF for formally distributed documents when necessary.

The default should remain plain-text, version-controlled formats whenever practical.

---

## 4. Documentation Location and Structure

Documentation must have a predictable location within the repository.

Treasure Bank should maintain a central documentation directory, such as:
```text
treasure-bank/
├── README.md
├── CONTRIBUTING.md
├── docs/
│   ├── architecture/
│   ├── development/
│   ├── testing/
│   ├── security/
│   ├── operations/
│   ├── api/
│   └── decisions/
├── pom.xml
└── ...
```
This structure is a recommended logical organization. The actual directory names and placement must align with the repository's approved structure.

Each directory should have a clear purpose.


| Directory | Purpose |
| :--- | :--- |
| `docs/architecture/` | System architecture, module boundaries, and architectural diagrams |
| `docs/development/` | Coding conventions, branching, commits, and contribution workflow |
| `docs/testing/` | Testing strategy, test execution, and test infrastructure |
| `docs/security/` | Security architecture, controls, and security procedures |
| `docs/operations/` | Deployment, configuration, monitoring, troubleshooting, and recovery |
| `docs/api/` | API specifications and integration guidance |
| `docs/decisions/` | Architecture Decision Records (ADRs) and significant technical decisions |

Not every category must immediately contain documents. Directories should be introduced when there is content to organize.

---

## 5. README Standards

The root README.md is the primary entry point to Treasure Bank.

It should provide a concise overview of the project and help readers discover the information they need.

The README should include, as applicable:

* Project name and description.
* Project purpose and objectives.
* Current development status.
* Core technology stack.
* High-level architecture overview.
* Prerequisites.
* Instructions for building and running the application.
* Instructions for running tests.
* Configuration guidance.
* Links to detailed documentation.
* Contribution guidance.
* Licensing information, where applicable.

The root README must not become a complete replacement for the documentation directory.

Detailed topics should live in dedicated documents and be linked from the README.

---

## 6. Document Naming Conventions

Documentation filenames must be descriptive and consistent.

The following conventions apply:

Use lowercase filenames.
Separate words with hyphens.
Use the .md extension for Markdown documents.
Prefer names that communicate the document's subject.
Avoid ambiguous names such as notes.md, misc.md, or stuff.md for permanent project documentation.
Avoid creating multiple documents that describe the same subject without a clear distinction.

Examples:
```text
system-overview.md
modular-monolith-architecture.md
module-dependency-rules.md
testing-standards.md
dependency-management.md
local-development.md
database-migration-guide.md
incident-response.md
```
Use README.md for directory-level index documents where appropriate.

---

## 7. Document Structure

Documents must use a consistent structure appropriate to their purpose.

A standards or policy document should generally include:

1. Title
2. Purpose
3. Scope or applicability, where relevant
4. Requirements, rules, or procedures
5. Examples, where useful
6. Governing principle, where appropriate
7. Acceptance criteria or compliance requirements, where appropriate

Technical guides should instead prioritize prerequisites, instructions, expected outcomes, troubleshooting, and related references.

Not every document needs every section. Structure should improve comprehension rather than create unnecessary boilerplate.

---

## 8. Headings and Formatting

Documentation must use Markdown consistently.

Standards include:

* Use a single level-one heading (#) for the document title.
* Use level-two headings (##) for primary sections.
* Use deeper heading levels only when necessary.
* Keep headings descriptive.
* Use lists for related items.
* Use tables for genuinely comparative or structured information.
* Use fenced code blocks for commands, configuration, and source examples.
* Specify a code block's language when known.
* Use inline code formatting for filenames, commands, configuration keys, class names, and identifiers.
* Use bold formatting sparingly to emphasize important information.

Avoid excessive nesting, overly wide tables, and formatting that makes the Markdown source difficult to maintain.

---

## 9. Writing Style

Documentation must use clear, precise, professional language.

Writers should:

* Prefer short, direct sentences.
* Define specialized terminology when first introduced.
* Use consistent technical terminology.
* Avoid ambiguous pronouns and vague instructions.
* Prefer active voice when it improves clarity.
* Separate mandatory requirements from recommendations.
* Include examples when they materially improve understanding.
* Avoid duplicating content already maintained elsewhere.

Requirements should use consistent language:

| Term | Meaning |
| :--- | :--- |
| **MUST** | Mandatory requirement |
| **MUST NOT** | Prohibited behavior |
| **SHOULD** | Recommended practice that may be departed from with justification |
| **SHOULD NOT** | Practice generally discouraged unless justified |
| **MAY** | Optional practice |

These terms should be used consistently within standards documents. A departure from a mandatory requirement must follow the project's approved exception process.

---

## 10. Documentation Accuracy

Documentation must accurately reflect the current state of Treasure Bank.

Authors must not present:

* Planned functionality as already implemented.
* Proposed architecture as an approved decision.
* Example configuration as verified production configuration.
* Unverified commands as guaranteed to work.
* Future capabilities as currently available.

Where status matters, documents should distinguish between:

* Implemented — Exists in the current implementation.
* Approved — Formally accepted but not necessarily implemented.
* Planned — Intended for future work.
* Deprecated — Still present but no longer recommended.
* Superseded — Replaced by a newer approach or decision.

Status labels should be introduced where they add real value, not mechanically applied to every paragraph.

---

## 11. Architecture Documentation

Architecture documentation must describe the actual and approved architectural structure of Treasure Bank.

It should cover, as applicable:

* Architectural style and rationale.
* System context.
* Major modules and responsibilities.
* Module boundaries.
* Dependency direction.
* Inter-module communication.
* Data ownership.
* External integrations.
* Security boundaries.
* Deployment architecture.
* Important constraints and trade-offs.

Architecture documentation must remain consistent with the approved modular monolith approach.

Modules should be documented in a way that explains their responsibilities and contracts without unnecessarily duplicating their implementation details.

Future microservice extraction should be described as a design consideration where appropriate, not as evidence that every module already operates independently.

---

## 12. Architecture Decision Records

Significant technical decisions should be documented using Architecture Decision Records (ADRs).

An ADR should generally contain:

* Title and identifier.
* Status.
* Context.
* Decision.
* Alternatives considered.
* Consequences.
* Relevant references.

Examples of decisions that may warrant an ADR include:

* Selecting a modular monolith over microservices.
* Choosing a database technology.
* Establishing module dependency rules.
* Selecting an authentication approach.
* Adopting a messaging mechanism.
* Making a significant change to an approved architectural decision.

ADRs should document the reasoning behind decisions, not merely record the final technology choice.

Once accepted, an ADR should normally remain available as a historical record. If a decision changes, create a new ADR that supersedes the earlier one rather than silently rewriting history.

---

## 13. API Documentation

Public or externally consumed APIs must have accurate, discoverable documentation.

Where applicable, API documentation should describe:

* Endpoints and HTTP methods.
* Request parameters and bodies.
* Response schemas.
* Status codes.
* Validation rules.
* Authentication and authorization requirements.
* Error formats.
* Idempotency behavior.
* Pagination and filtering.
* Versioning and compatibility expectations.

OpenAPI should be used where appropriate for HTTP APIs.

API documentation must remain consistent with the actual API implementation. Where feasible, automate its generation or validate it against the API contract.

---

## 14. Code-Level Documentation

Code-level documentation should explain concepts that are not adequately expressed by the implementation itself.

Documentation comments are appropriate for:

* Public APIs.
* Complex business rules.
* Non-obvious constraints.
* Important invariants.
* Security-sensitive behavior.
* Concurrency requirements.
* Complex algorithms.
* Non-obvious design decisions.

For Java code, Javadoc should be used for public APIs when documentation adds meaningful value.

Comments must not become a substitute for meaningful names, simple design, or readable code.

Avoid comments that merely repeat the code or remain inaccurate after the implementation changes.

---

## 15. Development and Setup Documentation

The repository must explain how an authorized contributor can set up the development environment.

Documentation should cover, as applicable:

* Required Java version.
* Maven Wrapper usage.
* Required tools and prerequisites.
* Environment configuration.
* Local database or infrastructure setup.
* Build commands.
* Test commands.
* Application startup.
* Common setup failures.
* Relevant links to additional instructions.

Commands should use the project's actual build conventions. For example, use the committed Maven Wrapper (./mvnw on Unix-like systems) where available rather than requiring contributors to install an arbitrary Maven version.

Platform-specific instructions should be provided where behavior differs materially between operating systems.

---

## 16. Configuration and Environment Variables

Configuration documentation must explain how the application is configured without exposing secrets.

Where relevant, document:

Configuration property names.
Purpose.
Whether a value is required.
Default values, if applicable.
Expected format.
Environment-specific considerations.
How secrets should be supplied.
How missing or invalid configuration is handled.

Use placeholder values in examples.

For example:
```text
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/treasure_bank
SPRING_DATASOURCE_USERNAME=${DB_USERNAME}
SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}
```
The exact property names and supported configuration mechanism must match the implementation.

Never commit real credentials, access tokens, private keys, or production secrets to documentation.

---

## 17. Testing Documentation

Testing documentation must explain how to execute and understand the project's test suites.

It should cover, as applicable:

* Test categories.
* Commands for running unit tests.
* Commands for running integration tests.
* Required containers or external infrastructure.
* Test data setup.
* Environment variables.
* Test report locations.
* Troubleshooting.
* CI testing behavior.

Testing instructions must align with the project's actual Maven configuration and test execution conventions.

If specialized test suites require separate profiles or commands, document them explicitly.

---

## 18. Security Documentation

Security documentation must explain relevant security controls and approved practices without disclosing exploitable secrets or unnecessary sensitive details.

It may cover:

* Authentication and authorization architecture.
* Secret management.
* Security configuration.
* Dependency vulnerability management.
* Secure development requirements.
* Security testing.
* Vulnerability reporting.
* Incident response.

Security-sensitive documents should be reviewed by appropriate personnel.

Security documentation must not encourage contributors to bypass established security controls for convenience.

---

## 19. Operational Documentation

Operational documentation must enable authorized personnel to build, configure, deploy, monitor, troubleshoot, and recover Treasure Bank.

Where applicable, it should cover:

* Build and container image creation.
* Deployment procedures.
* Environment configuration.
* Health checks.
* Logging and monitoring.
* Database migrations.
* Backup and recovery.
* Incident handling.
* Rollback procedures.
* Troubleshooting.

Operational procedures must distinguish between development, testing, staging, and production environments where their requirements differ.

Commands that can cause data loss or service disruption must include appropriate warnings and safeguards.

---

## 20. Diagrams and Visual Documentation

Diagrams should be used when they communicate relationships or workflows more effectively than prose alone.

Appropriate diagrams include:

* System context diagrams.
* Component and module diagrams.
* Dependency diagrams.
* Sequence diagrams.
* Data flow diagrams.
* Deployment diagrams.
* State-transition diagrams.

Diagrams should:

* Have a clear purpose.
* Use consistent terminology.
* Remain readable.
* Be version-controlled where practical.
* Reflect the approved or actual system state.
* Avoid duplicating information unnecessarily.

Text-based diagram formats such as Mermaid are preferred where practical because they can be maintained alongside Markdown and reviewed in Git.

A diagram must not contradict the written architecture description.

---

## 21. Documentation Review

Documentation changes must be reviewed as part of the normal Pull Request process.

Reviewers should verify:

* Accuracy.
* Clarity.
* Technical correctness.
* Appropriate structure.
* Consistency with the implementation.
* Consistency with approved architectural decisions.
* Valid internal and external links.
* Security and privacy considerations.
* Completeness of relevant instructions.

Documentation-only changes may require less extensive technical validation than changes affecting financial logic or security controls. However, they must still receive appropriate review.

---

## 22. Documentation Updates During Development

Documentation must be updated whenever a change materially affects documented behavior, architecture, configuration, or operational procedures.

Examples include:

* Adding or changing a module.
* Changing module dependencies.
* Introducing a new API.
* Changing configuration properties.
* Adding a dependency that affects setup.
* Changing build or test commands.
* Modifying database schemas or migrations.
* Changing deployment behavior.
* Introducing a significant architectural decision.
* Changing security controls.

Documentation updates should be included in the same Pull Request as the implementation whenever practical.

Documentation must not be routinely postponed to an unspecified future milestone.

---

## 23. Documentation Ownership

Every significant document should have a clear maintenance responsibility.

Ownership may be assigned to:

* The team responsible for a subsystem.
* The maintainer of a specific module.
* The owner of an operational procedure.
* The engineering team responsible for a cross-cutting standard.

Ownership does not mean that only one person can modify a document. It means someone is accountable for ensuring the document remains accurate and useful.

Outdated documents should be corrected, archived, or clearly marked as superseded rather than left to mislead contributors.

---

## 24. Links and Cross-References

Documentation should link to related information instead of copying the same content into multiple places.

Internal links should use repository-relative paths where appropriate.

For example:
```text
See the [Testing Standards](../testing/testing-standards.md)
for the project's testing requirements.
```
Authors must verify that links point to the correct destination.

When a document is moved or renamed, references to it should be updated.

The root README should serve as a navigation point to important project documentation.

---

## 25. Documentation Versioning

Documentation stored in the repository must be version-controlled through Git.

Documentation changes should follow the same branching, Pull Request, review, and merge standards as other repository changes.

Significant historical decisions should remain traceable through Git history and, where appropriate, ADRs.

Version-specific documentation should be maintained when different supported versions require materially different instructions or behavior.

---

## 26. Documentation Quality and Maintenance

Documentation should be reviewed periodically when it is especially important to system safety, developer onboarding, or operations.

Review priorities should include:

* Setup instructions.
* Build and test commands.
* Architecture documentation.
* Security guidance.
* Deployment and recovery procedures.
* API contracts.
* Critical operational runbooks.

The goal is not to create recurring documentation work without a reason. It is to identify and correct documentation that has become inaccurate, incomplete, or difficult to use.

---

## 27. Documentation Automation

Where practical, automated checks should validate documentation quality.

Possible checks include:

* Markdown linting.
* Broken-link detection.
* Spelling checks.
* Documentation build validation.
* OpenAPI specification validation.
* Diagram syntax validation.
* Required documentation presence.
* Consistency checks for generated API documentation.

Automation should be integrated into CI incrementally according to value and project maturity.

Automated checks should enforce objective rules wherever possible while leaving technical accuracy and usefulness to human review.

---

## 28. Documentation and Pull Requests

Pull Requests must identify relevant documentation updates.

Where documentation is changed, the author should describe what was updated.

Where documentation is not changed despite a potentially relevant implementation change, the author should be prepared to explain why an update is unnecessary.

Suggested Pull Request checklist:
```text
## Documentation

- [ ] Relevant documentation has been updated.
- [ ] New behavior is documented where necessary.
- [ ] Configuration instructions are accurate.
- [ ] API documentation is updated where applicable.
- [ ] Architecture documentation or ADRs are updated where necessary.
- [ ] Links and examples have been checked.
- [ ] No secrets or sensitive information have been included.
```
Not every Pull Request requires documentation changes. The requirement is to evaluate documentation impact and update it when necessary.

---

## 29. Documentation Exceptions

Exceptions to these standards must be justified when a mandatory requirement cannot reasonably be satisfied.

An exception should identify:

* The requirement being waived.
* The reason for the exception.
* The risk introduced.
* Any compensating measures.
* The responsible owner.
* The expected resolution, where temporary.

Exceptions must not be used as a routine substitute for maintaining documentation.

---

## 30. Documentation Review Checklist

Use the following checklist when reviewing significant documentation changes:
```text
- [ ] The document has a clear purpose.
- [ ] The filename and location follow repository conventions.
- [ ] The structure is appropriate for the document's purpose.
- [ ] The content is accurate and technically correct.
- [ ] Mandatory requirements are clearly distinguishable from recommendations.
- [ ] Examples match the implementation or are explicitly illustrative.
- [ ] Commands and configuration instructions are accurate.
- [ ] Relevant architecture and API information is documented.
- [ ] Cross-references and links are valid.
- [ ] Duplicate sources of truth have been avoided.
- [ ] Sensitive information is not exposed.
- [ ] Related documents have been updated where necessary.
- [ ] The document is discoverable through the appropriate index or README.
- [ ] The content can be maintained as the system evolves.
```
---

## 31. Governing Principle

> Every significant engineering decision, system behavior, development procedure, and operational requirement in Treasure Bank must be documented at the appropriate level so that the system remains understandable, maintainable, and safe to evolve.

Documentation should provide the information engineers need without becoming an unnecessarily large or duplicated collection of files.

---
