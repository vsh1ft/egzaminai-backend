package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityCourseCreditRepository
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MaturityCourseCreditController(private val repository: MaturityCourseCreditRepository) {

    @GetMapping("/credits")
    fun getCredits(): Collection<MaturityCourseCredit> {
        return repository.findAll()
    }

}
