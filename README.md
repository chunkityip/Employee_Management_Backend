# Employee Management System - Backend

A Spring Boot REST API application for managing employee data with authentication and CRUD operations.

## 🚀 Technologies Used

- **Java 17** (or your version)
- **Spring Boot 3.x**
- **Spring Data JDBC**
- **PostgreSQL** Database
- **Gradle** - Dependency Management
- **Swagger/OpenAPI** - API Documentation
- **Lombok** - Reduce boilerplate code

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- Java JDK 17 or higher
- Maven 3.6 or higher
- Docker Desktop
- DBeaver(Opinional)
- Your preferred IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🛠️ Installation & Setup

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd employee-management-backend
```

### 2. Configure Database

Update the `src/main/resources/application.properties` file with your database credentials:

```properties
#Database Configuration
spring.datasource.url=jdbc:db2://localhost:50000/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA/Hibernate Properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080
```

### 3. Create Database Tables

Please look at docker-compose.yml, look/edit the settings and run 
```
docker-compose up

```


### 4. Build the Application

```bash
mvn clean install
```

## 🏃‍♂️ Running the Application

1. Open the project in your IDE
2. Navigate to `src/main/java/com/example/demo/DemoApplication.java`
3. Right-click and select "Run 'DemoApplication'"

## 📡 API Endpoints

Once the application is running, you can access:

- **Application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

### Main Endpoints:

#### Authentication
- `POST /api/v1/users/register` - Register new user
- `POST /api/v1/users/login` - User login
- `GET /api/v1/users/{username}` - Get user details
- `PUT /api/v1/users/{username}/password` - Update password

#### Employee Management
- `GET /api/v1/employees/getAllEmployees` - Get all employees
- `GET /api/v1/employees/{email}` - Get employee by email
- `POST /api/v1/employees` - Create new employee
- `PUT /api/v1/employees` - Update employee
- `DELETE /api/v1/employees/{email}` - Delete employee
- `GET /api/v1/employees/search/firstname/{firstname}` - Search by firstname
- `GET /api/v1/employees/search/experience/{experience}` - Find by experience
- `GET /api/v1/employees/exists/{email}` - Check if email exists

## 🧪 Testing the API

### Using Postman or curl:

1. **Register a new user:**
```bash
curl -X POST http://localhost:8080/api/v1/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "dateOfBirth": "1990-01-01",
    "mobileNumber": 1234567890,
    "domain": "backend",
    "experience": 5
  }'
```

2. **Login:**
```bash
curl -X POST http://localhost:8080/api/v1/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "john.doe@example.com",
    "password": "password123"
  }'
```

## 📦 Project Structure

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/       # REST Controllers
│   │   ├── service/          # Business Logic
│   │   ├── dao/              # Data Access Layer
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── exception/        # Custom Exceptions
│   │   └── DemoApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql          # Initial data (if any)
└── test/                     # Unit and Integration tests
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- Chunkit Yip (CK) - Initial work
