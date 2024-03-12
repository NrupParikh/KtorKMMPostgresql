package com.nrup.ktor.backend.repository.auth

import AuthResponse
import SignInParams
import SignUpParams
import com.nrup.ktor.backend.base.BaseResponse

interface AuthRepository {
    suspend fun registerUser(params: SignUpParams): BaseResponse<AuthResponse>
    suspend fun loginUser(params: SignInParams): BaseResponse<AuthResponse>
}