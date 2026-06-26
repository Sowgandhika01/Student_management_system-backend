Student Management System - Backend

A comprehensive Spring Boot REST API backend for managing student information, courses, marks, orders, and user authentication. This application provides a complete Student Management System with real-time event streaming using Apache Kafka.

Features

- Student Management: Create, update, delete, and retrieve student records
- Course Management: Manage courses and course assignments
- Marks Tracking: Record and track student marks/grades
- Order Management: Handle orders with real-time event streaming via Kafka
- User Authentication: User login and role-based access control
- Passport Management: Manage passport information for international students
- Person Management: General person/contact management
- Real-time Events: Asynchronous event processing using Apache Kafka
- API Documentation: Interactive Swagger UI for API exploration
- Error Handling: Global exception handling with meaningful error messages
- Data Validation: Input validation using Spring Validation framework

Technology Stack

- Java: 21
- Spring Boot: 3.2.5
- Spring Data JPA: ORM for database operations
- Spring Kafka: Event streaming and messaging
- MySQL: Relational database
- Swagger/SpringDoc OpenAPI: API documentation
- Maven: Build automation
- Validation Framework: Spring Boot Starter Validation

Prerequisites

Before running the application, ensure you have:

- Java 21 JDK installed
- Apache Maven 3.6+
- MySQL Server 8.0+
- Apache Kafka 3.x+
- Git (optional)

Project Structure

```
helloapp/
├── src/
│   ├── main/
│   │   ├── java/com/cscorner/helloapp/
│   │   │   ├── controller/          # REST API endpoints
│   │   │   ├── service/              # Business logic layer
│   │   │   ├── repository/           # Data access layer
│   │   │   ├── model/                # Entity classes
│   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   ├── exception/            # Exception handling
│   │   │   ├── kafka/                # Kafka producers and consumers
│   │   │   └── HelloappApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/cscorner/helloapp/
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

Key Components

- Controllers: Handle HTTP requests and responses
  - `UserController` - User management
  - `StudentController` - Student management
  - `CourseController` - Course management
  - `MarksController` - Marks management
  - `OrderController` - Order management
  - `PassportController` - Passport information
  - `PersonController` - Person records

- Services: Business logic implementation
- Repositories: Database CRUD operations (Spring Data JPA)
- Models: JPA entities representing database tables
- DTOs: Data transfer objects for API requests/responses
- Kafka: Event producers and consumers for asynchronous operations

Setup Instructions

1. Clone the Repository

```bash
git clone <repository-url>
cd helloapp
```

2. Configure MySQL Database

```bash
# Create the database
CREATE DATABASE studentdb;
USE studentdb;
```

 3. Update Application Properties

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=root
spring.datasource.password=your_password
```

 4. Setup Apache Kafka

 Download and Extract Kafka

```bash
# Download Kafka from: https://kafka.apache.org/downloads
# Extract to desired location (e.g., C:\kafka)
```

 Start Kafka Server (Windows)

```bash
# In PowerShell/Command Prompt, navigate to Kafka directory
cd C:\kafka

# Generate cluster UUID (first time only)
.\bin\windows\kafka-storage.bat random-uuid

# Format the storage
.\bin\windows\kafka-storage.bat format --standalone -t <UUID_from_above> -c .\config\server.properties

# Start Kafka server
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
 Create Kafka Topics

In a new terminal window:

```bash
# Create hello-topic
cd C:\kafka
.\bin\windows\kafka-topics.bat --create --topic hello-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Create multi-order-topic
.\bin\windows\kafka-topics.bat --create --topic multi-order-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Verify topics
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list
```

 5. Build the Application

```bash
mvn clean install
```
API Documentation

Once the application is running, access the interactive Swagger UI:

```
http://localhost:8086/swagger-ui/index.html
```

 Configuration

Application Properties

```properties
# Server Configuration
spring.application.name=helloapp
server.port=8086

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=root
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Kafka Configuration
spring.kafka.bootstrap-servers=localhost:9092

# Producer Configuration
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

# Consumer Configuration (commented - enable as needed)
# spring.kafka.consumer.group-id=hello-group
# spring.kafka.consumer.auto-offset-reset=earliest
# spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
# spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
# spring.kafka.consumer.properties.spring.json.trusted.packages=*
```

Running the Application

Using Maven (Recommended)

```bash
# Run directly
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/helloapp-0.0.1-SNAPSHOT.jar
```

Using IDE

- IntelliJ IDEA: Right-click `HelloappApplication.java` → Run
- Eclipse: Run As → Spring Boot App
- VS Code: Use Spring Boot Extension

The application will start on `http://localhost:8086`

 Database Schema

