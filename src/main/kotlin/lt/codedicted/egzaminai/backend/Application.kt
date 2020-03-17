package lt.codedicted.egzaminai.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@ImportResource("classpath:context.xml")
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
