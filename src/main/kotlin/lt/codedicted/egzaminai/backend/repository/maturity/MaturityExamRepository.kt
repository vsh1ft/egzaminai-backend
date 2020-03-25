package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityExamRepository: MongoRepository<MaturityExam, ObjectId> {

    fun save(exam: MaturityExam)

    fun deleteById(id: String)

}
