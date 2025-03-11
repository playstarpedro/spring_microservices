# Microservices Project

This repository contains three microservices: `ProductService`, `ClientService`, and `ConfigServer`. Each service is a separate Spring Boot application.

## Services

### ProductService

The `ProductService` is responsible for managing product information. It provides endpoints to create, read, update, and delete products.

#### Features:
- Create new products
- Retrieve product details
- Update existing products
- Delete products

### ClientService

The `ClientService` is responsible for managing client information. It provides endpoints to create, read, update, and delete clients.

#### Features:
- Create new clients
- Retrieve client details
- Update existing clients
- Delete clients

### ConfigServer

The `ConfigServer` is a centralized configuration server for managing external properties for applications across all environments. It uses Spring Cloud Config to provide server-side and client-side support for externalized configuration in a distributed system.

#### Features:
- Centralized configuration management
- Supports multiple environments
- Dynamic configuration updates

## Project Structure

```
ClientService/
    .gitattributes
    .gitignore
    HELP.md
    mvnw
    mvnw.cmd
    pom.xml
    .idea/
    .mvn/
    src/
        main/
        test/
    target/
ConfigServer/
    .gitattributes
    .gitignore
    HELP.md
    mvnw
    mvnw.cmd
    pom.xml
    .idea/
    .mvn/
    src/
    target/
ProductService/
    .gitattributes
    .gitignore
    HELP.md
    mvnw
    mvnw.cmd
    pom.xml
    .idea/
    .mvn/
    src/
    target/
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher

### Running the Services

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/your-repo.git
    cd your-repo
    ```

2. Navigate to each service directory and run the service using Maven:
    ```sh
    cd ProductService
    ./mvnw spring-boot:run
    ```

    ```sh
    cd ClientService
    ./mvnw spring-boot:run
    ```

    ```sh
    cd ConfigServer
    ./mvnw spring-boot:run
    ```

### Configuration

The `ConfigServer` should be started first as it provides configuration for the other services. Ensure that the configuration files are properly set up in the `ConfigServer` before starting the other services.
