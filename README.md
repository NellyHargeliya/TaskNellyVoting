
springboot-rest-api

Sample showing REST API implementation using Spring Boot

Application to demonstrate various parts of a service oriented RESTfull application.

Technology Stack

Component	Technology
Frontend	js
Backend (REST)	SpringBoot (Java 8)
REST Documentation	Swagger UI / Springfox and ReDoc
REST Spec	Open API Standard
In Memory DB	H2
Persistence	JPA (Using Spring Data)
Server Build Tools	Maven(Java) 

About

This is an RESTfull implementation of an order processing app based on Northwind database schema from Microsoft. The goal of the project is to

Highlight techniques of making and securing a REST full app using SpringBoot
How to consume an RESTfull service and make an HTML5 based Single Page App using JS

In Memory DB (H2)

I have included an in-memory database for the application. Database schema and sample data for the app is created everytime the app starts, and gets destroyed after the app stops, so the changes made to to the database are persistent only as long as the app is running 
Creation of database schema and data are done using sql scripts that Springs runs automatically. To modify the database schema or the data you can modify schema.sql which can be found at /src/main/resources
