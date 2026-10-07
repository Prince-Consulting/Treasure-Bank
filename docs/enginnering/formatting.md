# Treasure Bank Formatting Standards

## 1. Purpose

This document defines the source-code formatting standards for Treasure Bank.

The objective is to ensure that all developers and automated systems produce consistently formatted code without relying on individual preferences.

Formatting is intentionally separated from coding standards, linting, and static analysis:

- Coding standards define how code should be designed and written.
- Formatting standards define how code should be visually structured.
- Linting defines detectable code-quality and style violations.
- Static analysis identifies deeper correctness, maintainability, and architectural concerns.

This document is the authoritative source for formatting decisions.

---

## 2. Scope

These formatting standards apply to:

- Java source files.
- Java test files.
- Spring Boot application code.
- Configuration classes.
- Repository and adapter implementations.
- Domain, application, infrastructure, and interface layers.
- Test fixtures and test-support code.
- Build-related Java source where applicable.

They complement the Treasure Bank Coding Standards and Naming Conventions.

They do not define:

- Business logic standards.
- Naming conventions.
- Dependency-management policy.
- Git commit conventions.
- Pull-request standards.
- Testing strategy.
- Architectural rules.

Those concerns are defined by their respective engineering standards.

---

## 3. Formatting Baseline

Treasure Bank uses:

**Google Java Format**

as the baseline Java formatter.

Formatting must be deterministic and must not depend on an individual developer's IDE configuration.

Developers should configure their IDEs to use the project-approved formatter rather than relying on personal formatting preferences.

The formatter configuration must be treated as a project standard.

---

## 4. Indentation

Java source code must use the indentation produced by the approved formatter.

Developers must not introduce manual indentation conventions that conflict with the formatter.

Example:

```java
public class AccountService {

    public Account openAccount(CreateAccountCommand command) {
        validate(command);
        return accountRepository.save(createAccount(command));
    }
}
```
---

## 5. Braces

Use braces consistently for:

* Classes.
* Methods.
* Constructors.
* Conditional statements.
* Loops.
* Exception handling blocks.

Example:
```text
if (account.isActive()) {
    processAccount(account);
}
```
Do not omit braces merely because a statement contains a single line.

Avoid:
```text
if (account.isActive())
    processAccount(account);
```
The purpose is to keep formatting consistent and reduce the possibility of errors when code is subsequently modified.

---

## 6. Line Length

Developers should allow the approved formatter to determine line wrapping.

Do not manually introduce awkward line breaks solely to satisfy an arbitrary personal line-length preference.

Example:
```text
Account account =
        accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
```
Readable wrapping is preferred over compressed or excessively dense expressions.

When a statement becomes difficult to understand even after formatting, the preferred solution is often to simplify the code rather than manipulate formatting.

---

## 7. Method Declarations

Method declarations must be formatted consistently by the approved formatter.

Example:
```text
public Account openAccount(
        CustomerId customerId,
        Money initialDeposit,
        AccountType accountType) {

    // implementation
}
```
Do not manually align parameters or annotations for visual effect.

The formatter is responsible for determining appropriate line breaks and indentation.

---

## 8. Method Calls

Method calls should remain readable when they span multiple lines.

Example:
```text
Account account =
        accountService.openAccount(
                customerId,
                initialDeposit,
                accountType);
```
Avoid manually compressing complex calls into excessively long lines.

If a method call becomes difficult to read, consider whether the underlying method has too many parameters or whether a domain object should represent the operation.

Formatting must not be used to conceal poor API design.

---

## 9. Imports

Imports must be:

Explicit.
Automatically organized.
Free from unnecessary entries.
Consistently ordered by the approved formatter/IDE configuration.

Unused imports must not remain in committed code.

Wildcard imports should not be introduced unless explicitly required by an approved project convention or framework constraint.

Example:
```text
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
```
Import ordering must not be manually customized on an individual developer's machine.

---

## 10. Annotations

