package com.nrup.ktor.plugins

import com.nrup.ktor.backend.repository.UserRepository
import com.nrup.ktor.backend.service.CreateUserParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: UserRepository) {
    val log = this.log
    routing {
        route("/auth") {
            post("/register") {
                log.info("Received registration request: $call")
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result.statusCode, result)
            }
        }

    }
}
