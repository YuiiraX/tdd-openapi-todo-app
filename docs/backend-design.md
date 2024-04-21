# Backend Design

## 0. Introduction

This document outlines the design of the backend services for the Todo application.
The document is aim to provide a step-by-step journey showing the evolution of the backend services.

## 1. Core Service

### 1.1. Requirements

- [x] Todo CRUD operations
- [x] Filter todos (by status, due date, etc.)
- [x] Sort todos (by name, status, due date, etc.)

### 1.2. Model and System Design

![backend-design-step1.png](./backend-design-step1.png)

### 1.3. API

```yaml
openapi: 3.0.3
info:
  title: Todo Core API
  description: Core API for the Todo application
  version: 1.1.0
servers:
  - url: 'https://localhost:{port}'
    description: Local server
    variables:
      port:
        default: '8080'
        description: The port number

tags:
  - name: todos
    description: Operations related to todos

paths:
  /todos:
    get:
      summary: Get all todos
      operationId: getAllTodos
      tags:
        - todos
      parameters:
        - name: statuses
          in: query
          description: The statuses of the todos to filter by
          schema:
            type: array
            items:
              $ref: '#/components/schemas/TodoItemStatus'
        - name: dueDateStart
          in: query
          description: The start of the due date range to filter by
          schema:
            type: string
            format: date-time
        - name: dueDateEnd
          in: query
          description: The end of the due date range to filter by
          schema:
            type: string
            format: date-time
        - name: sort
          in: query
          description: The field to sort by
          schema:
            type: string
            enum:
              - name
              - status
              - dueDate
        - name: order
          in: query
          description: The order to sort by
          schema:
            type: string
            enum:
              - asc
              - desc
      responses:
        '200':
          description: A list of todos
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/TodoItem'
    post:
      summary: Create a todo
      operationId: createTodo
      tags:
        - todos
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/NewTodoItem'
      responses:
        '201':
          description: The created todo
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TodoItem'
  /todos/{id}:
    get:
      summary: Get a todo by ID
      operationId: getTodoById
      tags:
        - todos
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the todo
          schema:
            type: string
            format: uuid
      responses:
        '200':
          description: The todo
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TodoItem'
    put:
      summary: Update a todo
      operationId: updateTodo
      tags:
        - todos
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the todo
          schema:
            type: string
            format: uuid
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/TodoItem'
      responses:
        '200':
          description: The updated todo
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TodoItem'
    delete:
      summary: Delete a todo
      operationId: deleteTodo
      tags:
        - todos
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the todo
          schema:
            type: string
            format: uuid
      responses:
        '204':
          description: The todo was deleted

components:
  schemas:
    TodoItem:
      type: object
      properties:
        id:
          type: string
          format: uuid
        name:
          type: string
        description:
          type: string
        dueDate:
          type: string
          format: date-time
        status:
          $ref: '#/components/schemas/TodoItemStatus'
      example:
        id: 'aa8970d2-8564-4f5a-8f4a-9e95a9b92298'
        name: 'Buy groceries'
        description: 'Buy milk, eggs, and bread'
        dueDate: '2021-12-31T23:59:59Z'
        status: 'NOT_STARTED'
    NewTodoItem:
      type: object
      properties:
        name:
          type: string
          x-field-extra-annotation: "@jakarta.validation.constraints.NotBlank"
        description:
          type: string
        dueDate:
          type: string
          format: date-time
        status:
          $ref: '#/components/schemas/TodoItemStatus'
      required:
        - name
        - status
      example:
        name: 'Buy groceries'
        description: 'Buy milk, eggs, and bread'
        dueDate: '2021-12-31T23:59:59Z'
        status: 'NOT_STARTED'
    TodoItemStatus:
      type: string
      enum:
        - NOT_STARTED
        - IN_PROGRESS
        - COMPLETED
```

### 1.4. Database

```sql
CREATE TABLE todo_items
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    due_date    TIMESTAMP WITH TIME ZONE,
    status      VARCHAR(255) NOT NULL
);
```

## 2. Basic Identity and Access Management

### 2.1 Requirements

- [x] Basic HTTP Authentication
- [x] User registration
- [ ] Identity Models persistence

### 2.2. Model and System Design

### 2.3. API

- Use Basic HTTP Authentication for simplicity

