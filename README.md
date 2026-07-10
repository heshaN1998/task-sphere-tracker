TaskSphere is a task and weekly reporting management system
that allows managers to manage projects, users, and monitor weekly reports submitted by team members.

#Used Intellij IDEA
## Backend
- Java 21
- Spring Boot 4
- Spring Security + JWT Authentication(jjwt api/jackson/impl)
- Spring Data JPA / Hibernate
- PostgreSQL Database
- Maven
- Lombok
- Spting Ai bom / Ai advisor vecor stor / open ai 

##Project Structure
TaskSphere
│
├── backend
   ├── controller
   ├── services
   ├── repositories
   ├── entities
   ├── securities
   └── configs


#Make sure the following are installed:

- Java JDK 21
- Maven
- Node.js and npm
- PostgreSQL 
- Git

## Install Backend Dependencies

-Navigate to the backend project folder:
  cd backend

-Install Maven dependencies:
  mvn clean install
  Configure Application Properties

-Update src/main/resources/application.properties:
  server.port=8082
  spring.datasource.url=jdbc:postgresql://localhost:5432/sisencodig-taskTracker
  spring.datasource.username=postgres
  spring.datasource.password=your_password

  spring.jpa.hibernate.ddl-auto=update

  jwt.secret=your_secret_key
  jwt.expiration-ms=86400000

-Replace:
  your_password with your PostgreSQL password
  your_secret_key with your JWT secret key
  Start Backend Server

  -Run the Spring Boot application:
    mvn spring-boot:run or run Jar file

#Backend will start at: http://localhost:8082

##Install PostgreSQL
https://www.postgresql.org/download/

-Create the database:
  CREATE DATABASE "sisencodig-taskTracker";
  
-Verify Database Connection

  spring.datasource.url=jdbc:postgresql://localhost:5432/sisencodig-taskTracker
  spring.datasource.username=postgres
  spring.datasource.password=your_password
  When the backend starts successfully, Hibernate will automatically create/update tables:
  spring.jpa.hibernate.ddl-auto=update
  Database tables will be generated from JPA entities automatically.



  
