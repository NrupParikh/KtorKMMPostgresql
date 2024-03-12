import com.nrup.ktor.backend.data.models.User
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
    val data: User? = null,
    val errorMessage: String? = null
)