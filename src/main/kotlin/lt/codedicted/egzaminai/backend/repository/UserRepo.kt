package lt.codedicted.egzaminai.backend.repository

import lt.codedicted.egzaminai.backend.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepo : MongoRepository<User, ObjectId> {
    fun findByUsername(username: String): User?
}
