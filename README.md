# 🛒 E-Commerce Backend API (Spring Boot)

A production-style e-commerce backend built using **Java & Spring Boot**, designed to demonstrate secure API development, scalable architecture, and real-world backend workflows.

The system supports authentication, product management, cart operations, checkout simulation, real-time notifications, and inventory management.

---

## Features

###  Authentication & Security
- JWT Authentication (Register / Login)
- Role-based access control (Admin / User)
- Secure endpoints using Spring Security

###  Product Management
- Product CRUD operations
- Product search functionality
- Bulk product import via Excel (.xlsx)
- Excel export for inventory management

###  Shopping & Orders
- Per-user shopping cart
- Add / update / remove cart items
- Checkout workflow with order creation
- Payment simulation

###  Real-Time Updates
- Server-Sent Events (SSE) for product notifications

###  Quality & DevOps
- Unit, Integration, and API testing
- Swagger/OpenAPI documentation
- Dockerized services
- CI/CD using GitHub Actions

---

##  Admin Features

- Admin-only APIs :
- Bulk product upload via Excel
- Data validation during import
- Real-time product notification stream

---

##  User Features

- Browse and search products
- Manage personal shopping cart
- Checkout and order creation
- Subscribe to product notification stream

---

##  Live Demo

⚠️ The backend service is deployed, but the hosted database instance may periodically expire due to free-tier hosting limits.

You can still explore API structure via Swagger: https://ecommerce-backend-java.onrender.com/swagger-ui/index.html

---

##  Architecture

The application follows a layered architecture:

- **Controller Layer** — REST endpoints
- **Service Layer** — Business logic
- **Repository Layer** — Database interaction
- **Security Layer** — JWT authentication & authorization

Designed for scalability and maintainability.

---

##  Running Locally

### Start Application

```bash
git clone https://github.com/adhil5108/E-commerce.git
cd E-commerce

docker-compose up --build
