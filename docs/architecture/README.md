# Treasure Bank Architecture

This section defines the architectural baseline and structural rules for Treasure Bank.

The documentation is intentionally concise. It describes **boundaries, responsibilities, dependencies, contracts, and architectural decisions** rather than duplicating implementation details that belong in the source code.

## Architecture Documentation

| Document | Purpose |
|---|---|
| [Architecture Overview](architecture-overview.md) | High-level architecture, technology baseline, architectural style, and system structure. |
| [System Context](system-context.md) | System boundary, external actors, infrastructure, and major interactions. |
| [Module Architecture](module-architecture.md) | Modular-monolith boundaries, module ownership, dependency direction, and communication rules. |
| [Architecture Rules](architecture-rules.md) | Non-negotiable constraints that protect module isolation and architectural integrity. |
| [Architecture Decision Records](../adr/README.md) | Significant decisions, their rationale, and consequences. |

## Milestone 1 Baseline

Milestone 1 establishes the engineering foundation on which all future banking domains will be built. The architecture baseline therefore focuses on:

- Modular-monolith structure.
- Explicit module boundaries.
- Public API versus internal implementation boundaries.
- Shared-kernel governance.
- Dependency direction and architectural isolation.
- Application and infrastructure separation.
- Configuration and environment boundaries.
- Error and observability boundaries.
- Test and CI architectural validation.
- Containerized application packaging.

Business-domain architecture will be added as those domains are introduced in later milestones. The documentation must reflect the **implemented and approved architecture**, not speculative future components.

## Documentation Principle

> Architecture documentation explains **what the system is, where its boundaries are, and why important structural decisions exist**. It should not become a duplicate of the source code.

When a significant architectural decision is made, record the rationale in an ADR rather than expanding this document indefinitely.