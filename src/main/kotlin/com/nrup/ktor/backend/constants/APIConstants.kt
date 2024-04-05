package com.nrup.ktor.backend.constants

class APIConstants {
    companion object{

        // Validation messages
        const val msgEnterFullName = "Please enter full name"
        const val msgEnterEmail = "Please enter email"
        const val msgEnterValidEmail = "Please enter valid email"
        const val msgEnterPassword = "Please enter password"
        const val msgInvalidPassword = "Password must be 6 character long"
        const val msgUserAlreadyExist = "User already exist"

        const val msgEnterPostTitle = "Please enter Post title"
        const val msgEnterImageUrl = "Please enter Image URL"
        const val msgEnterValidImageUrl = "Please enter valid Image URL"


        // General messages
        const val msgInvalidCredentials = "Invalid credentials"
        const val msgSomethingWentWrong = "Something went wrong"
        const val msgNoPostFound = "No post found"
        const val msgUserAuthenticated = "User Authenticated"

        // Auth
        const val routeAuth = "/auth"
        const val pathRegister = "/register"
        const val pathLogin = "/login"
        const val pathTestAPI = "/test_api"

        // Post
        const val routePost = "/post"
        const val pathCreatePost = "/createPost"
        const val pathGetAllPost = "/getAllPost"
    }
}