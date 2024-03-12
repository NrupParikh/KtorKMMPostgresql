package com.nrup.ktor.backend.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val fullName: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String
)