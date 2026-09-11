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
- Spring Security (JWT, RBAC, OIDC/JWKS)
- RestAPI & gRPC
- OpenAPI / Swagger
- Maven
- Docker
- IaC
- AWS (VPC, Subnets, ELB, AWS fargate/EC2, AWS RDB, ElasticCache)

## Architecture 

A high-level overview of the Treasure Bank architecture, including the modular monolith structure, domain boundaries, event-driven communication, and AI architecture. 
[View Architecture Documentation](docs/architecture/README.md)

## Core Banking Domains

The planned banking domains and their responsibilities are documented separately and will evolve as implementation progresses. 
[View Core Banking Domains](docs/domains/README.md)

## Development 

Treasure Bank is developed incrementally through milestones and GitHub issues. Each milestone represents a defined stage of the platform's development. 
[View Development Process](docs/development/README.md)

## Roadmap 

The project roadmap defines the planned milestones and the progression from the engineering foundation to a complete AI-powered core banking platform. 
[View Roadmap](docs/roadmap/README.md)

## Architecture Decisions 

Significant architectural and technical decisions are documented using Architecture Decision Records (ADRs). 
[View Architecture Decision Records](docs/adr/README.md)

## Project Status

🚧 Early Development

The project is currently being established from the architectural and engineering foundations upward.

Development will be organized into milestones, with implementation tracked through GitHub issues.

## Documentation 

Project documentation is maintained alongside the implementation. 

- [Architecture](docs/architecture/README.md)
- [Core Banking Domains](docs/domains/README.md)
- [Development](docs/development/README.md)
- [Roadmap](docs/roadmap/README.md)
- [Architecture Decision Records](docs/adr/README.md)

## License 

Treasure Bank is licensed under the **Apache License 2.0**. 
See the [LICENSE](LICENSE) file for the full license text.

## Disclaimer

Treasure Bank is an open-source engineering project and is not intended for production use by a financial institution.
