package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class PuppExamDateController(private val repository: PuppExamDateRepository) {

    @GetMapping("/pupp-dates")
    fun getDates(): Collection<PuppExamDate> {
        return repository.findAll()
    }

    @PostMapping("/pupp-dates")
    fun save(@RequestBody date: PuppExamDate) {
        repository.save(date.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/pupp-dates")
    fun update(@RequestBody date: PuppExamDate) {
        repository.save(date)
    }

    @DeleteMapping("/pupp-dates/{dateId}")
    fun delete(@PathVariable dateId: String) {
        repository.deleteById(dateId)
    }

}
