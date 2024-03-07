package com.nrup.ktor.plugins

import com.nrup.ktor.routes.customerRouting
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import randomRabbitRouting

fun Application.configureRouting() {

    routing {
        randomRabbitRouting()
//        get("/") {
//            call.respondText("Hello World! This is Nrup Parikh")
//        }
        // Static plugin. Try to access `/static/index.html`

        staticResources("/", "static")

        // Customer Route
        customerRouting()

    }
}
