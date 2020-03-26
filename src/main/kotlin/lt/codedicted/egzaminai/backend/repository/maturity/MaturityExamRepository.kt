package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityExamRepository: MongoRepository<MaturityExam, String> {

    fun save(exam: MaturityExam)

}
