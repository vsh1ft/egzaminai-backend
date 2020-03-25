package lt.codedicted.egzaminai.backend.model.maturity

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class MaturityCourseCredit(

    @Id val id: String,

    @Length(min = 1, max = 100)
    val name: String,

    @Min(2010)
    @Max(2030)
    val year: Int,

    @URL
    val creditUrl: String
)
