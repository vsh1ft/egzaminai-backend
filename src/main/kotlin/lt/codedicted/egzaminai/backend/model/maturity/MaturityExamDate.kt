package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import java.time.LocalDateTime

data class MaturityExamDate(

    val name: ExamName,

    val type: ExamType,

    val dateTime: LocalDateTime
)
