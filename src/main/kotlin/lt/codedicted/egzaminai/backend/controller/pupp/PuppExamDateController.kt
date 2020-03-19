package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class PuppExamDateController(private val repository: PuppExamDateRepository) {

    @GetMapping("/pupp-dates")
    fun getDates(): Collection<PuppExamDate> {
        return repository.findAll()
    }

}
