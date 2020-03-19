package lt.codedicted.egzaminai.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource

@SpringBootApplication
@ImportResource("classpath:context.xml")
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
