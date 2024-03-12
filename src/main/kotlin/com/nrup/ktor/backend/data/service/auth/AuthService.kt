package com.nrup.ktor.backend.data.service.auth

import SignUpParams
import com.nrup.ktor.backend.data.models.User

interface AuthService {
    suspend fun registerUser(params: SignUpParams): User?
    suspend fun findUserByEmail(params: String): User?
    suspend fun loginUser(email: String, password: String): User?
}