# Amazon-like Product Selling Website Backend

This project is a Spring Boot application that provides RESTful APIs for managing users, products, and orders, mimicking basic functionalities of an e-commerce platform like Amazon.

## Features

- User registration, retrieval, update, deletion
- Product catalog management (CRUD)
- Order placement with stock management
- Validation and error handling
- ModelMapper for DTO mapping
- MySQL database integration
- Dockerized application and database setup

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- ModelMapper
- Lombok
- Maven
- Docker, Docker Compose

## Prerequisites

- Java 17+
- Maven 3.6+
- Docker & Docker Compose (for containerized setup)
- MySQL (if running locally without Docker)

## Getting Started

### Running with Docker

1. Build Docker images and start containers:
   ```bash
   docker-compose up --build
   ```

2. The application will be available at `http://localhost:8080`

### Running Locally

1. Update `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/amazonlike?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=password
   ```

2. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. The application will be available at `http://localhost:8080`

## API Endpoints

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create a new user
- `PUT /api/users/{id}` - Update existing user
- `DELETE /api/users/{id}` - Delete user

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update existing product
- `DELETE /api/products/{id}` - Delete product

- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update existing order
- `DELETE /api/orders/{id}` - Delete order

## License

This project is licensed under the MIT License.
