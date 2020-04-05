package lt.codedicted.egzaminai.backend.model.pupp

import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class PuppExam(

    @Id val id: String,

    val name: PuppExamName,

    @field:Min(2010)
    @field:Max(2030)
    val year: Int,

    @field:URL
    val examUrl: String
)
