package lt.codedicted.egzaminai.backend.model.maturity

data class MaturityExam(
    val name: String,
    val year: Int,
    val type: String,
    val examUrl: String,
    val answerUrl: String
)
