package lt.codedicted.egzaminai.backend.repository

import lt.codedicted.egzaminai.backend.model.PasswordChangeToken
import org.springframework.data.mongodb.repository.MongoRepository

interface PasswordChangeRepository: MongoRepository<PasswordChangeToken, String> {
    fun save(token: PasswordChangeToken)
}
