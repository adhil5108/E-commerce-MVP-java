# E-Commerce Backend (Spring Boot)

Spring Boot REST API
JWT Authentication (Register / Login)
Role Based Access (Admin / User)
Product CRUD + Search
Shopping Cart (per-user)
Checkout + Payment Simulation
Excel Import / Export (Inventory Management)
Real-time Product Notification (Server-Sent Events)
Swagger API Documentation
Docker Compose (PostgreSQL + Redis)
JUnit + Mockito + Integration Tests
CI/CD with GitHub Actions (Build + Test + Push Docker Image)


# Admin Features
Admin-only APIs using @PreAuthorize("hasRole('ADMIN')")
Bulk product upload (.xlsx)
Excel validation
Product add notification event stream


# User Features
Browse products
Add / Update / Remove cart items
User-based cart stored in DB
Checkout with order creation
Subscribe notification stream


How to Run
Live Hosted Version (Recommended)

The backend is already deployed — just open Swagger to test all APIs:

🔗 Live Swagger:
https://ecommerce-backend-java.onrender.com/swagger-ui/index.html

# Tests
mvn test


# Important Endpoints
-POST /api/auth/register
-POST /api/auth/login
-GET/POST/PUT/DELETE /api/v1/products
-POST /api/v1/products/import   (Excel upload)
-GET /api/v1/products/export    (Download Excel)
-GET /api/v1/products/notifications  (SSE stream)
-POST /api/v1/cart
-POST /api/v1/orders/checkout


# Tech Stack
Spring Boot 3
Spring Security + JWT
PostgreSQL
Redis
Docker + Docker Compose
JUnit 5 + Mockito
Swagger / OpenAPI
Apache POI (Excel)
GitHub Actions (CI/CD)
