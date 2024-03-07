package com.nrup.ktor.backend.service

import com.nrup.ktor.backend.models.User

interface UserService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun findUserByEmail(email: String): User?
}