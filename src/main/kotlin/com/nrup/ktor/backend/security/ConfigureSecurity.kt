package com.nrup.ktor.backend.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

    JWTConfig.initialize("my-ktor-app")
    install(Authentication) {
        jwt {
            verifier(JWTConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfig.CLAM).asInt()
                if (claim != null) {
                    // Validate the user
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }
        }
    }
}