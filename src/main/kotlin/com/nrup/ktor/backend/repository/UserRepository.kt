package com.nrup.ktor.backend.repository

import com.nrup.ktor.backend.service.CreateUserParams
import com.nrup.ktor.backend.util.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}