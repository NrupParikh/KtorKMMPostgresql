package com.nrup.ktor.backend.routes.auth

data class CreateUserParams(
    val fullName: String,
    val email: String,
    val password: String,
)