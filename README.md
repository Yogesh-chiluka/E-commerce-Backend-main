# 🛍️ E-Commerce Backend with Spring Boot



This project is a backend API for an e-commerce platform, built with Spring Boot. It includes features for managing products, categories, carts, orders, users, and authentication.

## 📚 Table of Contents 
- [Project Overview](#project-overview)

- [Features](#Features)

- [Technologies Used](#Technologies-Used)

- [Getting Started](#Getting-Started)

- [Endpoints](#Endpoints)

- [Project Structure](#Project-Structure)

- [Security](#Security)

- [Testing](#Testing)
---

<a name="project-overview"></a>
## Project Overview

This backend application provides RESTful APIs to handle CRUD operations for products, categories, users, and order processing. It includes JWT-based authentication for secure login and access to resources.


---
<a name="Features"></a>
## Features

- Product Management: Create, update, delete, and retrieve products.

- Category Management: Organize and retrieve products by category.

- Cart Functionality: Add, update, and remove items in the shopping cart.

- Order Processing: Manage user orders and order details.

- User Authentication: JWT-based login for secure access.

- Spring Security Integration: Role-based access control for endpoints.



---
<a name="Technologies-Used"></a>
## Technologies Used

- Java 17

- Spring Boot

- Spring Data JPA for database interactions

- Spring Security for authentication and authorization

- JWT (JSON Web Token) for stateless authentication

- MySQL as the primary database

- Maven for dependency management



---
<a name="Getting-Started"></a>
## Getting Started 


### Prerequisites
1. **Java Development Kit (JDK 8 or higher)**
2. **Maven**
3. **MySQL or PostgreSQL**

### Installation Steps
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Yogesh-chiluka/E-commerce-Backend-main
   ```
   
2. **Navigate to the Project Directory:**
   ```bash
   cd E-commerce-Backend-main
   ```

3. **Configure the Database:**
   Open the `application.properties` file and set your database connection details:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your-database
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

4. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

### Building the Project
To build the project, use:
```bash
mvn clean install
```

### Additional Tips
- Make sure your database server is running before you attempt to connect.
- If you encounter any issues, check the console logs for error messages—they can provide clues for troubleshooting.
- Consider using a `.env` file or similar for sensitive information like database credentials to enhance security.

If you have any specific questions or run into issues during the setup, feel free to ask!


---
<a name="Endpoints"></a>
## Endpoints 

#### Product Management
- **GET /products**: List all products.
- **POST /products**: Add a new product.

#### Category Management
- **GET /categories**: List all categories.

#### Cart Management
- **POST /cart/add**: Add an item to the cart.
- **POST /cart/remove**: Remove an item from the cart.

#### User Authentication
- **POST /auth/login**: Log in and receive a JWT.
- **POST /auth/register**: Register a new user.
---
 <a name="Project-Structure"></a>
## Project Structure
- **Entity Classes**: Represent database tables (e.g., Product, User, Order).
- **Repository Layer**: Interfaces extending `JpaRepository` for database interactions.
- **Service Layer**: Contains business logic for handling operations.
- **Controller Layer**: REST API endpoints for application interaction.
- **DTOs**: Used for data transfer between layers.
---
 <a name="Security"></a>
## Security
The project uses **Spring Security** with JWT-based authentication:
- **UserSecurityService**: Custom user details service.
- **JWT Util**: Manages JWT creation and validation.
- **SecurityConfig**: Configures security filters and endpoint restrictions.

### JWT-Based Authentication Flow
1. Users log in via **/auth/login** to receive a JWT.
2. The JWT is included in request headers for authentication and authorization.
---
<a name="Testing"></a>
## Testing 
- **Unit Testing** and **Integration Testing** are employed to verify core functionality.
- Manual API testing is conducted using **Postman**.


If you have any specific questions about implementing these features or need further assistance, feel free to ask!
