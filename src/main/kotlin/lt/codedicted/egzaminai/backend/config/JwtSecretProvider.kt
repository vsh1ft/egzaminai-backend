package lt.codedicted.egzaminai.backend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtSecretProvider {

    @Value("\${JWT_SECRET}")
    lateinit var value: String

}
