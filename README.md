# Exam and Project Management System — Spring Boot

A web application for managing academic exams and projects, built with Spring Boot, Spring Security, JPA/Hibernate and Thymeleaf. This is a web reimplementation of the same system previously built in Java Swing + JDBC, demonstrating the same domain logic through a modern MVC web architecture.

---

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Security | Spring Security (BCrypt, role-based access) |
| ORM | JPA / Hibernate |
| Frontend | Thymeleaf + HTML/CSS |
| Database | Microsoft SQL Server |
| Build | Maven |

---

## Architecture

Standard Spring Boot MVC layered architecture:

```
Controller → Service → Repository → Database
                ↕
           Thymeleaf Templates
```

- **Controllers** — handle HTTP requests, route to views or REST endpoints
- **Services** — business logic (enrollment, grade management, authentication)
- **Repositories** — JPA interfaces for database access
- **Models** — JPA entities mapped to SQL Server tables
- **Security** — Spring Security with BCrypt password encoding and role-based route protection

---

## Features

### Authentication
- Login for students and professors
- BCrypt password encoding
- Role-based access control via Spring Security

### Professor Features
- View and manage exams and projects
- Assign grades to students

### Student Features
- Browse and enroll in available exams
- Browse and enroll in available projects
- Update project status (Not started / In progress / Finished)
- View grades

### REST API
- `ProiectRestController` exposes REST endpoints for project data

---

## Database Schema

Same schema as the Java Swing version — 7 tables with relational integrity:

| Table | Description |
|---|---|
| Student | Student accounts |
| Profesor | Professor accounts |
| Materie | Subjects |
| Sala | Classrooms |
| Examen | Exams |
| Proiect | Projects |
| StudentExamen | Exam enrollment + grades |
| StudentProiect | Project enrollment + grades |

See `baza_de_date.sql` for the full schema.

---

## Setup

### Prerequisites
- Java 17+
- Microsoft SQL Server (local instance)
- Maven

### Database
1. Open SQL Server Management Studio
2. Run `baza_de_date.sql` to create the schema

### Configuration
Copy `application.properties.example` to `application.properties` and update with your local SQL Server details:

```properties
spring.datasource.url=jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=ExameneProiecte;integratedSecurity=true;trustServerCertificate=true;
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### Run
```bash
mvn spring-boot:run
```

The app runs on **http://localhost:8080**

---

## Comparison with Java Swing Version

| Aspect | Java Swing + JDBC | Spring Boot |
|---|---|---|
| UI | Desktop (Swing) | Web (Thymeleaf) |
| DB Access | Raw JDBC + manual SQL | JPA/Hibernate |
| Auth | Manual session | Spring Security |
| API | None | REST endpoints |
| Deployment | Local JAR | Web server |