```yaml
openapi: 3.0.3
info:
  title: Todo Core API
  description: Core API for the Todo application
  version: 1.2.0
servers:
  - url: '{protocol}://localhost:{port}/v1'
    description: Local server
    variables:
      protocol:
        enum:
          - http
          - https
        default: 'http'
      port:
        default: '8080'
tags:
  - name: todos
    description: Operations related to todos
  - name: identity
    description: Operations related to Identity
security:
  - BasicAuth: []
paths:
  /todos:
    get:
      summary: Get all todos
      operationId: getAllTodos
      tags:
        - todos
      parameters:
        - name: statuses
          in: query
          description: The statuses of the todos to filter by
          schema:
            type: array
            items:
              $ref: '#/components/schemas/TodoItemStatus'
        - name: dueDateStart
          in: query
          description: The start of the due date range to filter by
          schema:
            type: string
            format: date-time
        - name: dueDateEnd
          in: query
          description: The end of the due date range to filter by
          schema:
            type: string
            format: date-time
        - name: sort
          in: query
          description: The field to sort by
          schema:
            type: string
            enum:
              - name
              - status
              - dueDate
        - name: order
          in: query
          description: The order to sort by
          schema:
            type: string
            enum:
              - asc
              - desc
      responses:
        '200':
          description: A list of todos
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/TodoItem'
    post:
      summary: Create a todo
      operationId: createTodo
      tags:
        - todos
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/NewTodoItem'
      responses:
        '201':
          description: The created todo
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TodoItem'
  /todos/{id}:
    get:
      summary: Get a todo by ID
      operationId: getTodoById
      tags:
        - todos
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the todo
          schema:
            type: string
            format: uuid
      responses:
        '200':
          description: The todo
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TodoItem'
    put:
      summary: Update a todo
      operationId: updateTodo
      tags:
        - todos
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the todo
          schema:
            type: string
            format: uuid
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/TodoItem'
      responses:
        '200':
          description: The updated todo
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TodoItem'
    delete:
      summary: Delete a todo
      operationId: deleteTodo
      tags:
        - todos
      parameters:
        - name: id
          in: path
          required: true
          description: The ID of the todo
          schema:
            type: string
            format: uuid
      responses:
        '204':
          description: The todo was deleted
  /identity/register:
    post:
      summary: Register a new user
      operationId: registerUser
      tags:
        - identity
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/RegisterUserRequest'
      responses:
        '201':
          description: Created
components:
  securitySchemes:
    BasicAuth:
      type: http
      scheme: basic
  schemas:
    TodoItem:
      type: object
      properties:
        id:
          type: string
          format: uuid
        name:
          type: string
        description:
          type: string
        dueDate:
          type: string
          format: date-time
        status:
          $ref: '#/components/schemas/TodoItemStatus'
      example:
        id: 'aa8970d2-8564-4f5a-8f4a-9e95a9b92298'
        name: 'Buy groceries'
        description: 'Buy milk, eggs, and bread'
        dueDate: '2021-12-31T23:59:59Z'
        status: 'NOT_STARTED'
    NewTodoItem:
      type: object
      properties:
        name:
          type: string
          x-field-extra-annotation: "@jakarta.validation.constraints.NotBlank"
        description:
          type: string
        dueDate:
          type: string
          format: date-time
        status:
          $ref: '#/components/schemas/TodoItemStatus'
      required:
        - name
        - status
      example:
        name: 'Buy groceries'
        description: 'Buy milk, eggs, and bread'
        dueDate: '2021-12-31T23:59:59Z'
        status: 'NOT_STARTED'
    TodoItemStatus:
      type: string
      enum:
        - NOT_STARTED
        - IN_PROGRESS
        - COMPLETED
    RegisterUserRequest:
      type: object
      properties:
        username:
          type: string
        password:
          type: string
    RegisterUserResponse:
      type: object
      properties:
        id:
          type: string
        username:
          type: string
```

### 2.4. Database

I've tried to use the default JdbcUserDetailsManager, but the default scheme is not compatible with postgresql, as it
uses `varchar_ignorecase` which is not supported by postgresql.

I will build on top of the default DDL at classpath `org/springframework/security/core/userdetails/jdbc/users.ddl` to
create our own User and Role models and implement the UserDetailsService interface.

I think this will also make it a good time to introduce database migrations to our application.

For database migration, we have two options: Flyway and Liquibase, 
and we will be using Liquibase for now in this project. 

Flyway version will be added in the future.

#### 2.4.1. Liquibase

Official documentation: https://www.liquibase.org/documentation/index.html

1. We will add the changelog for the existing todo_items table.  
    see: `src/main/resources/db/changelog/changes/db.changelog-v2024042101-create-core-tables.yaml`
2. We will add a new changelog for the users and authorities tables.
    see: `src/main/resources/db/changelog/changes/db.changelog-v2024042201-create-user-role-tables.yaml`
3. We will add a new changelog for the initial data.
    see: `src/main/resources/db/changelog/changes/db.changelog-v2024042302-insert-default-user.yaml`

#### 2.4.2. Flyway

Official documentation: https://flywaydb.org/documentation/

## Next Step

- [ ] Add Todo List Model
- [ ] Implement Faceted Search Service
- [ ] Realtime Collaboration Service
