package lt.codedicted.egzaminai.backend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Document
data class User @PersistenceConstructor constructor(

    @Email
    @Indexed(unique = true)
    val email: String,

    @field:Size(min = 4, message = "Password too short")
    val password: String,

    val roles: MutableList<String> = mutableListOf(),
    @Id var id: ObjectId = ObjectId()
)
