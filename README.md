# Ktor for KMM

-----

- **KMM**
  - Kotlin Multiplatform for mobile
  - Kotlin Multiplatform technology simplifies the development of cross-platform projects.
  - The Kotlin applications will work on different operating systems like iOS, Android, macOS, Windows, Linux, watchOS, and others.
- **Ktor**
  - Framework to build connected applications - web app, HTTP services, Mobile and browser app
- **Natty**
  - To run a Ktor server application, you need to create and configure a server first.
  - This server configuration includes an engine, host, port , SSL settings etc.
  - Natty is engine which we are using here.
  - Other engines Ktor supports are Jetty, Tomcat, CIO, ServletApplicationEngine
  - Natty uses JVM platform and HTTP/2 protocol
  
### What this repo includes

-----

- Simple REST API With Ktor and show data in  Android App
- I used IntelliJ IDEA 2022.2.2 (Edu) for development
- Getting random Rabbit information in Json format
- Images are stored in the application itself

### Plugins added are

-----

- **Monitoring**
  - CallLogging : tor provides the capability to log application events using SLF4J library.
- **Routing**
  - Routing is the core Ktor plugin for handling incoming requests in a server application.
 
- **Serialization and ContentNegotiation **
  - kotlinx.serialization : We use this.
  - Gson
  - Jackson

### API Calling

-----

- in cmd type "ipconfig" and copy the address of your Ethernet adapter: IPV4 Address

~~~
Request to get random rabbit

http://localhost:8080/randomrabbit

{
	"name": "Carl",
	"description": "A cute brown rabbit",
	"imageUrl": "http://X.X.X.X:8080/rabbits/1_rabbit.jpg"
}

Request to get image
From Above response copy the Image url and hit in the browser

Load the index.html content in broswer
localhost:8080

output: This is Random Rabbit Example.
~~~

### Reference

-----

#### Project setup and learnings
https://www.youtube.com/watch?v=c6I3Dw0xDlQ
#### Ktor
https://ktor.io/docs/welcome.html
#### KMM
https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html
#### Creating APIs
https://ktor.io/docs/creating-http-apis.html#source_code

### Note
- Use InteliJ IDE for this Ktor KMM Project
- To runt the project, go to Application.kt file and Run the Main function
- Change the BASE_URL in RabbitRoute.kt file

### Postgresql + HIKARI + EXPOSED + JACKSON

#### Ktor [Web framework]
- Ktor is the foundation upon which your web application is built. 
- It provides a lightweight, asynchronous framework for handling HTTP requests and responses, routing, and managing the overall application lifecycle.
- https://ktor.io/

#### PostgreSQL [RDBMS]
- PostgreSQL is a separate database system used to store and manage persistent data for your application.
- Ktor doesn't directly interact with databases; it relies on other libraries like Exposed or JPA to handle database interactions.
- https://www.postgresql.org/
- Ktor with PostgreSQL, Hikari, Exposed and Jackson
- https://www.youtube.com/watch?v=dGln2gzdxq8

#### Hikari [Connection pooling library]
- Hikari is used to manage connections to a database like PostgreSQL. 
- It creates a pool of reusable connections, improving performance and efficiency by avoiding the overhead of creating new connections for each database interaction. 
- Ktor can leverage Hikari to establish efficient database connections through Exposed or JPA.
- How to install PostgreSQL : https://www.youtube.com/watch?v=IYHx0ovvxPs

#### Exposed [Kotlin library for interacting with relational databases]
- Exposed provides a Kotlin-friendly DSL (Domain Specific Language) for building database queries and interacting with relational databases like PostgreSQL.
- It integrates with Ktor and Hikari to simplify database access within your application.

#### Jackson [JSON serialization/deserialization library]
- Jackson is a popular library for working with JSON data in Java applications. 
- It can be used with Ktor for tasks like: Serializing data objects into JSON format for sending in responses.
- Deserializing JSON data received in requests into corresponding Kotlin objects

### Summary
- Ktor acts as the core framework for your web application.
- PostgreSQL is the database that stores your application's data.
- Hikari manages connections to the database efficiently.
- Exposed provides a Kotlin-friendly way to interact with the database.
- Jackson helps in handling JSON data within your application.
- These libraries work together to build a well-structured and efficient web application on the JVM platform.