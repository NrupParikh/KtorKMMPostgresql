package com.nrup.ktor.backend.data.service.auth

import AuthResponseData
import SignUpParams

interface AuthService {
    suspend fun registerUser(params: SignUpParams): AuthResponseData?
    suspend fun findUserByEmail(params: String): AuthResponseData?
    suspend fun loginUser(email: String, password: String): AuthResponseData?
}