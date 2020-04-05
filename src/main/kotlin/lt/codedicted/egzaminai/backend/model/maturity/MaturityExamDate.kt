package lt.codedicted.egzaminai.backend.model.maturity

import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import org.springframework.data.annotation.Id
import java.time.LocalDateTime
import javax.validation.constraints.Size

data class MaturityExamDate(

    @Id val id: String,

    val name: ExamName,

    val type: ExamType,

    @field:Size(min = 1, max = 10)
    val color: String,

    val dateTime: LocalDateTime
)
