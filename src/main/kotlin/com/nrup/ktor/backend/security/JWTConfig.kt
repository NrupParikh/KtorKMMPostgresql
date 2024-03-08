package com.nrup.ktor.backend.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JWTConfig private constructor(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIANCE)
        .build()

    // Generate access token for the user with use of id
    fun createAccessToken(id: Int): String =
        JWT.create()
            .withAudience(AUDIANCE)
            .withIssuer(ISSUER)
            .withClaim(CLAM, id)
            .sign(algorithm)

    companion object {
        private const val ISSUER = "my-ktor-app"
        private const val AUDIANCE = "my-ktor-app"
        const val CLAM = "id"

        // Initialize the JWT configuration because our constructor is private

        lateinit var instance: JWTConfig
            private set

        fun initialize(secret: String) {
            synchronized(this) {
                if (!this::instance.isInitialized) {
                    instance = JWTConfig(secret)
                }
            }
        }
    }
}