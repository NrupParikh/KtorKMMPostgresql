package com.nrup.ktor


import com.nrup.ktor.backend.config.configureContentNegotiation
import com.nrup.ktor.backend.config.configureDatabase
import com.nrup.ktor.backend.config.configureRouting
import com.nrup.ktor.backend.security.configureSecurity
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    // ========================= SAMPLE
//    embeddedServer(
//        Netty,
//        port = 8080
//    ){
//        routing {
//            get("/my_api"){
//                call.respond("Hello, world! how are you")
//            }
//        }
//    }.start(wait = true)

    // ============================================

    // Netty : From io.ktor.server.netty run on port 8080 hosted on 127.0.01 machine

    embeddedServer(
        Netty,
        port = 8080,
        host = "10.37.54.125",
        module = Application::module
    ).start(wait = true)

}

fun Application.module() {

    // Database and Table creation and connection
    configureDatabase()

    // Content negotiation between client-server (serialization and deserialization)
    configureContentNegotiation()

    // Configure security with JWT Auth token
    configureSecurity()

    // Defining the routes (API methods)
    configureRouting()

}