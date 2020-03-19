package lt.codedicted.egzaminai.backend.repository.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MaturityCourseCreditRepository  : MongoRepository<MaturityCourseCredit, ObjectId>
