package lt.codedicted.egzaminai.backend.config

import com.mongodb.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory

@Configuration
class MongoFactory: AbstractMongoConfiguration() {

    @Value("\${MONGO_DATABASE}")
    private lateinit var databaseName: String

    @Value("\${MONGO_HOST}")
    private lateinit var host: String

    @Bean
    override fun mongoTemplate(): MongoTemplate =
        MongoTemplate(SimpleMongoDbFactory(mongoClient(), databaseName)).apply {
            setWriteConcern(WriteConcern.ACKNOWLEDGED)
        }

    override fun getDatabaseName() = databaseName

    override fun mongoClient() =
        MongoClient(
            ServerAddress(host),
            MongoClientOptions.builder().build()
        )

}
