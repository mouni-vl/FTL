# Fantasy Backend

This is a Hexagonal-Architecture (Ports & Adapters) Spring Boot project for a Fantasy League application.

## Features

- Domain models for User, League, Team, Player, etc.
- Ports & Adapters structure:
  - `domain/` for business logic
  - `application/` for use cases and DTOs
  - `adapter/` for REST controllers, JPA repositories, CSV importers
  - `shared/` for utilities & common config
- Flyway migrations for schema versioning
- JWT-based security
- MapStruct for mapping Entity ↔ Domain ↔ DTO
- SpringDoc OpenAPI for API documentation
- Caffeine cache for frequently read endpoints

## Project Structure

```
src
├─ main
│  ├─ java/com/example/fantasy
│  │  ├─ config
│  │  ├─ domain
│  │  │  ├─ model
│  │  │  ├─ repository
│  │  │  ├─ service
│  │  │  └─ exception
│  │  ├─ application
│  │  │  ├─ dto
│  │  │  ├─ mapper
│  │  │  ├─ port
│  │  │  │  ├─ in
│  │  │  │  └─ out
│  │  │  ├─ service
│  │  │  └─ exception
│  │  ├─ adapter
│  │  │  ├─ inbound
│  │  │  │  ├─ rest
│  │  │  │  └─ messaging
│  │  │  └─ outbound
│  │  │     ├─ persistence
│  │  │     │  ├─ entity
│  │  │     │  ├─ repository
│  │  │     │  ├─ mapper
│  │  │     │  └─ CsvImport
│  │  │     └─ mail
│  │  └─ shared
│  │     ├─ utils
│  │     └─ config
│  └─ resources
│     ├─ application.yml
│     └─ db/migration
└─ test
   └─ java/com/example/fantasy
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- PostgreSQL 12 or higher

### Database Setup

1. Create a PostgreSQL database named `fantasy`:

```sql
CREATE DATABASE fantasy;
```

2. Update the database credentials in `src/main/resources/application.yml` if needed:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fantasy
    username: your_db_user
    password: your_db_password
```

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

The application will start on port 8080 and Flyway will automatically create the database schema.

### API Documentation

Once the application is running, you can access the Swagger UI to view and test the API:

```
http://localhost:8080/swagger-ui.html
```

## Testing

Run the tests with:

```bash
mvn test
```

## Hexagonal Architecture

This project follows the Hexagonal Architecture (Ports & Adapters) pattern:

- **Domain Layer**: Contains the business logic and domain models
- **Application Layer**: Contains use cases, DTOs, and ports (interfaces)
- **Adapter Layer**: Contains implementations of the ports (adapters)

### Key Concepts

- **Inbound Ports**: Interfaces that the application exposes to the outside world (e.g., REST controllers)
- **Outbound Ports**: Interfaces that the application uses to interact with external systems (e.g., databases)
- **Adapters**: Implementations of the ports

## License

This project is licensed under the MIT License - see the LICENSE file for details.
