package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.Subject
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id

data class MaturityProgram(

    @Id val id: String,

    @field:Length(min = 1, max = 100)
    val name: String,

    val subject: Subject,

    @field:URL
    val programUrl: String
)
