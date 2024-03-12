package com.nrup.ktor.backend.repository.auth

import AuthResponse
import SignInParams
import SignUpParams
import com.nrup.ktor.backend.security.JWTConfig
import com.nrup.ktor.backend.data.service.auth.AuthService
import com.nrup.ktor.backend.base.BaseResponse
import io.ktor.http.*

class AuthRepositoryImpl(
    private val userService: AuthService
) : AuthRepository {
    override suspend fun registerUser(params: SignUpParams): BaseResponse<AuthResponse> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(
                code = HttpStatusCode.Conflict,
                data = AuthResponse(errorMessage = "User already exist")
            )
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = AuthResponse(user))
            } else {
                BaseResponse.ErrorResponse(
                    code = HttpStatusCode.InternalServerError,
                    data = AuthResponse(errorMessage = "Something went wrong")
                )
            }
        }
    }

    override suspend fun loginUser(params: SignInParams): BaseResponse<AuthResponse> {
        val user = userService.loginUser(params.email, params.password)

        return if (user != null) {
            val token = JWTConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(data = AuthResponse(user))
        } else {
            BaseResponse.ErrorResponse(
                code = HttpStatusCode.Forbidden,
                data = AuthResponse(errorMessage = "Invalid credentials")
            )
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }

}