Annotations should appear directly above the declaration to which they apply.

Example:
```text
@Service
public class AccountService {
}
```
Multiple annotations should remain consistently grouped:
```text
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
}
```
Do not introduce arbitrary blank lines between annotations and their target declaration.

---

## 11. Class Structure

Classes should follow a predictable visual structure.

Where applicable, use the following general ordering:

* Class declaration.
* Constants.
* Instance fields.
* Constructors.
* Public methods.
* Protected methods.
* Package-private methods.
* Private methods.
* Nested types.

Example:
```text
public class AccountService {

    private static final int MAX_ACCOUNTS = 10;

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account openAccount(CreateAccountCommand command) {
        return createAccount(command);
    }

    private Account createAccount(CreateAccountCommand command) {
        // implementation
    }
}
```
The exact member ordering should remain consistent within a class.

Formatting should make the responsibilities of a class visually easy to scan.

---

## 12. Blank Lines

Blank lines should separate logically distinct sections of code.

Use blank lines to improve readability between:

* Fields and constructors.
* Constructors and methods.
* Related but distinct operations.
* Logical blocks within a method when appropriate.

Do not use excessive blank lines.

Avoid formatting such as:
```text
private final AccountRepository repository;



public AccountService(AccountRepository repository) {
}
```
Prefer:
```text
private final AccountRepository repository;

public AccountService(AccountRepository repository) {
}
```

---

## 13. Whitespace

Whitespace must be consistent and should be managed by the approved formatter.

Avoid:

* Trailing whitespace.
* Multiple unnecessary spaces.
* Inconsistent spacing around operators.
* Manual alignment that conflicts with automated formatting.
* Spaces inside unnecessary parentheses.

Example:
```text
if (balance >= withdrawalAmount) {
    return true;
}
```
Not:
```text
if(balance>=withdrawalAmount){
    return true;
}
```

---

## 14. Expressions

Expressions should remain readable.

Avoid formatting tricks that make expressions visually dense.

Prefer:
```text
boolean eligible =
        account.isActive()
                && account.hasSufficientFunds(amount)
                && customer.isVerified();
```
over forcing a long expression onto one line.

If an expression remains difficult to understand after formatting, simplify the expression or extract meaningful domain logic.

---

## 15. Records

Records must use the standard formatter.

Example:
```text
public record AccountResponse(
        String accountId,
        String accountNumber,
        AccountStatus status) {
}
```
Record components should remain semantically grouped.

Do not manually align record components.

---

## 16. Enums

Enum declarations should be formatted consistently.

Example:
```text
public enum AccountStatus {
    ACTIVE,
    SUSPENDED,
    CLOSED
}
```
When enum constants contain additional declarations or behavior, the standard formatter remains authoritative.

---

## 17. Lambda Expressions

Lambda expressions must be formatted for readability.

Example:
```text
accounts.stream()
        .filter(Account::isActive)
        .map(Account::getId)
        .toList();
```
Avoid unnecessary formatting complexity.

Where a lambda becomes difficult to read, consider extracting the behavior into a named method.

---

## 18. Streams

Stream pipelines should remain visually readable.

Example:
```text
return accounts.stream()
        .filter(Account::isActive)
        .filter(account -> account.hasSufficientFunds(amount))
        .map(Account::getId)
        .toList();
```
Do not compress complex pipelines into a single unreadable line.

Formatting must expose the logical stages of a pipeline.

---

## 19. Exception Handling

Exception handling blocks should be clearly separated.

Example:
```text
try {
    accountRepository.save(account);
} catch (DataAccessException exception) {
    throw new AccountPersistenceException(exception);
}
```
Avoid empty catch blocks.

Do not use formatting to obscure broad or inappropriate exception handling.

The complete exception taxonomy and handling strategy are defined by the Error-Handling Framework.

---

## 20. Comments

Comments should be formatted consistently and should explain intent where necessary.

