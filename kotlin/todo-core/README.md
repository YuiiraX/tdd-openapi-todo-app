# TodoCoreApplication

This is a Kotlin application that provides a RESTful API for managing todo items. 
The application is built with Spring Boot and follows the OpenAPI specification.

## Prerequisites

- JDK 17 or later
- Gradle

## Getting Started

1. Build the project:
    ```bash
    ./gradlew build
    ```
2. Run the application:
    ```bash
    ./gradlew bootRun
    ```

The application will start and by default can be accessed at http://localhost:8080.

## Running Tests

To run the tests, execute the following command:
```bash
./gradlew test
```

## API Documentation

The API follows the OpenAPI specification. The specification file is located at `specs/todo-core-v1.yaml`. You can view the API documentation by opening this file in an OpenAPI viewer.

