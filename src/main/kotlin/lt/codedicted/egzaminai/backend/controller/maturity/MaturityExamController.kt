package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityExamController(
    private val repository: MaturityExamRepository,
    private val validator: ValidatorToExceptionConverter
) {

    @GetMapping("/exams")
    fun getExams(): Collection<MaturityExam> {
        return repository.findAll()
    }

    @PostMapping("/exams")
    fun save(@RequestBody exam: MaturityExam) {
        validator.validate(exam)
        repository.save(exam.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/exams")
    fun update(@RequestBody  exam: MaturityExam) {
        validator.validate(exam)
        repository.save(exam)
    }

    @DeleteMapping("/exams/{examId}")
    fun delete(@PathVariable examId: String) {
        repository.deleteById(examId)
    }

}
