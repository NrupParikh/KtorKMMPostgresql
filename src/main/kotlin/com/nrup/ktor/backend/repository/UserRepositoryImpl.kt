package com.nrup.ktor.backend.repository

import com.nrup.ktor.backend.security.JWTConfig
import com.nrup.ktor.backend.service.CreateUserParams
import com.nrup.ktor.backend.service.UserService
import com.nrup.ktor.backend.util.BaseResponse

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "Email already exist")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, message = "User inserted successfully")
            } else {
                BaseResponse.ErrorResponse(message = "User is null")
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }

}