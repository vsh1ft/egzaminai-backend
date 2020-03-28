package lt.codedicted.egzaminai.backend.model.maturity

import org.springframework.data.annotation.Id

class PasswordResetToken (

    @Id
     val id: String,
     val token: String

)
