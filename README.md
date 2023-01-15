# Test Driven OpenAPI Todo App

A proof of concept for utilizing TDD on schema first project.

## Motivation

I have always struggled to fully utilize TDD and schema first practice during development. This project is a proof of
concept in helping me understand both schema first development for OpenAPI and general practice for TDD.

For development that start from the frontend, this project is a proof of concept in introducing TDD and CDC when it
comes to integrating with the backend.

## Technology

### Frontend

- React JS

### Backend

- Kotlin Spring

### Testing/Mocking Tools

- Spring Cloud Contract
- Prism
- Postman

## Development Plan

1. Implement the frontend with TDD
2. Create contract test and stub to continue frontend development
3. Implement the API according to the contract test
4. Rerun frontend tests with the now implemented API as the provider
5. Refactor if needed
