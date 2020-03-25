package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import org.bson.types.ObjectId
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class MaturityExam(

    @Id val id: String,

    @Length(min = 1, max = 100)
    val name: ExamName,

    @Min(2010)
    @Max(2030)
    val year: Int,

    val type: ExamType,

    @URL
    val examUrl: String,

    @URL
    val answerUrl: String
)