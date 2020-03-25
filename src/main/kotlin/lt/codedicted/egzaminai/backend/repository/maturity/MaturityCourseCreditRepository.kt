package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityCourseCreditRepository  : MongoRepository<MaturityCourseCredit, String> {

    fun save(credit: MaturityCourseCredit)

}
