## General Information 


This application is an example of using Spring Boot and the HAPI (Healthcare API for Java) library
for managing and creating HL7 messages. The application includes several classes including:

A Spring Boot controller that exposes an endpoint for creating HL7 messages. (HapiController),
a Service class that creates and manages the creation of HL7 messages using the HAPI library. (HapiService),
a factory class for creating different types of HL7 messages. (AdtMessageFactory),
a class for constructing HL7 messages of type ADT A01. (AdtA01MessageBuilder).

## Configuration
The main configuration of the application is managed through the application.yaml file
-Port on which the Spring Cloud Gateway server listens for HTTP requests.
port: 8080
-Definition of routes for redirecting requests
spring.cloud.gateway.routes:

## Setting
Make sure you have Java 17+ installed.
GitHub repository: https://github.com/gaetanoo/hl7-gateway