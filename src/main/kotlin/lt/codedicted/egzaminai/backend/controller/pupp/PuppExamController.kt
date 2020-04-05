package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.core.model.pupp.PuppExam
import lt.codedicted.egzaminai.core.service.pupp.PuppExamService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PuppExamController(private val service: PuppExamService
) {

    @GetMapping("/pupp-exams")
    fun getExams(): Collection<PuppExam> {
        return service.getExams()
    }

    @DatabaseUpdated
    @PostMapping("/pupp-exams")
    fun save(@RequestBody exam: PuppExam) {
        service.save(exam)
    }

    @DatabaseUpdated
    @PutMapping("/pupp-exams")
    fun update(@RequestBody exam: PuppExam) {
        service.save(exam)
    }

    @DatabaseUpdated
    @DeleteMapping("/pupp-exams/{puppExamId}")
    fun delete(@PathVariable puppExamId: String) {
        service.delete(puppExamId)
    }

}
