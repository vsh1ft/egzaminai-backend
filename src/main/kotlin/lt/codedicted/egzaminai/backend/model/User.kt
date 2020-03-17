package lt.codedicted.egzaminai.backend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User @PersistenceConstructor constructor(@Indexed(unique = true) val username: String,
                                                    val password: String,
                                                    val roles: MutableList<String> = mutableListOf(),
                                                    @Id var id: ObjectId = ObjectId())
