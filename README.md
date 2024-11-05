# E-commerce-Backend
E-commerce Backend

🛍️ E-Commerce Backend with Spring Boot

This project is a backend API for an e-commerce platform, built with Spring Boot. It includes features for managing products, categories, carts, orders, users, and authentication.


---

📚 Table of Contents

	
[Project Overview](#project-overview)

Features

Technologies Used

Getting Started

Endpoints

Project Structure

Security

[Testing](#Testing)



---

<a name="project-overview"></a>Project Overview

This backend application provides RESTful APIs to handle CRUD operations for products, categories, users, and order processing. It includes JWT-based authentication for secure login and access to resources.


---

Features

Product Management: Create, update, delete, and retrieve products.

Category Management: Organize and retrieve products by category.

Cart Functionality: Add, update, and remove items in the shopping cart.

Order Processing: Manage user orders and order details.

User Authentication: JWT-based login for secure access.

Spring Security Integration: Role-based access control for endpoints.



---

Technologies Used

Java 8 / 11

Spring Boot

Spring Data JPA for database interactions

Spring Security for authentication and authorization

JWT (JSON Web Token) for stateless authentication

MySQL/PostgreSQL as the primary database

Maven for dependency management



---

Getting Started

Prerequisites

Java Development Kit (JDK 8 or higher)

Maven

MySQL or PostgreSQL


Installation

1. Clone the repository:

git clone https://github.com/Yogesh-chiluka/E-commerce-Backend-main


2. Navigate to the project directory:

cd E-commerce-Backend-main


3. Configure the database in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password


4. Run the application:

mvn spring-boot:run



Building the Project

To build the project, use:

mvn clean install


---

Endpoints

Below are some key endpoints. For a full list, refer to api-docs or check /swagger-ui after running the application.

Product Management

GET /products - List all products

POST /products - Add a new product


Category Management

GET /categories - List all categories


Cart Management

POST /cart/add - Add item to cart

POST /cart/remove - Remove item from cart


User Authentication

POST /auth/login - Login and get a JWT

POST /auth/register - Register a new user




---

Project Structure

Major Components

Entity Classes: Represent database tables (e.g., Product, User, Order).

Repository Layer: Interfaces extending JpaRepository for database interactions.

Service Layer: Business logic for handling operations.

Controller Layer: REST API endpoints for interacting with the application.

DTOs (Data Transfer Objects): Used to transfer data between layers.



---

Security

The project integrates Spring Security with JWT-based authentication. Access to endpoints is controlled based on user roles.

Key Security Components

UserSecurityService: Custom user details service.

JWT Util: Manages JWT creation and validation.

SecurityConfig: Configures security filters and endpoint restrictions.


JWT-Based Authentication Flow

1. Users log in via /auth/login to receive a JWT.


2. The JWT is included in request headers to authenticate and authorize user actions.




---

<a name="Testing"></a>Testing

Unit Testing and Integration Testing are used to verify core functionality.

Manual API testing was conducted using Postman.



---

This streamlined README keeps the essential project details clear and concise. Adjust as necessary for accuracy with your specific project setup.

