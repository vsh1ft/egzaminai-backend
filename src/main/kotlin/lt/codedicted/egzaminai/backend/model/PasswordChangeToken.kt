package lt.codedicted.egzaminai.backend.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import javax.validation.constraints.Email

data class PasswordChangeToken(
    @Id val token: String,

    @Email
    val email: String,

    private val date: LocalDateTime = LocalDateTime.now(UTC)
)
