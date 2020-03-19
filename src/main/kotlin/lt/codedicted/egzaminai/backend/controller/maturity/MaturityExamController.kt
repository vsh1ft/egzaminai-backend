package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MaturityExamController(private val repository: MaturityExamRepository) {

    @GetMapping("/exams")
    fun getExams(): Collection<MaturityExam> {
        return repository.findAll()
    }

}
