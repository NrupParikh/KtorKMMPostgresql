import kotlinx.serialization.Serializable

@Serializable
data class SignUpParams(
    val fullName: String,
    val email: String,
    val password: String,
)

@Serializable
data class SignInParams(val email: String, val password: String)

@Serializable
data class AuthResponse(
    val data: AuthResponseData? = null,
    val errorMessage: String? = null
)

@Serializable
data class AuthResponseData(
    val id: Int,
    val fullName: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String
)