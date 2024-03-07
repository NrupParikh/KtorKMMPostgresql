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

#### Postgresql
- open source object-relational database

#### HIKARI
- For Connection pooling and caching

#### EXPOSED
- Database persistance with exposed
- Creating database and tables

#### JACKSON
- JSON Parser API provides easy way to convert JSON to POJO Object and supports easy conversion to Map from JSON data

### Reference

- Ktor
https://ktor.io/

- PostgreSQL
https://www.postgresql.org/

- Ktor with PostgreSQL, Hikari, Exposed and Jackson
https://www.youtube.com/watch?v=dGln2gzdxq8

- How to install PostgreSQL
https://www.youtube.com/watch?v=IYHx0ovvxPs

