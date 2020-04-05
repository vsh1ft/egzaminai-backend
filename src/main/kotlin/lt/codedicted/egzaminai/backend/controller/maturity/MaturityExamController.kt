package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.core.model.maturity.MaturityExam
import lt.codedicted.egzaminai.core.service.maturity.MaturityExamService
import org.springframework.web.bind.annotation.*

@RestController
class MaturityExamController(
   private val service: MaturityExamService
) {

    @GetMapping("/exams")
    fun getExams(): Collection<MaturityExam> {
        return service.getExams()
    }

    @DatabaseUpdated
    @PostMapping("/exams")
    fun save(@RequestBody exam: MaturityExam) {
        service.save(exam)
    }

    @DatabaseUpdated
    @PutMapping("/exams")
    fun update(@RequestBody  exam: MaturityExam) {
        service.save(exam)
    }

    @DatabaseUpdated
    @DeleteMapping("/exams/{examId}")
    fun delete(@PathVariable examId: String) {
        service.delete(examId)
    }

}
