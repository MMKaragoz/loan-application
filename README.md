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
    git clone https://github.com/MMKaragoz/loan-application
```

## How to run

There is two ways to run the app.

### <b> Maven and npm </b>

- Open two terminals and make sure of that you are in main directory.

#### <b> Starting Backend </b>

<br>
Open first terminal and apply those below respectively. <br>
<b>$PORT: 8080</b>

```ssh
    $ cd .\backend\loanapp\
    $ mvn install
    $ mvn spring-boot:run
```

#### <b> Starting Frontend </b>

<br>
Open second terminal and apply those below respectively. <br>
<b>$PORT: 3000 </b>

```ssh
    $ cd .\frontend\loan-app\
    $ npm install
    $ npm start
```

### <b> Docker Compose </b>

```ssh
 docker-compose up
```

## Archictecture

![architecture](/docs/loan-app-archictecture.svg)

## Database Diagram

![diagram](/docs/loan-application-db.png)

### Swagger Endpoint

<br>

<b>$PORT: 8080</b>

```
http://localhost:${PORT}/swagger-ui/index.html
```
