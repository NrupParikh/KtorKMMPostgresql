package com.nrup.ktor.backend.repository.auth

import com.nrup.ktor.backend.security.JWTConfig
import com.nrup.ktor.backend.routes.auth.CreateUserParams
import com.nrup.ktor.backend.routes.auth.UserLoginParams
import com.nrup.ktor.backend.data.service.auth.AuthService
import com.nrup.ktor.backend.base.BaseResponse

class AuthRepositoryImpl(
    private val userService: AuthService
) : AuthRepository {
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

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        val user = userService.loginUser(params.email,params.password)

        return if(user!=null){
            val token = JWTConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(data = user, message = "User loggedIn")
        }else{
            BaseResponse.ErrorResponse("User login failure")
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }

}