# Treasure Bank Naming Conventions

## 1. Purpose

This document defines naming conventions for Treasure Bank source code, tests, configuration, and architectural components.

Consistent naming makes the codebase easier to understand, search, review, maintain, and extend.

Names should communicate intent and reflect the language of the business domain.

## 2. Scope

These conventions apply to all Treasure Bank modules and supporting code.

They complement the Coding Standards and are especially important for maintaining consistency across the modular-monolith architecture.

## 3. General Principles

Names must be:

- Descriptive.
- Consistent.
- Domain-oriented.
- Unambiguous within their context.
- Appropriate to the responsibility of the type or member.
- Free from unnecessary abbreviations.

Prefer a clear, slightly longer name over a short name whose meaning must be inferred.

One business concept should have one canonical name unless different names represent genuinely different concepts.

## 4. Package Naming

Java package names must:

- Be lowercase.
- Use the organization's established reverse-domain namespace.
- Reflect architectural and domain boundaries.
- Avoid underscores and mixed-case names.

Example:

```text
com.princeconsulting.treasurebank
```

Module structure should reflect the domain and architectural boundaries:

```text
com.princeconsulting.treasurebank.account.domain
com.princeconsulting.treasurebank.account.application
com.princeconsulting.treasurebank.account.infrastructure
com.princeconsulting.treasurebank.account.interfaces
```

Package names should describe responsibility rather than implementation detail alone.

## 5. Modules

Business modules should use concise, domain-oriented names.

Examples:

```text
account
customer
payment
transfer
ledger
```

Avoid vague module names such as:

```text
common
misc
stuff
core2
module1
```

unless the name has a clearly defined architectural meaning.

## 6. Classes

Classes use `PascalCase`.

Examples:

```java
Account
Customer
Transaction
AccountRepository
PaymentGateway
```

Class names should communicate what the type represents or does.

Avoid vague names such as:

```text
Manager
Helper
Util
Data
Info
Processor
```

unless the name accurately describes a clearly defined responsibility.

## 7. Interfaces

Interfaces use `PascalCase`.

Do not prefix interfaces with `I`.

Prefer:

```java
AccountRepository
PaymentGateway
NotificationSender
```

rather than:

```java
IAccountRepository
IPaymentGateway
INotificationSender
```

Implementation names should communicate their concrete responsibility.

Prefer meaningful names such as:

```java
JpaAccountRepository
StripePaymentGateway
```

when appropriate.

Avoid automatically adding `Impl`:

```text
AccountRepositoryImpl
```

unless there is a specific reason that the generic implementation name is meaningful.

## 8. Records

Records use `PascalCase` and should be named after what they represent.

Examples:

```java
Money
AccountSummary
CreateAccountCommand
AccountResponse
```

Avoid generic names such as:

```text
AccountData
AccountInfo
AccountObject
```

unless those names have specific semantic meaning.

## 9. Enums

Enum types use `PascalCase`.

Enum constants use `UPPER_SNAKE_CASE`.

Example:

```java
enum AccountStatus {
    ACTIVE,
    SUSPENDED,
    CLOSED
}
```

Enum values should represent meaningful domain states or categories.

## 10. Methods

Methods use `camelCase`.

Methods should normally begin with a verb or otherwise clearly communicate their intent.

Examples:

```java
openAccount()
closeAccount()
findAccountById()
calculateAvailableBalance()
validateTransaction()
```

Boolean methods should read naturally:

```java
isActive()
hasSufficientFunds()
canWithdraw()
```

Avoid vague method names such as:

```text
process()
handle()
execute()
doSomething()
```

unless the surrounding abstraction makes the intent explicit and the name is appropriate to the responsibility.

## 11. Variables and Parameters

Variables and parameters use `camelCase`.

Examples:

```java
accountId
availableBalance
transactionAmount
customerRepository
```

Names should describe the value rather than its implementation.

Avoid cryptic abbreviations:

```text
acctId
bal
txnAmt
custRepo
```

unless the abbreviation is an established and unambiguous domain term.

## 12. Constants

Constants use `UPPER_SNAKE_CASE`.

Examples:

```java
MAX_TRANSACTION_AMOUNT
DEFAULT_CURRENCY
ACCOUNT_NUMBER_LENGTH
```

