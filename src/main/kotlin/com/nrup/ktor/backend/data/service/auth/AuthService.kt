package com.nrup.ktor.backend.data.service.auth

import com.nrup.ktor.backend.data.models.User
import com.nrup.ktor.backend.routes.auth.CreateUserParams

interface AuthService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(params: String): User?
    suspend fun loginUser(email: String, password: String): User?
}