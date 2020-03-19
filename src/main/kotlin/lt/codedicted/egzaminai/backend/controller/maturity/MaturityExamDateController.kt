package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamDateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MaturityExamDateController(private val repository: MaturityExamDateRepository) {

    @GetMapping("/dates")
    fun getDates(): Collection<MaturityExamDate> {
        return repository.findAll()
    }

}
