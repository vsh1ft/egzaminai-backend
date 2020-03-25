package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityExamDateRepository  : MongoRepository<MaturityExamDate, String> {

    fun save(date: MaturityExamDate)

}
