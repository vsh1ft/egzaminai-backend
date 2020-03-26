package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityCourseCreditRepository  : MongoRepository<MaturityCourseCredit, String> {

    fun save(credit: MaturityCourseCredit)

}
