# Treasure Bank

> An AI-powered, event-driven core banking platform built with Java and Spring Boot.

[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/status-in%20development-orange.svg)]()
[![Architecture](https://img.shields.io/badge/architecture-modular%20monolith-purple.svg)]()

## Overview

Treasure Bank is an open-source project focused on building a modern core banking system from the ground up.

The project is being designed as a **modular monolith** with an **event-driven architecture**, with AI integrated as a core intelligence layer of the platform.

The system will progressively cover core banking capabilities including customers, accounts, transactions, payments, lending, accounting, risk, compliance, and AI-powered banking intelligence.

## Technology Stack

- Java 25+
- Spring Boot 4.1.0
- PostgreSQL 18+
- Redis
- Apache Kafka
- Spring Security
- OpenAPI / Swagger
- Maven
- Docker

## Architecture

The initial architecture follows a modular monolith approach.

```text
                    Treasure Bank
                         |
                +--------+--------+
                |                 |
          Banking Modules     AI Intelligence
                |                 |
                +--------+--------+
                         |
                  Event-Driven Core
                         |
                       Kafka
                         |
                      Ledger
```

The system will maintain clear boundaries between banking domains while using events for communication and integration.

The financial ledger will remain the authoritative source of financial state.

Planned Core Domains
Customer & Identity
Accounts & Deposits
Product Management
Transactions
Payments
Ledger & Accounting
Lending & Credit
Risk & Compliance
Fraud
AI Intelligence

Additional domains will be introduced as the project evolves.

## Project Status

🚧 Early Development

The project is currently being established from the architectural and engineering foundations upward.

Development will be organized into milestones, with implementation tracked through GitHub issues.

## Development Approach

The project will be developed incrementally:

Backlog
   ↓
Milestone
   ↓
Issue
   ↓
Design
   ↓
Implementation
   ↓
Testing
   ↓
Review
   ↓
Merge

Architecture decisions and significant technical decisions will be documented as the project evolves.

## Documentation

Project documentation will be maintained alongside the implementation.

---

## Disclaimer

Treasure Bank is an open-source engineering project and is not intended for production use by a financial institution.
