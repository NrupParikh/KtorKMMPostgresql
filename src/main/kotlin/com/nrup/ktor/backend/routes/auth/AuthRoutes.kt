package com.nrup.ktor.backend.routes.auth

import AuthResponse
import SignInParams
import SignUpParams
import com.nrup.ktor.backend.constants.APIConstants
import com.nrup.ktor.backend.constants.AppConstants
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
        route(APIConstants.routeAuth) {

            // =========== REGISTER
            post(APIConstants.pathRegister) {
//                log.info("Received registration request: $call")
                val params = call.receiveNullable<SignUpParams>()
                if (params == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgInvalidCredentials
                        )
                    )
                    return@post
                } else if (params.fullName.isEmpty()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterFullName
                        )
                    )
                    return@post
                } else if (params.email.isEmpty()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterEmail
                        )
                    )
                    return@post
                } else if (AppConstants.emailRegex.matches(params.email).not()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterValidEmail
                        )
                    )
                    return@post

                } else if (params.password.isEmpty()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterPassword
                        )
                    )
                    return@post
                } else if (params.password.length < 6) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgInvalidPassword
                        )
                    )
                    return@post
                }

                val result = repository.registerUser(params)
                call.respond(status = result.statusCode, message = result.data)
            }

            // URL will be : http://127.0.01:8080/auth/login

            // =========== LOGIN
            post(APIConstants.pathLogin) {
                log.info("Received login request: $call")
                val params = call.receiveNullable<SignInParams>()
                if (params == null) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgInvalidCredentials
                        )
                    )
                    return@post
                } else if (params.email.isEmpty()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterEmail
                        )
                    )
                    return@post
                }
                else if (AppConstants.emailRegex.matches(params.email).not()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterValidEmail
                        )
                    )
                    return@post

                }
                else if (params.password.isEmpty()) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgEnterPassword
                        )
                    )
                    return@post
                } else if (params.password.length < 6) {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = AuthResponse(
                            errorMessage = APIConstants.msgInvalidPassword
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
            get(APIConstants.pathTestAPI) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = AuthResponse(
                        errorMessage = APIConstants.msgUserAuthenticated
                    )
                )
            }
        }
    }
}
