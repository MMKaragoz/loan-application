# Loan Application

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- OpenAPI Documentation
- Docker
- Docker Compose
- JUnit 5
- React.js

## Installation

- Clone the repository. <br>

```
    https://github.com/MMKaragoz/loan-application
```

## How to run

There is two ways to run the app.

### <b> Maven and npm </b>

- Open two terminals and make sure of that you are in main directory.

### Starting Backend

<br>
Open first terminal and apply those below respectively. <br>
<b>$PORT: 8080</b>

```ssh
    $ cd .\backend\loanapp\
    $ mvn install
    $ mvn spring-boot:run
```

### Starting Frontend

<br>
Open second terminal and apply those below respectively. <br>
<b>$PORT: 3000 </b>

```ssh
    $ cd .\frontend\loan-app\
    $ npm install
    $ npm start
```

### Swagger Endpoint

<br>

<b>$PORT: 8080</b>

```
http://localhost:${PORT}/swagger-ui/index.html
```
