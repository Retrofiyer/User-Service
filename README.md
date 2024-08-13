<a id="readme-top"></a>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
  </ol>
</details>

<div>
    <h1>User Registration Microservice</h1>
</div>

## About The Project

This microservice is designed to handle the registration of users for a streaming application. It manages the creation of user accounts, including the capture and validation of user details such as username, password, and email. The microservice ensures that all user data is securely stored and that the registration process is seamless, providing an essential component for user authentication and account management within the streaming platform.

Key features include:
User Account Creation: Handles the registration process with input validation and error handling.
Secure Data Storage: Ensures user credentials are encrypted and stored securely.
Integration-Ready: Easily integrates with other services, such as authentication and user profile management, within the streaming platform.
Scalability: Designed to handle a high volume of user registrations, making it suitable for large-scale applications.

## Prerequisites

Before you begin, make sure you have the following tools installed on your machine:

- **Java 17 or higher** - [Download Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Spring Boot 3.3.2** - [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- **Maven** - [Maven](https://maven.apache.org/install.html)

If you don't have any of these tools installed, follow the provided links to install them.


### Built With

[![Java][java.com]][java-url]
[![Spring Boot][springboot.com]][springboot-url]
[![PostgreSQL][postgresql.com]][postgresql-url]
[![RabbitMQ][rabbitmq.com]][rabbitmq-url]
[![Swagger][swagger.com]][swagger-url]
[![Docker][docker.com]][docker-url]

<!-- GETTING STARTED -->
## Getting Started

### Install

1.- Clone the repository
   ```sh
   git clone https://github.com/Retrofiyer/User-Service.git
   cd User-Service
   ```
2.- Build project using maven
 ```sh
   mvn clean install
   ```

## Configuration

The configuration for the User Service is located in
```md
src/main/resources/application.properties
```
Below is an example configuration:

 ```sh
 spring.datasource.url=jdbc:postgresql://host-name:port/DB-Name
 spring.datasource.username=postgres-username
 spring.datasource.password=postgres-password
   ```

## Running the service

  ```sh
    mvn spring-boot:run
   ```

## Contributing

I would like you to contribute to this project. Whether it's fixing a bug, adding a new feature or improving the documentation, your help is always welcome. Please email me at 
```md
sebitas5225@gmail.com
```
with all the details for improvement.

<!-- LINKS & IMAGES -->

[docker.com]: https://img.shields.io/badge/Docker-black?style=for-the-badge&logo=docker&logoColor=white
[docker-url]: https://www.docker.com/
[springboot.com]: https://img.shields.io/badge/SpringBoot-black?style=for-the-badge&logo=springboot&logoColor=white
[springboot-url]: https://spring.io/projects/spring-boot
[java.com]: https://img.shields.io/badge/Java-black?style=for-the-badge&logo=java&logoColor=white
[java-url]: https://www.oracle.com/java/
[rabbitmq.com]: https://img.shields.io/badge/RabbitMQ-black?style=for-the-badge&logo=rabbitmq&logoColor=white
[rabbitmq-url]: https://www.rabbitmq.com/
[swagger.com]: https://img.shields.io/badge/Swagger-black?style=for-the-badge&logo=swagger&logoColor=white
[swagger-url]: https://swagger.io/
[postgresql.com]: https://img.shields.io/badge/PostgreSQL-black?style=for-the-badge&logo=postgresql&logoColor=white
[postgresql-url]: https://www.postgresql.org/

