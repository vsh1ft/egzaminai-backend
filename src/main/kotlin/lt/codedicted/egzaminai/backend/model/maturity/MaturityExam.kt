package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id
import javax.validation.constraints.*

data class MaturityExam(

    @Id val id: String,

    val name: ExamName,

    @field:Min(2010)
    @field:Max(2030)
    val year: Int = 0,

    val type: ExamType,

    @field:URL
    val examUrl: String,

    @field:URL
    val answerUrl: String
)
