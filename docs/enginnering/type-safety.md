# Treasure Bank Type Safety Standards

## 1. Purpose

This document defines the type-safety standards for Treasure Bank.

The objective is to use Java's type system to prevent invalid states, ambiguous values, unsafe conversions, and incorrect operations wherever practical.

Treasure Bank is a financial system, so type safety is an important part of correctness, maintainability, and risk reduction.

The guiding principle is:

> Make invalid states difficult or impossible to represent.

Type safety complements:

- Coding Standards.
- Naming Conventions.
- Formatting Standards.
- Linting.
- Static Analysis.
- Testing.
- Architectural Compliance.

---

## 2. Scope

These standards apply to:

- Domain models.
- Value objects.
- Application services.
- Commands and queries.
- DTOs.
- API request and response models.
- Repository contracts.
- Persistence models.
- External-system adapters.
- Events and messages.
- Configuration types.
- Test code.

The standards apply consistently across all Treasure Bank modules.

---

## 3. Type-Safety Principles

Treasure Bank code should:

- Prefer strong types over ambiguous primitives.
- Represent domain concepts explicitly.
- Minimize unchecked operations.
- Avoid raw types.
- Avoid unnecessary casts.
- Prefer immutable types.
- Make illegal states difficult to construct.
- Validate external input at system boundaries.
- Avoid passing loosely typed data deep into the application.
- Prefer compile-time guarantees over runtime checks where practical.
- Make units and semantics explicit.
- Preserve type information across architectural boundaries.

---

## 4. Strong Types Over Ambiguous Primitives

Primitive types should not automatically be used to represent domain concepts.

For example, avoid:

```java
public void transfer(
        String sourceAccountId,
        String destinationAccountId,
        BigDecimal amount) {
}
```
when the concepts have meaningful domain semantics.

Prefer domain-specific types:
```text
public void transfer(
        AccountId sourceAccountId,
        AccountId destinationAccountId,
        Money amount) {
}
```
The stronger representation communicates intent and reduces accidental misuse.

---

## 5. Domain Identifiers

Important domain identifiers should use explicit types where practical.

Examples include:

AccountId
CustomerId
TransactionId
PaymentId
TransferId
LedgerEntryId

Avoid using a generic String or Long everywhere when different identifiers represent different concepts.

For example, this should be avoided where domain-specific types are appropriate:
```text
void transfer(String accountId, String customerId) {
}
```
because the compiler cannot distinguish the two values.

Prefer:
```text
void transfer(AccountId accountId, CustomerId customerId) {
}
```
This allows the compiler to detect accidental argument interchange.

---

## 6. Financial Amounts

Monetary values must use a type that explicitly represents monetary semantics.

double and float must not be used for financial amounts.

Avoid:
```text
double balance;
double transactionAmount;
```
Floating-point arithmetic is inappropriate for representing exact monetary values.

Use an approved monetary representation such as a dedicated Money value object backed by an appropriate exact numeric representation.

Example:
```text
public record Money(BigDecimal amount, Currency currency) {
}
```
The actual Treasure Bank Money implementation should enforce the required monetary invariants.

---

## 7. Currency

Currency must not be represented as an arbitrary string throughout the domain.

Avoid:
```text
String currency = "USD";
```
when currency has domain significance.

Prefer a dedicated type or the appropriate Java currency representation.

Example:
```text
Currency currency;
```
Where the business domain requires stronger guarantees, Treasure Bank may introduce a dedicated CurrencyCode value object.

---

## 8. Units and Quantities

Values with different semantic units should not be represented by indistinguishable primitive types when accidental interchange is possible.

Examples include:

* Monetary amounts.
* Interest rates.
* Percentages.
* Account numbers.
* Identifiers.
* Durations.
* Quantities.

The type should communicate the semantic meaning of the value.

For example, an interest rate should not automatically be treated as an arbitrary double.

---

## 9. Records

Java records should be preferred for immutable data carriers where their semantics are appropriate.

Examples include:
```text
public record AccountId(String value) {
}
```
```text
public record CreateAccountCommand(
        CustomerId customerId,
        AccountType accountType) {
}
```
```text
public record AccountResponse(
        AccountId accountId,
        AccountStatus status) {
}
```
Records should not be used merely to replace every class.

They are particularly appropriate when:

* State is immutable.
* Equality is value-based.
* The type primarily represents data.
* No complex mutable lifecycle is required.

