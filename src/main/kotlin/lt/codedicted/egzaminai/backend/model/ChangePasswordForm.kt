package lt.codedicted.egzaminai.backend.model

data class ChangePasswordForm(
    val token: String,
    val password: String
)
