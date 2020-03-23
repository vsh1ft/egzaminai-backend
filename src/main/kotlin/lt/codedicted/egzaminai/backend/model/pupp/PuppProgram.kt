package lt.codedicted.egzaminai.backend.model.pupp

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL

data class PuppProgram(

    @Length(min = 1, max = 100)
    val name: String,

    @URL
    val programUrl: String
)
