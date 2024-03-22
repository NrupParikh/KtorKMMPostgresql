package com.nrup.ktor.backend.routes.auth

import AuthResponse
import SignInParams
import SignUpParams
import com.nrup.ktor.backend.repository.auth.AuthRepository
import io.ktor.http.*
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
//                log.info("Received registration request: $call")
                val params = call.receiveNullable<SignUpParams>()
                if (params == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = "Invalid credentials"
                        )
                    )
                    return@post
                }

                val result = repository.registerUser(params)
                call.respond(status = result.statusCode, message = result.data)
            }

            // URL will be : http://127.0.01:8080/auth/login

            post("/login") {
                log.info("Received login request: $call")
                val params = call.receiveNullable<SignInParams>()
                if (params == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = "Invalid credentials"
                        )
                    )
                    return@post
                }

                val result = repository.loginUser(params)
                call.respond(status = result.statusCode, message = result.data)
            }
        }

        /*
            Testing JWT Token working or not
            API listed below the authenticate block required
            JWT token in header or throw error
            401 : Unauthorized
        */

        authenticate {
            get("/test_api") {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = AuthResponse(
                        errorMessage = "User Authenticated"
                    )
                )
            }
        }
    }
}
