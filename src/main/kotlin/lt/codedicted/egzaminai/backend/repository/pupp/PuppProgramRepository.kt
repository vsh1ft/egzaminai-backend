package lt.codedicted.egzaminai.backend.repository.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppProgramRepository: MongoRepository<PuppProgram, String> {

    fun save(program: PuppProgram)

}


