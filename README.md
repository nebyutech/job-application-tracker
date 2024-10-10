# Job Application Tracker

## Overview
Job Application Tracker is a web-based application built with Spring Boot to help users manage and track their job applications. Users can register, log in, and manage their job applications, including adding new applications, viewing existing ones, and deleting them. The application also includes a feature to upload resumes.

## Features
- User Registration and Login
- Add, View, and Delete Job Applications
- Resume Upload functionality
- Input validation for forms
- Secure password storage using BCrypt
- Exception handling for better user experience

## Technology Stack
- **Back-end**: Spring Boot, Spring Security, Spring Data JPA, Hibernate, MySQL
- **Front-end**: Thymeleaf, HTML5, CSS3, JavaScript (for validation)
- **Database**: MySQL
- **Testing**: JUnit, Spring Boot Test, Mockito

## Project Structure
```
src
 ├── main
 │   ├── java
 │   │   └── com.nebyu.jobapplicationtracker
 │   │        ├── config               # Security configuration
 │   │        ├── controller           # REST Controllers
 │   │        ├── exception            # Exception handling classes
 │   │        ├── model                # Entity models
 │   │        ├── repository           # Repository interfaces
 │   │        └── service              # Service classes
 │   ├── resources
 │   │   ├── static                    # Static assets (CSS, JS)
 │   │   ├── templates                 # Thymeleaf templates (HTML)
 │   │   └── application.properties    # Database configurations
 └── test
     └── java
         └── com.nebyu.jobapplicationtracker
              ├── repository           # Unit tests for repositories
              ├── controller           # Unit tests for controllers
              └── service              # Unit tests for services
```

## Setup & Installation
1. **Clone the Repository:**
   ```
   git clone https://github.com/nebyutech/job-application-tracker.git
   cd job-application-tracker
   ```

2. **Configure Database:**
    - Set up MySQL and create a database called `job_tracker_db`.
    - Update the `application.properties` file with your MySQL credentials:
      ```
      spring.datasource.url=jdbc:mysql://localhost:3306/job_tracker_db
      spring.datasource.username=YOUR_DB_USERNAME
      spring.datasource.password=YOUR_DB_PASSWORD
      ```

3. **Run the Application:**
    - Use Maven to build and run the application:
      ```
      mvn spring-boot:run
      ```

4. **Access the Application:**
    - Open your browser and navigate to `http://localhost:8080`.

## Running Tests
The application includes unit and integration tests. To run the tests, use the following command:
```
mvn test
```

## Future Improvements
- Add features to categorize applications by job type.
- Include pagination and search for job applications.
- Add email notifications for application updates.
- Use cloud storage (e.g., AWS S3) for storing resumes.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

---

