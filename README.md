# Test Driven OpenAPI Todo App

A proof of concept for utilizing TDD on schema first project.

## Motivation

I have always struggled to fully utilize TDD and schema first practice during development. This project is a proof of
concept in helping me understand both schema first development for OpenAPI and general practice for TDD.

We will simulate getting requirements from a set of user stories and then use TDD to develop the project.

We will start from a frontend first approach and then move to a backend first approach. However, we will start with the data contract when we start to develop any data related functionality.

## User Stories

These are the user stories that we will be using to develop the project. Details of the user stories can be found in the [user stories](docs/user-stories.md) document.

- [ ] As a user, I want to be able to create a new todo item by entering a title and description, so that I can keep track of my tasks.
- [ ] As a user, I want to be able to view a list of all my todo items, so that I can see what tasks I have to complete.
- [ ] As a user, I want to be able to mark a todo item as complete, so that I can keep track of my progress.
- [ ] As a user, I want to be able to delete a todo item, so that I can remove tasks that are no longer relevant.
- [ ] As a user, I want to be able to edit the title and description of a todo item, so that I can update the details of a task.
- [ ] As a user, I want to be able to sort my todo items by title, so that I can easily find a specific task.
- [ ] As a user, I want to be able to filter my todo items by status (complete or incomplete), so that I can focus on a specific set of tasks.

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