Constant names must communicate their meaning.

Avoid unexplained constants such as:

```java
VALUE_1
LIMIT
NUMBER
DEFAULT
```

when a more precise name is possible.

## 13. Exceptions

Exception types use `PascalCase` and normally end with `Exception`.

Examples:

```java
AccountNotFoundException
InsufficientFundsException
InvalidTransactionException
```

Exception names should communicate the condition represented by the exception.

Avoid generic names such as:

```text
AccountError
AccountProblem
GenericException
```

The complete error taxonomy and exception semantics are governed by the Error-Handling Framework.

## 14. Spring Components

Spring component names should communicate their architectural responsibility.

Examples:

```text
AccountController
OpenAccountService
AccountConfiguration
JpaAccountRepository
PaymentGatewayAdapter
```

Avoid using a generic component name when the component's architectural role can be expressed clearly.

## 15. DTOs, Commands, Queries, and Responses

Names should communicate the object's purpose and direction.

Examples:

```text
CreateAccountCommand
OpenAccountCommand
FindAccountQuery
AccountResponse
AccountSummary
```

Do not use a generic `DTO` suffix when the object's actual purpose can be expressed more precisely.

For example, prefer:

```text
AccountResponse
```

over:

```text
AccountDTO
```

when the type specifically represents an API response.

## 16. Test Naming

Test classes should correspond clearly to the production type or behavior under test.

Example:

```java
class AccountServiceTest
```

Test methods should communicate the behavior and expected result.

Examples:

```java
shouldOpenAccountWhenValidRequestIsProvided()

shouldRejectWithdrawalWhenFundsAreInsufficient()

shouldNotOpenAccountWhenCustomerIsSuspended()
```

Test names should make the scenario understandable without requiring the reader to inspect the test implementation.

## 17. Architectural Naming

Names should reinforce the modular-monolith architecture.

Example:

```text
account/
├── domain/
│   ├── Account.java
│   ├── AccountStatus.java
│   └── AccountRepository.java
│
├── application/
│   ├── OpenAccountService.java
│   └── CloseAccountService.java
│
├── infrastructure/
│   └── persistence/
│       └── JpaAccountRepository.java
│
└── interfaces/
    └── rest/
        ├── AccountController.java
        └── AccountResponse.java
```

A type's name and package should make its architectural responsibility reasonably clear.

## 18. Domain Terminology

Treasure Bank must maintain consistent domain terminology.

The same concept should not be arbitrarily named differently across modules.

For example, if the canonical domain concept is `Customer`, the system should not use `Client`, `User`, and `Customer` interchangeably unless those terms represent distinct domain concepts.

Canonical terminology should remain consistent across:

- Java code.
- APIs.
- Database structures.
- Events and messages.
- Tests.
- Documentation.

## 19. Abbreviations

Avoid abbreviations unless they are:

- Widely understood.
- Established within the banking or technical domain.
- Unambiguous in context.

Examples of potentially acceptable domain abbreviations may include established terms such as `API`, `URL`, or `ID`.

When in doubt, prefer the full descriptive term.

## 20. Anti-Patterns

Avoid:

- Single-letter variables outside very small, conventional scopes.
- Cryptic abbreviations.
- Generic `Util`, `Helper`, and `Manager` naming.
- Redundant names.
- Names that reveal implementation details unnecessarily.
- Names that conflict with established domain terminology.
- Inconsistent synonyms for the same concept.

For example, avoid:

```text
AccountServiceAccountService
AccountControllerController
AccountRepositoryRepository
```

## 21. Naming and Code Review

Naming should be reviewed for:

- Clarity.
- Domain accuracy.
- Consistency.
- Architectural meaning.
- Long-term maintainability.

A technically correct implementation with misleading naming should not be considered complete.

## 22. Enforcement

Naming conventions are part of the Treasure Bank engineering baseline.

Where practical, naming-related rules should be reinforced through:

- Static analysis.
- Linting.
- Automated architecture checks.
- Code review.
- Test conventions.
- CI validation.

Not every naming rule can or should be mechanically enforced. Human review remains necessary for domain terminology and semantic clarity.