Example:
```text
// The ledger entry must be created before the account balance is updated.
createLedgerEntry(transaction);
updateAccountBalance(account, transaction);
```
Avoid comments that merely restate obvious code:
```text
// Increment balance
balance++;
```
Comments should not become a substitute for clear code.

Documentation comments should follow standard JavaDoc conventions.

---

## 21. JavaDoc

Public APIs that require documentation should use properly formatted JavaDoc.

Example:
```text
/**
 * Opens a new account for the specified customer.
 *
 * @param customerId the customer who owns the account
 * @return the newly opened account
 */
public Account openAccount(CustomerId customerId) {
    // implementation
}
```
JavaDoc formatting must remain consistent across the project.

---

## 22. Test Formatting

Production and test code must follow the same formatting standards.

Example:
```text
@Test
void shouldRejectWithdrawalWhenFundsAreInsufficient() {
    Account account = createAccountWithBalance(Money.of("100.00"));

    assertThatThrownBy(() -> account.withdraw(Money.of("150.00")))
            .isInstanceOf(InsufficientFundsException.class);
}
```
Test formatting must not be treated as a lower standard than production code.

Readable test structure is particularly important because tests are part of the project's executable documentation.

---

## 23. Configuration Code

Java configuration classes must follow the same formatting rules as application code.

Example:
```text
@Configuration
public class AccountConfiguration {

    @Bean
    public AccountService accountService(AccountRepository repository) {
        return new AccountService(repository);
    }
}
```
Formatting must not vary between business and configuration modules.

---

## 24. Configuration Files

Non-Java configuration files should use the established syntax and project formatter where an appropriate formatter exists.

This includes:

* YAML.
* Properties.
* JSON.
* XML.
* Docker-related configuration.
* Build configuration.

Formatting rules for each format should be automated where practical.

The project must avoid introducing multiple competing formatting conventions for the same file type.

---

## 25. IDE Configuration

Developers may use different IDEs, but committed source code must conform to the project formatting standard.

IDE-specific formatting preferences must not override project standards.

Recommended developer configuration:

* Enable automatic formatting using the approved formatter.
* Enable import organization.
* Enable trailing-whitespace removal.
* Enable formatting on save where practical.
* Use project-level configuration where supported.

The repository should provide the necessary formatter configuration so developers do not have to manually reproduce formatting settings.

---

## 26. Generated Code

Generated source code should not be manually reformatted unless the generation process explicitly supports project formatting.

Generated code should be clearly identifiable where practical.

Formatting rules for generated code must not compromise reproducibility of the generation process.

---

27. Formatting and Architecture

Formatting must never be used to bypass architectural boundaries.

For example, code should not be compressed or structured in a way that hides:

* Cross-module dependencies.
* Infrastructure dependencies.
* Business logic inside controllers.
* Persistence access from inappropriate layers.
* Framework dependencies inside domain code.

Readable formatting should make architectural responsibilities easier to identify.

---

## 28. Formatting and Code Review

Pull requests should not contain unrelated formatting churn.

A developer should not reformat unrelated files merely because they are modifying another area of the codebase.

Formatting changes should generally be isolated from functional changes unless the formatting is required by the formatter or the modified code.

Large formatting-only changes should be reviewed separately where practical.

---

## 29. Formatting Enforcement

Formatting should eventually be enforced automatically.

The intended enforcement path is:

* Developer IDE formatting.
* Local build/verification.
* Automated formatting check.
* CI validation.
* Pull-request validation.

A pull request should not be considered compliant if committed source files violate the project formatting standard.

The specific build-plugin and CI implementation belongs to the Linting and Static Analysis requirements.

---

## 30. Formatting Exceptions

Exceptions to these standards must have a technical reason.

An exception should:

Be intentional.
Be narrowly scoped.
Avoid creating a new project-wide convention.
Be documented when it materially affects maintainability or tooling.

Individual developer preference is not sufficient justification for a formatting exception.

