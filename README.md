
# springboot-rest-api


***Technology Stack***

* Frontend	js 
 * REST	
 * SpringBoot 
 * Java 8 
 * DB	H2 
 * JDBC 
 * Maven (Java)

***About***

This is a simple standalone java application which provides all necessary actions to work with various topics for voting.
Client-server communication: REST; content type: JSON.

Highlight techniques of making and securing a REST full app using SpringBoot How to consume an RESTfull service and make an HTML5 based Single Page App using JS

***In Memory DB (H2)***

I have included an in-memory database for the application. Database schema and sample data for the app is created everytime the app starts, and gets destroyed after the app stops, so the changes made to to the database are persistent only as long as the app is running Creation of database schema and data are done using sql scripts that Springs runs automatically. To modify the database schema or the data you can modify schema.sql which can be found at /src/main/resources
