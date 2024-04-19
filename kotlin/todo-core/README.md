# TodoCoreApplication

This is a Kotlin application that provides a RESTful API for managing todo items. 
The application is built with Spring Boot and follows the OpenAPI specification.

## Prerequisites

- JDK 17 or later
- Maven
- Gradle

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/irasychan/tddopenapitodoapp.git
    ```
2. Navigate to the project directory:
    ```bash
    cd tddopenapitodoapp
    ```
3. Build the project:
    ```bash
    ./gradlew build
    ```
4. Run the application:
    ```bash
    ./gradlew run
    ```

The application will start and by default can be accessed at http://localhost:8080.

## Running Tests

To run the tests, execute the following command:
```bash
./gradlew test
```

## API Documentation

The API follows the OpenAPI specification. The specification file is located at `specs/todo-core-v1.yaml`. You can view the API documentation by opening this file in an OpenAPI viewer.

