package lt.codedicted.egzaminai.backend.model.pupp

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id

data class PuppProgram(

    @Id val id: String,

    @Length(min = 1, max = 100)
    val name: String,

    @URL
    val programUrl: String
)
