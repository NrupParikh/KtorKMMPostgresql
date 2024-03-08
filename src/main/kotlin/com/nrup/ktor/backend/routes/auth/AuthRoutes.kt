package com.nrup.ktor.backend.routes.auth

import com.nrup.ktor.backend.repository.auth.AuthRepository
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: AuthRepository) {
    val log = this.log

    // URL will be : http://127.0.01:8080/auth/register

    routing {
        route("/auth") {
            post("/register") {
                log.info("Received registration request: $call")
                val params = call.receive<CreateUserParams>()
                val result = repository.registerUser(params)
                call.respond(result.statusCode, result)
            }

    // URL will be : http://127.0.01:8080/auth/login

            post("/login") {
                val params = call.receive<UserLoginParams>()
                val result = repository.loginUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}
