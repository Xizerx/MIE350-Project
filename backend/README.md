# Backend

## Stack
- **Java 21**
- **Spring Boot 3.2**
- **H2** in-memory database (development)
- **JPA / Hibernate** for database access
- **Maven** for builds

## Structure (to be added)
```
backend/
├── src/
│   └── main/
│       ├── java/com/accessories/
│       │   ├── controller/     ← REST endpoints
│       │   ├── service/        ← business logic
│       │   ├── model/          ← database entities
│       │   ├── repository/     ← database queries
│       │   ├── dto/            ← response shapes
│       │   └── config/         ← app config & sample data
│       └── resources/
│           └── application.properties
└── pom.xml
```

## Running locally

> Prerequisites: Java 21, Maven

```bash
cd backend
mvn spring-boot:run
```

- API available at: `http://localhost:8080/api`
- Database console: `http://localhost:8080/api/h2-console`
  - JDBC URL: `jdbc:h2:mem:accessoriesdb`
  - Username: `sa`
  - Password: *(leave blank)*

## Key endpoints
See [`../docs/api-reference.md`](../docs/api-reference.md) for the full list.