---

## 10. Value Objects

Important domain concepts should be represented as value objects when doing so improves correctness.

Examples include:

* Money
* AccountId
* CustomerId
* EmailAddress
* PhoneNumber
* AccountNumber

A value object should enforce its own invariants where practical.

For example:
```text
public record AccountId(String value) {

    public AccountId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Account ID must not be blank");
        }
    }
}
```
The value object should not permit an invalid instance to be created.

---

## 11. Null Handling

Nullability should be deliberate.

Avoid allowing null to represent multiple meanings such as:

* Unknown.
* Missing.
* Not applicable.
* Not initialized.
* Invalid.

Where meaningful, model those states explicitly.

For example, use an appropriate type or domain state rather than relying on undocumented null semantics.

---

## 12. Optional

Optional should be used deliberately.

It is appropriate for representing an explicitly absent return value where absence is part of the method contract.

Example:
```text
Optional<Account> findById(AccountId accountId);
```
Avoid using Optional indiscriminately for:

* Entity fields.
* Every method parameter.
* Every DTO property.
* Collections.
* Serialization models unless specifically justified.

An empty collection should generally be represented by an empty collection rather than:
```text
Optional<List<Account>>
```
Prefer:
```text
List<Account>
```
when the contract naturally means "zero or more accounts."

---

## 13. Collections

Collection types should communicate the required semantics.

Use:

* List when ordering and duplicates are meaningful.
* Set when uniqueness is required.
* Map when key-based lookup is the intended abstraction.

Avoid exposing concrete collection implementations unnecessarily.

Prefer:
```text
List<Account>
```
over:
```text
ArrayList<Account>
```
at abstraction boundaries unless the concrete implementation is itself part of the contract.

---

## 14. Generic Types

Generic types should be used to preserve compile-time type information.

Avoid raw types:

```text
List accounts;
```
Prefer:
```text
List<Account> accounts;
```
Generic collections should normally specify their element type.

Raw types are prohibited unless a legacy or framework API makes them unavoidable and the exception is documented.

---

## 15. Wildcards

Generic wildcards should be used only when they improve the API's type semantics.

For example, when appropriate:
```text
void processAccounts(List<? extends Account> accounts) {
}
```
Avoid unnecessarily complex generic signatures.

Type-system complexity should provide a meaningful correctness or abstraction benefit.

---

## 16. Unchecked Casts

Unchecked casts should be avoided.

Avoid:
```text
Account account = (Account) value;
```
when a type-safe design can eliminate the cast.

If an unchecked cast is genuinely unavoidable:

* Minimize its scope.
* Validate the assumption.
* Document why it is safe.
* Avoid propagating the unsafe value throughout the application.

Unchecked cast warnings must not simply be suppressed without justification.

---

## 17. Suppression of Type Warnings

Warnings such as:
```text
unchecked
rawtypes
deprecation
```

must not be suppressed merely to obtain a clean build.

Suppression is acceptable only when:

* The operation is genuinely safe.
* There is no practical type-safe alternative.
* The suppression is narrowly scoped.
* The reason is documented where necessary.

Broad suppression of compiler warnings is prohibited.

---

## 18. Enums

Enums should be preferred when a domain concept has a fixed and controlled set of values.

Example:
```text
public enum AccountStatus {
    ACTIVE,
    SUSPENDED,
    CLOSED
}
```
Avoid representing controlled states as arbitrary strings:
```text
String status = "ACTIVE";
```
Enums provide compiler assistance, discoverability, and protection against invalid values.

---
## Sealed Types

Sealed classes and interfaces should be considered when a domain concept has a deliberately restricted set of implementations.

Example:
```text
public sealed interface AccountOperation
        permits Deposit, Withdrawal, Transfer {
}
```
Sealed types are particularly useful when:

* The set of variants is intentionally controlled.
* Exhaustive handling is valuable.
* Domain behavior differs by known variants.

They should not be introduced merely for stylistic reasons.

---

## 20. Pattern Matching

Modern Java pattern matching should be used where it improves type-safe branching and readability.

Example:
```text
if (operation instanceof Withdrawal withdrawal) {
    processWithdrawal(withdrawal);
}
```
Pattern matching should replace unnecessary manual casts where appropriate.

---

## 21. Switch Expressions

Switch expressions should be preferred where they provide clearer and more exhaustive handling.

