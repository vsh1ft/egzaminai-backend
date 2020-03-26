package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PuppExamController(private val repository: PuppExamRepository) {

    @GetMapping("/pupp-exams")
    fun getExams(): Collection<PuppExam> {
        return repository.findAll()
    }

    @PostMapping("/pupp-exams")
    fun save(@RequestBody exam: PuppExam) {
        repository.save(exam.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/pupp-exams")
    fun update(@RequestBody exam: PuppExam) {
        repository.save(exam)
    }

    @DeleteMapping("/pupp-exams/{puppExamId}")
    fun delete(@PathVariable puppExamId: String) {
        repository.deleteById(puppExamId)
    }

}
