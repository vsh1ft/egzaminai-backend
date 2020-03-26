package lt.codedicted.egzaminai.backend.repository.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppProgramRepository: MongoRepository<PuppProgram, String> {

    fun save(program: PuppProgram)

}


