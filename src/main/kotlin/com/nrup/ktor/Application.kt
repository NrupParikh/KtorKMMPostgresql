package com.nrup.ktor


import com.nrup.ktor.backend.config.configureContentNegotiation
import com.nrup.ktor.backend.config.configureDatabase
import com.nrup.ktor.backend.config.configureRouting
import com.nrup.ktor.backend.security.configureSecurity
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {

    // Netty : From io.ktor.server.netty run on port 8080 hosted on 127.0.01 machine

    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "10.37.54.125") {

        configureDatabase()
        configureContentNegotiation()
        configureSecurity()
        configureRouting()

    }.start(wait = true)

}