Example:
```text
return switch (accountStatus) {
    case ACTIVE -> processActiveAccount();
    case SUSPENDED -> handleSuspendedAccount();
    case CLOSED -> rejectClosedAccount();
};
```
Where exhaustive handling is important, the compiler should be allowed to identify newly introduced cases.

---

## 22. Boolean Parameters

Boolean parameters should not be used when the boolean value has ambiguous business meaning.

Avoid:
```text
openAccount(customer, true, false);
```
The meaning of the arguments is unclear.

Prefer an explicit type:
```text
openAccount(customer, AccountOpeningOptions.standard());
```
or an appropriately named domain object.

Boolean parameters may be acceptable for genuinely simple technical options where the meaning is unambiguous.

---

## 23. Multiple Similar Primitive Parameters

Methods containing several parameters of the same primitive type should be reviewed carefully.

Avoid:
```text
transfer(
        String sourceAccountId,
        String destinationAccountId,
        String customerId) {
}
```
when domain-specific types would prevent accidental argument interchange.

Prefer:
```text
transfer(
        AccountId sourceAccountId,
        AccountId destinationAccountId,
        CustomerId customerId) {
}
```

---

## 24. Boundary Validation

External data should be validated and converted into strong internal types at the system boundary.

Examples of boundaries include:

* HTTP requests.
* Messaging systems.
* Database results.
* External banking APIs.
* File imports.
* Configuration.
* User-provided input.

The preferred flow is:
```text
External Data
     ↓
Boundary Validation
     ↓
Typed Representation
     ↓
Application
     ↓
Domain
```
Weakly typed external data should not flow unnecessarily deep into the domain.

---

## 25. DTOs and Domain Types

API DTOs should not automatically become domain models.

External representations should be translated into appropriate internal types.

For example:
```text
HTTP Request
     ↓
CreateAccountRequest
     ↓
CreateAccountCommand
     ↓
Domain
```
This prevents external API representation from dictating internal domain semantics.

---

## 26. Persistence Types

Persistence models should not automatically become domain types.

Where the architecture requires separation, persistence representations should be mapped into domain representations.

For example:
```text
Database Entity
      ↓
Repository Adapter
      ↓
Domain Model
```
This prevents database-specific representation from leaking into the domain.

---

## 27. External Integrations

External-system models should be isolated at integration boundaries.

Avoid allowing third-party SDK types to spread throughout the domain and application layers.

Prefer:
```text
External API
      ↓
External DTO
      ↓
Adapter
      ↓
Internal Type
      ↓
Domain
```
This protects the application from external type-system and API changes.

---

## 28. Domain Invariants

Types should help enforce domain invariants where practical.

For example, if an account identifier cannot be blank, the corresponding type should prevent invalid instances.

If a monetary amount must have a currency, the type should represent both.

If an operation has only a finite set of states, an enum or sealed type should be considered.

The goal is to move correctness from:
```text
Runtime assumption
```
toward:
```text
Compile-time guarantee
```
where practical.

---

## 29. Immutability

Immutable types should be preferred when mutation is not required.

Prefer:

* Records.
* Final fields.
* Immutable collections.
* Value objects.
* Stateless services.

Avoid exposing mutable internal state unnecessarily.

For example, do not return a mutable internal collection merely because it is convenient.

---

## 30. Type-Safe Configuration

Configuration values should be represented using appropriate types where practical.

Avoid treating every configuration value as an arbitrary string throughout the application.

For example:
```text
Duration timeout;
```
is preferable to repeatedly parsing:
```text
String timeout;
```
inside business logic.

Spring Boot configuration binding should be used to create typed configuration objects where appropriate.

---

## 31. Date and Time

Use the Java java.time API.

Avoid legacy date/time APIs unless required for a specific integration.

Prefer semantically appropriate types such as:

* Instant
* LocalDate
* LocalDateTime
* OffsetDateTime
* ZonedDateTime
* Duration
* Period

The selected type must match the actual business meaning.

For example, an event timestamp representing an instant in time should generally use Instant rather than an ambiguous local date/time.

---

## 32. Numeric Types

Numeric types must match their domain semantics.

Examples:

* int/long for appropriate integral values.
* BigDecimal for exact decimal arithmetic where required.
* BigInteger where arbitrary-precision integer values are necessary.

Floating-point types must not be used for exact financial calculations.

Numeric conversion must be explicit when precision or range can be affected.

---

