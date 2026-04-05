# product-catalog

Microservice responsible for managing products and categories in the [AlgaShop](https://github.com/jeanmalvessi/ems-algashop-meta) platform.

Built using **Contract-Driven Development (CDD)** with Spring Cloud Contract, and API documentation generated automatically from tests via Spring REST Docs.

## Responsibilities

- Product registration, updates, enable/disable lifecycle
- Stock management (restock and withdraw operations)
- Category management
- Publishing contract stubs for consumer-driven testing by other services (e.g., `ordering`)
- Generating API documentation from integration tests (AsciiDoc)

## Architecture

- **Domain Layer:** Aggregates (`Product`, `Category`), Value Objects, Domain Events (`ProductAddedEvent`, `ProductDelistedEvent`, `CategoryUpdatedEvent`), Repository interfaces
- **Application Layer:** Management and query services for products and categories
- **Infrastructure Layer:** MongoDB repositories, Redis cache configuration, ModelMapper-based DTO mapping, REST Docs configuration

## Tech Stack

- **Java 25**, Spring Boot 4.0.0
- **Spring Data MongoDB** + MongoDB 8 (3-node replica set for transaction support)
- **Spring Cache** + Redis 8.4 (server-side caching — Cache-Aside and Write-Through patterns, CacheEvict invalidation)
- **HTTP Caching:** ETags, Last-Modified, and Cache-Control headers on product endpoints (1-minute cache)
- **Spring Cloud Contract 5.0.0** (contract verification + stub publication for consumers)
- **Spring REST Docs** (API documentation generated from MockMvc tests, published as AsciiDoc)
- **Testcontainers** (MongoDB integration tests)
- **ModelMapper** (entity/DTO mapping)
- **Lombok**

## API

Base path: `/api/v1`

| Method | Path | Description |
|--------|------|-------------|
| POST | `/products` | Create product |
| PUT | `/products/{productId}` | Update product |
| PUT | `/products/{productId}/enable` | Enable product |
| DELETE | `/products/{productId}/enable` | Disable product |
| GET | `/products/{productId}` | Get product (with HTTP cache headers) |
| GET | `/products` | List/filter products with pagination |
| POST | `/products/{productId}/restock` | Increase stock quantity |
| POST | `/products/{productId}/withdraw` | Decrease stock quantity |
| POST | `/categories` | Create category |
| PUT | `/categories/{categoryId}` | Update category |
| GET | `/categories/{categoryId}` | Get category |
| GET | `/categories` | List categories |

## Running

```bash
./gradlew bootRun
```

Default port: **8083** (development profile)

Database: MongoDB replica set on `localhost:27017,27018,27019` (db: `product_catalog`)
Cache: Redis on `localhost:6379` (db 0)
