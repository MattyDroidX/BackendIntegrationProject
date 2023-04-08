# Backend Integrator Project

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a REST Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Shift Reservation System

It is desired to implement a system that allows managing the reservation of shifts for a dental clinic. This must meet the following requirements:
* <strong>Dentist data management: </strong>list, add, modify and delete dentists. Register last name, first name and license plate of the same.
* <strong>Patient data management: </strong>list, add, modify and delete patients. For each one, the following are stored: name, surname, address, ID and registration date.
* <strong>Register shift: </strong>it must be possible to allow a patient to be assigned a shift with a dentist at a certain date and time.
* <strong>Login: </strong>validate the entry to the system by means of a login with username and password. Any logged-in user (ROLE_USER) should be allowed to register an appointment, but only those with an administration role (ROLE_ADMIN) to be able to manage dentists and patients. A user may have a single role, and they will be entered directly into the database.


### Technical Requirements

The application must be developed in layers:
* <strong>Business entities layer: </strong>they are the Java classes of our business modeled through the object-oriented paradigm.
* <strong>Data access layer (Repository): </strong> these are the classes that will be in charge of accessing the database.
* <strong>Data layer (database): </strong>is the database of our system modeled through an entity-relationship model. We will use the H2 base for its practicality.
* <strong>Business layer: </strong>they are the service classes that are in charge of decoupling the access to data from the view.
* <strong>Presentation layer: </strong>these are the web screens that we will have to develop using the Spring Boot MVC framework with the controllers and one of these two options: HTML+JavaScript or React for the view.
  
It is important to handle exceptions by logging any exception that may be generated and carrying out unit tests to guarantee the quality of the developments.


### Progress

The work will have a single final delivery, but to help you organize, we suggest that you progress as follows:

### Sprint 0 (Start)

Once the course has started with the knowledge already acquired in Object-Oriented Programming, Database I and Front End I, you will be able to start building your UML model of the classes you will need for the integrating project as well as everything related to the tables of the relational database that you will need to persist the data and the HTML screens with their styles to enter them. Do not worry that throughout the course you will learn to integrate all these parts!!!

### Sprint 1 (Start of week 1 to End of week 2)

With what you have learned during these weeks you will be able to carry out unit tests of the Java classes that you have programmed. To make sure from now on that with each change your software continues to work unit tests are very important.

### Sprint 2 (Start of week 3 to End of week 4)

During this sprint with everything learned during the course in class 18 you will be able to work with Maven in your project to reference your libraries and with what we saw in class 14 you will be able to build your DAO classes (data access layer with JDBC) and the service classes (business layer) for each of the entities of your project, always being able to guarantee the operation of everything you build using unit tests.


### Sprint 3 (Start of week 5 to End of week 6)

Throughout this sprint you will be refactoring the data access layer to be able to access and retrieve them through an ORM. Creating the mappings and the Repository classes that will be replaced by the DAO fulfilling the same function.
With everything learned in classes 25, 27 and 28 you will be able to build the APIs during this sprint (through the development of the controllers) and the integration with the presentation layer, that is, the HTML screens, through javascript.

### Sprint 4 (Start of week 7 to End of week 8)

The simplest remains for last. With the knowledge acquired in class 43, you can very easily add a login with Spring Security to your project.
Class 48 delivery. You will have time throughout the day to deliver until 11:59 p.m.


### How to Run!

Once you clone the repository you can check the endpoints on this website:

```
  localhost:8085/swagger-ui/index.html
```
To check other endpoints instead you will have to add credentials as:

```
    username: "admin"
    password: "password"
```
You can also check the docker registry with all the related versions:

```
    https://hub.docker.com/repository/docker/mattydroidx/backend-integration-project/general
```

You can run the docker command with the following steps:

```
    docker run -p 8085:8085 mattydroidx/backend-integration-project:latest
```

For the latest version.

For further information you can reach me at fernando.matias.duarte@gmail.com

Thanks for sharing!
Happy coding.