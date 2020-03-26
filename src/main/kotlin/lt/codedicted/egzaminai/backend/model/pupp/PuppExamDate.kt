package lt.codedicted.egzaminai.backend.model.pupp

import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class PuppExamDate(

    @Id val id: String,

    val name: PuppExamName,

    val dateTime: LocalDateTime
)