The application automatically creates the following tables via JPA:

- students - Student information
- courses - Course details
- marks - Student grades/marks
- users- User accounts
- orders - Order records
- passports - Passport details
- persons - General person information

 Sample Entities

```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
```
Kafka Integration

Publishing Events

The application produces events to Kafka topics for order and event notifications:

```java
// Kafka Producer Configuration
@Configuration
public class KafkaProducerConfig {
    // Producer bean configurations
}

// Send messages using KafkaTemplate
kafkaTemplate.send("hello-topic", orderDTO);
kafkaTemplate.send("multi-order-topic", orderDTO);
```

Consuming Events

Enable and configure consumers in `application.properties` to listen to topics:

```properties
spring.kafka.consumer.group-id=hello-group
spring.kafka.consumer.auto-offset-reset=earliest
```

 API Endpoints

Student Endpoints
- `GET /students` - Get all students
- `GET /students/{id}` - Get student by ID
- `POST /students` - Create new student
- `PUT /students/{id}` - Update student
- `DELETE /students/{id}` - Delete student

Course Endpoints
- `GET /courses` - Get all courses
- `POST /courses` - Create course
- `PUT /courses/{id}` - Update course
- `DELETE /courses/{id}` - Delete course

 User Endpoints
- `GET /users` - Get all users
- `POST /users/login` - User login
- `POST /users` - Register user
- `PUT /users/{id}` - Update user
Order Endpoints
- `GET /orders` - Get all orders
- `POST /orders` - Create order (publishes to Kafka)
- `PUT /orders/{id}` - Update order
- `DELETE /orders/{id}` - Delete order

Marks Endpoints
- `GET /marks` - Get all marks records
- `POST /marks` - Record marks
- `PUT /marks/{id}` - Update marks

Additional Endpoints
- `GET /passports` - Passport management
- `GET /persons` - Person records

*For complete endpoint documentation, refer to Swagger UI*

 Development

 Adding New Entity

1. Create entity class in `model/` package
2. Create repository interface extending `JpaRepository`
3. Create service class with business logic
4. Create controller class with API endpoints
5. Add corresponding DTO classes

Example:

```java
// Model
@Entity
@Table(name = "new_entity")
public class NewEntity { ... }

// Repository
public interface NewEntityRepository extends JpaRepository<NewEntity, Long> { ... }

// Service
@Service
public class NewEntityService { ... }

// Controller
@RestController
@RequestMapping("/new-entities")
public class NewEntityController { ... }
```
 Troubleshooting

 Issue: Kafka Connection Failed

Solution: Ensure Kafka server is running on `localhost:9092`
```bash
# Check Kafka status
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list
```

 Issue: Port Already in Use

Solution: Change port in `application.properties`
```properties
server.port=8087
```
Issue: MySQL Connection Error

Solution: Verify credentials and database existence
```bash
mysql -u root -p
# Then check: SHOW DATABASES; USE studentdb;
```

 Issue: Kafka Port 9092 Already in Use

Solution: Kill the process using the port or change Kafka configuration

```bash
# Windows
netstat -ano | findstr :9092
taskkill /PID <PID> /F

# Generate new cluster ID and reformat storage
.\bin\windows\kafka-storage.bat random-uuid
.\bin\windows\kafka-storage.bat format --standalone -t <new-UUID> -c .\config\server.properties
```

 Issue: Build Failures

Solution: Clear Maven cache and rebuild
```bash
mvn clean install -U
```

 Notes

- The application uses Spring Data JPA with `ddl-auto=update` for automatic schema management
- Database credentials in `application.properties` should be changed for production
- Kafka topics must be created before starting the application
- Consider using environment variables for sensitive configuration in production
- API validation is enforced using Spring Validation annotations



Last Updated: June 2026  

**Docker**

- The repository includes a multi-stage `Dockerfile` that builds the application with Maven and produces a runtime image that runs the packaged JAR. The runtime image exposes port `8086`.

- Build the Docker image locally:

```bash
docker build -t helloapp:latest .
```

- Run the image and connect to a MySQL instance on the host (useful if you want the container to use your existing DB):

```bash
docker run --rm -p 8086:8086 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/studentdb \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  helloapp:latest
```

- Or use Docker Compose (the included `docker-compose.yml`) to build and run the app and related services:

```bash
docker compose up --build
```

- Notes:
  - Inside containers, use `host.docker.internal` to reach services running on the host (Windows/macOS). On Linux, you may need another method (e.g., host network or `--add-host` mapping).
  - Ensure MySQL accepts connections from Docker and the user has proper privileges.

