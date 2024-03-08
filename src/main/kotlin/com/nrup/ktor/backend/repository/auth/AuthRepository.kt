package com.nrup.ktor.backend.repository.auth

import com.nrup.ktor.backend.routes.auth.CreateUserParams
import com.nrup.ktor.backend.routes.auth.UserLoginParams
import com.nrup.ktor.backend.base.BaseResponse

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>
}