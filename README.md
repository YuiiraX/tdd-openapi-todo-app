# Test Driven OpenAPI Todo App

A proof of concept for utilizing TDD on schema first project.

## Motivation

I have always struggled to fully utilize TDD and schema first practice during development. This project is a proof of
concept in helping me understand both schema first development for OpenAPI and general practice for TDD.

We will simulate getting requirements from a set of user stories and then use TDD to develop the project.

We will start from a frontend first approach and then move to a backend first approach. However, we will start with the data contract when we start to develop any data related functionality.

## Technology

### Frontend

- React JS

### Backend

- Kotlin Spring

### Testing/Mocking Tools

- JUnit 5 (Spring Boot Test)
- MockK

### Stages

See [backend-design.md](./docs/backend-design.md) for the backend design.

- [x] Create a basic OpenAPI schema
- [x] Create a basic backend
  - [x] Implement a basic CRUD functionality
- [ ] Create a basic frontend
  - [ ] Implement a basic UI
  - [ ] Implement a basic CRUD functionality