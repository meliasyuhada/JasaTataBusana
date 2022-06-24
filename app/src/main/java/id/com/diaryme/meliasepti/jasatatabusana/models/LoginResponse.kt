package id.com.diaryme.meliasepti.jasatatabusana.models

data class LoginResponse(
    val message: String,
    val error: Boolean,
    val data: User
)
