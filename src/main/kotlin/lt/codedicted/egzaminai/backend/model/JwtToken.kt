package lt.codedicted.egzaminai.backend.model

import org.springframework.data.annotation.Id

data class JwtToken (
    @Id val token: String
)
