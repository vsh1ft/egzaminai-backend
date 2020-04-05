package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PuppExamController(private val repository: PuppExamRepository,
                         private val validator: ValidatorToExceptionConverter
) {

    @GetMapping("/pupp-exams")
    fun getExams(): Collection<PuppExam> {
        return repository.findAll()
    }

    @DatabaseUpdated
    @PostMapping("/pupp-exams")
    fun save(@RequestBody exam: PuppExam) {
        validator.validate(exam)
        repository.save(exam.copy(id = UUID.randomUUID().toString()))
    }

    @DatabaseUpdated
    @PutMapping("/pupp-exams")
    fun update(@RequestBody exam: PuppExam) {
        validator.validate(exam)
        repository.save(exam)
    }

    @DatabaseUpdated
    @DeleteMapping("/pupp-exams/{puppExamId}")
    fun delete(@PathVariable puppExamId: String) {
        repository.deleteById(puppExamId)
    }

}
