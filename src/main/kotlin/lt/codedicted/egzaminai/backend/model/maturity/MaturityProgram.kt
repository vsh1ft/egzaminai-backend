package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.Subject
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL

data class MaturityProgram(

    @Length(min = 1, max = 100)
    val name: String,

    val subject: Subject,

    @URL
    val programUrl: String
)
