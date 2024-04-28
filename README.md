# Test Driven OpenAPI Todo App

A proof of concept for utilizing TDD on schema first project.

## Motivation

I have always struggled to fully utilize TDD and schema first practice during development. This project is a proof of
concept in helping me understand both schema first development for OpenAPI and general practice for TDD.

We will simulate getting requirements from a set of user stories and then use TDD to develop the project.

We will start from a frontend first approach and then move to a backend first approach. However, we will start with the data contract when we start to develop any data related functionality.

## Getting Started

### Prerequisites

- JDK 17 or later
- Gradle

### Building the Project

#### Backend (Kotlin Spring)

See [kotlin/todo-core/README.md](./kotlin/todo-core/README.md) for more information.

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
- [ ] Create authentication and authorization
  - [ ] Add Identity component to the core application
  - [ ] Implement a HTTP basic authentication for the API
  - [ ] Implement RBAC for the todo items
    - [ ] Implement a
- [ ] Create a basic frontend
  - [ ] Implement a basic UI
  - [ ] Implement a basic CRUD functionality
  - 
## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
