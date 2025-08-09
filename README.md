# User Registration and Login API

A simple Spring Boot RESTful API for user registration, login, and management.

## Features

- Register new users with roles (TENANT, LANDLORD, ADMIN)
- Login with email and password
- Retrieve all users
- Delete user by email
- Data validation and enum handling
- Cross-Origin setup for frontend integration (e.g., React)

## Tech Stack

- Java 17+
- Spring Boot
- JPA/Hibernate
- H2 or PostgreSQL (configurable)
- Maven

## API Endpoints

| Method | Endpoint                | Description                    |
|--------|-------------------------|--------------------------------|
| GET    | /api/v1/users/all       | Get all users                  |
| POST   | /api/v1/users/register  | Register a new user            |
| POST   | /api/v1/users/login     | Login user by email/password   |
| DELETE | /api/v1/users/delete    | Delete user by email           |

## Getting Started

1. Clone the repository  
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   
2. Navigate to the project directory
   ```bash
   cd your-repo-name
   
3. Build and run the application
   ```bash
   mvn spring-boot:run

## Notes
Make sure your frontend (e.g., React) runs on http://localhost:3000 for CORS compatibility.

Adjust database settings in application.properties as needed.
