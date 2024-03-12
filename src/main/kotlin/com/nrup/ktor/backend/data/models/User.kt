package com.nrup.ktor.backend.data.models

data class User(
    val id: Int,
    val fullName: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String
)