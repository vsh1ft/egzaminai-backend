package lt.codedicted.egzaminai.backend.repository.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import org.springframework.data.mongodb.repository.MongoRepository

interface PuppExamDateRepository: MongoRepository<PuppExamDate, String> {

    fun save(exam: PuppExamDate)

}


