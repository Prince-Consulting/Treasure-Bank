# Treasure Bank Commit Convention

## 1. Purpose

The purpose of this document is to define the commit message convention for Treasure Bank.
A consistent commit convention ensures that the Git history remains:

* Clear
* Consistent
* Searchable
* Auditable
* Easy to review
* Suitable for automation
* Useful for future maintenance and troubleshooting

Treasure Bank will use the Conventional Commits specification as the foundation of its commit convention.

---

## 2. Commit Message Structure

All commits must follow the following structure:
```text
<type>(<scope>): <description>
```
When a meaningful scope cannot be identified:
```text
docs: update development setup instructions
```
For commits requiring additional context:
```text
fix(transaction): prevent duplicate transaction processing

Transaction requests can be retried by clients when network failures
occur. Add idempotency validation before transaction persistence so
retries cannot create duplicate financial transactions.
```

---

## 3. Commit Types

The following commit types are approved for Treasure Bank.