## 33. Serialization and Deserialization

Serialization boundaries must not be allowed to weaken domain type guarantees.

Incoming serialized data should be:

* Parsed.
* Validated.
* Converted to appropriate typed representations.
* Passed into the application/domain layer.

Deserialization must not be treated as proof that the resulting object is semantically valid.

---

## 34. Type Safety and Persistence

Database constraints remain important even when application types are strong.

Type safety must not be considered a replacement for:

* Database constraints.
* Primary keys.
* Foreign keys.
* Unique constraints.
* Check constraints.
* Transactional guarantees.

The strongest design uses multiple layers of protection.

---

## 35. Type Safety and APIs

API contracts should use explicit types.

Request and response models should communicate:

Required values.
Optional values.
Enumerated states.
Identifiers.
Monetary values.
Dates and times.

API contracts should not expose ambiguous generic maps when a structured type can represent the contract.

Avoid:
```text
Map<String, Object>
```
as a substitute for a well-defined domain or API model unless the endpoint genuinely requires dynamic data.

---

## 36. Map and Generic Data Structures

Generic structures such as:
```text
Map<String, Object>
```
should be treated as escape hatches rather than default application models.

They may be appropriate for genuinely dynamic data, but they should not be used to avoid defining proper types.

If a structure has a known schema, create a typed model.

---

## 37. Reflection

Reflection should be used only when justified by framework or infrastructure requirements.

Reflection weakens compile-time guarantees.

Where ordinary language constructs can provide the same behavior, prefer them over reflection.

Framework-driven reflection is acceptable where required by Spring Boot or other approved infrastructure.

Such framework usage should remain outside the domain where practical.

---

## 38. Dependency Injection

Dependency injection should preserve explicit types.

Prefer constructor injection:
```text
public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
}
```
over service-locator or generic lookup patterns.

Dependencies should have meaningful interfaces or concrete types according to the architecture.

---

## 39. Generic Exceptions

Generic exception types should not be used as substitutes for meaningful domain or application types.

Avoid:
```text
throw new RuntimeException("Something went wrong");
```
when a meaningful exception type can communicate the failure.

The detailed exception hierarchy is defined by the Error-Handling Framework.

---

## 40. Type Safety in Tests

Tests should use the same strong types as production code.

Tests must not bypass domain types merely for convenience.

Avoid constructing invalid objects through unsafe casts or generic maps when the production API requires strong types.

Tests may intentionally construct invalid boundary input when testing validation behavior, but such invalidity should be explicit and confined to the relevant test.

---

## 41. Compiler Warnings

The project should treat relevant compiler warnings as engineering signals.

Developers should not routinely suppress warnings to obtain a clean build.

Warnings should be:

* Understood.
* Fixed where practical.
* Deliberately accepted when unavoidable.
* Narrowly suppressed when justified.

The project should progressively minimize unexplained compiler warnings.

---

## 42. Deprecated APIs

Deprecated APIs should not be introduced into new Treasure Bank code unless there is a documented technical reason.

Existing deprecated usage should be identified and progressively removed.

Static analysis and compiler diagnostics should help identify deprecated usage.

---

## 43. Type-Safety Exceptions

A type-safety exception is acceptable only when:

* The framework or external API requires it.
* A legacy integration makes it unavoidable.
* The alternative introduces disproportionate complexity.
* The operation is demonstrably safe.
* 
Exceptions should be:

* Narrowly scoped.
* Documented where necessary.
* Prevented from spreading through the application.

---

## 44. Enforcement

Type safety is enforced through multiple mechanisms:

* Java compiler.
* Generic type checking.
* Compiler warnings.
* Static analysis.
* Linting.
* Code review.
* Tests.
* Architectural boundaries.

The compiler is the primary authority for Java's type system.

Static analysis and linting provide additional safeguards.

---

## 45. Anti-Patterns

The following patterns should generally be avoided:

* Raw collections.
* Unchecked casts.
* Broad warning suppression.
* Map<String, Object> as a default application model.
* Generic String identifiers throughout the domain.
* double or float for monetary values.
* Arbitrary strings for controlled domain states.
* Ambiguous boolean parameters.
* Excessive primitive parameters.
* Unvalidated external data flowing into the domain.
* Third-party SDK types leaking throughout the application.
* Persistence entities being treated as domain models without architectural justification.
* Excessive reflection.
* Generic object wrappers used to avoid defining proper types.



