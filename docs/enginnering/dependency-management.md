# Treasure Bank Dependency Management

## 1. Purpose

Dependency management defines how Treasure Bank declares, versions, updates, verifies, and governs third-party and internal dependencies.

The objective is to keep the codebase **secure, reproducible, maintainable, and compatible with the project's Java 25+ and Spring Boot 4.1.x baseline**.

---

## 2. Dependency Declaration

All application dependencies **must be declared through the project's build system** and committed to version control.

For the Maven-based project:

- Dependencies must be declared in `pom.xml`.
- Versions must not be scattered unnecessarily throughout individual dependency declarations.
- Related dependencies should use a common version property or, preferably, an appropriate **Bill of Materials (BOM)** where one exists.
- Transitive dependencies should not be declared explicitly unless there is a clear architectural or technical reason.
- Every explicitly declared dependency must have a documented purpose within the application.

The dependency tree should remain intentional: if Treasure Bank does not directly use a library, it should generally not be explicitly declared merely because another dependency happens to bring it in.

---

## 3. Dependency Version Management

Treasure Bank must maintain explicit control over dependency versions.

The project should:

- Use the Spring Boot dependency-management mechanism/BOM for dependencies managed by Spring Boot.
- Use Maven BOMs for compatible dependency families where appropriate.
- Centralize manually managed versions.
- Avoid dynamic versions such as:
    - `LATEST`
    - `RELEASE`
    - version ranges such as `[1.0,)`
- Prefer stable, supported releases.
- Keep Java, Spring Boot, Spring Framework, and major infrastructure dependencies aligned with the project's supported technology baseline.

Dependency upgrades must be treated as deliberate engineering changes rather than simply changing arbitrary version numbers.

---

## 4. Direct vs. Transitive Dependencies

A distinction must be maintained between **direct** and **transitive** dependencies.

A dependency should be declared directly when Treasure Bank's source code, tests, build, or runtime configuration directly relies upon it.

Transitive dependencies should generally remain managed by the dependency that introduces them.

If a transitive dependency must be explicitly pinned or overridden because of:

- a security vulnerability;
- a compatibility issue;
- a required bug fix;
- a known undesirable version; or
- another documented technical requirement;

the reason should be documented and the override should be reviewed when the parent dependency is subsequently upgraded.

---

## 5. Dependency Scope

Maven dependency scopes must accurately represent how a dependency is used.

| Scope | Intended use |
|---|---|
| `compile` | Required by the application |
| `runtime` | Required at runtime but not for compilation |
| `test` | Required only by tests |
| `provided` | Supplied by the runtime/environment |
| `import` | Used for BOM dependency management |

Dependencies must not be given broader scopes than necessary.

For example, a testing library must not accidentally become a production runtime dependency.

---

## 6. Dependency Hygiene

The project must regularly identify and remove:

- unused dependencies;
- duplicate dependencies;
- redundant dependencies;
- obsolete libraries;
- unnecessary transitive overrides;
- dependencies that have reached end-of-life;
- dependencies that are no longer compatible with the supported Java/Spring Boot baseline.

A dependency should have a clear reason for existing.

This is particularly important for Treasure Bank because unnecessary dependencies increase the **attack surface, maintenance burden, build complexity, and potential license exposure**.

---

## 7. Dependency Security

Dependency management must include vulnerability detection.

The project should continuously identify known vulnerabilities in both:

- direct dependencies; and
- transitive dependencies.

Dependency vulnerability scanning should be integrated into CI so that vulnerable dependencies can be detected before changes are accepted.

Security findings should be classified according to their actual applicability and severity rather than blindly treating every scanner result as an exploitable vulnerability.

Where a vulnerable dependency cannot immediately be upgraded, the project should document:

1. the affected dependency;
2. the vulnerability;
3. the affected version;
4. the reason an upgrade cannot yet be performed;
5. the mitigation, if available; and
6. the planned resolution.

---

## 8. Dependency Updates

Dependency updates should follow a controlled process.

Before upgrading an important dependency, verify:

- compatibility with Java 25+;
- compatibility with Spring Boot 4.1.x;
- compatibility with related Spring modules;
- breaking changes;
- deprecated APIs;
- security implications;
- test impact;
- runtime impact.

Updates should preferably be **small and independently reviewable** rather than combining numerous unrelated major dependency upgrades into one change.

---

## 9. Reproducible Builds

Dependency resolution must be reproducible.

The project should use Maven's **Maven Wrapper** so developers and CI use the intended Maven version.

The repository must include the required Maven Wrapper files.

Dependency versions must be deterministic so that:

> The same source revision should resolve to the same dependency set under the same repository/environment conditions.

Developers and CI should not depend on an arbitrary locally installed Maven version.

---

## 10. Dependency Verification

Dependency integrity should be considered part of the build supply chain.

Where practical, the project should use Maven's dependency verification capabilities to provide stronger assurance that resolved artifacts are the artifacts expected by the project.

Dependency provenance should also be considered when introducing new libraries.

A dependency should be evaluated for:

- project maturity;
- maintenance activity;
- release history;
- repository/source availability;
- known vulnerabilities;
- licensing;
- ecosystem compatibility;
- transitive dependency footprint.

---

## 11. Dependency Governance

Adding a new dependency should require a clear engineering justification.

Before introducing a dependency, consider whether the required functionality can reasonably be provided by:

1. the Java platform;
2. Spring/Spring Boot;
3. an existing Treasure Bank dependency; or
4. a small amount of maintainable application code.

A new external dependency should not be introduced simply to avoid implementing a trivial piece of functionality.

For significant dependencies, the architectural documentation should explain their role where that dependency materially affects the system architecture.

---

## 12. Dependency Management and CI

Dependency management must integrate with the CI pipeline established for Treasure Bank.

CI should validate at minimum:

- dependency resolution;
- dependency compatibility;
- dependency vulnerabilities;
- build reproducibility;
- test execution after dependency changes.

A dependency-related failure must prevent the pipeline from being considered successful when the failure represents a defined project quality or security gate.

---

## 13. Dependency Management Principle

The governing principle for Treasure Bank is:

> **Every dependency must be intentional, version-controlled, reproducible, supportable, and continuously evaluated for security and compatibility.**

The project should favor a **small, well-understood dependency graph** over accumulating libraries merely for convenience.

---

## 14. Acceptance Criteria

This requirement is fulfilled when:

- [ ] All dependencies are declared through Maven.
- [ ] Dependency versions are centrally and consistently managed.
- [ ] Spring Boot-managed dependencies use the appropriate dependency-management/BOM mechanism.
- [ ] Dynamic dependency versions are prohibited.
- [ ] Direct and transitive dependencies are intentionally distinguished.
- [ ] Dependency scopes accurately reflect dependency usage.
- [ ] Unused and redundant dependencies can be identified and removed.
- [ ] Dependency vulnerability scanning is integrated into CI.
- [ ] A documented process exists for handling vulnerable dependencies that cannot immediately be upgraded.
- [ ] Maven Wrapper is committed and used by the project.
- [ ] Dependency resolution is deterministic.
- [ ] Dependency integrity/provenance is addressed.
- [ ] New dependencies require an engineering justification.
- [ ] Dependency upgrades are tested for Java 25+ and Spring Boot 4.1.x compatibility.
- [ ] Dependency management is integrated with the project's CI quality gates.
- [ ] The dependency graph remains intentionally small and maintainable.
