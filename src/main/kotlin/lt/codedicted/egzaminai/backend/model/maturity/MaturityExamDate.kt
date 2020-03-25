package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class MaturityExamDate(

    @Id val id: String,

    val name: ExamName,

    val type: ExamType,

    val dateTime: LocalDateTime
)
