package lt.codedicted.egzaminai.backend.repository

import lt.codedicted.egzaminai.backend.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun save(user: User)
    fun findByEmail(email: String): User
    fun existsByEmail(email: String): Boolean
}
