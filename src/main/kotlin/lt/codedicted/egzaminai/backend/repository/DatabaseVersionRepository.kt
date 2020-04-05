package lt.codedicted.egzaminai.backend.repository

import lt.codedicted.egzaminai.backend.model.DatabaseVersion
import lt.codedicted.egzaminai.backend.model.JwtToken
import lt.codedicted.egzaminai.backend.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface DatabaseVersionRepository : MongoRepository<DatabaseVersion, String> {

    fun save(db: DatabaseVersion)

}
