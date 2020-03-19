package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PuppExamController(private val repository: PuppExamRepository) {

    @GetMapping("/pupp-exams")
    fun getExams(): Collection<PuppExam> {
        return repository.findAll()
    }

}
