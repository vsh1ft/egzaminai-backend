package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityExamController(private val repository: MaturityExamRepository) {

    @GetMapping("/exams")
    fun getExams(): Collection<MaturityExam> {
        return repository.findAll()
    }

    @PostMapping("/exams")
    fun save(@RequestBody exam: MaturityExam) {
        repository.save(exam.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/exams")
    fun update(@RequestBody exam: MaturityExam) {
        repository.save(exam)
    }

    @DeleteMapping("/exams/{examId}")
    fun delete(@PathVariable examId: String) {
        repository.deleteById(examId)
    }

}
