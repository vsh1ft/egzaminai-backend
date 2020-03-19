package lt.codedicted.egzaminai.backend.model.maturity

import java.time.LocalDateTime

data class MaturityExamDate(
    val name: String,
    val type: String,
    val dateTime: LocalDateTime
)
