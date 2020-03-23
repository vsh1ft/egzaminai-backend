package lt.codedicted.egzaminai.backend.model.pupp

import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class PuppExam(

    val name: PuppExamName,

    @Min(2010)
    @Max(2030)
    val year: Int,

    @URL
    val examUrl: String
)
