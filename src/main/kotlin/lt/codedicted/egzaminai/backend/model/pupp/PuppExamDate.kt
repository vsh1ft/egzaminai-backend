package lt.codedicted.egzaminai.backend.model.pupp

import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import java.time.LocalDateTime

data class PuppExamDate(

    val name: PuppExamName,

    val dateTime: LocalDateTime
)
