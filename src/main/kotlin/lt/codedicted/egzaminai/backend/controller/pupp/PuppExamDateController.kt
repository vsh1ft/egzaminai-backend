package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class PuppExamDateController(private val repository: PuppExamDateRepository,
                             private val validator: ValidatorToExceptionConverter
) {

    @GetMapping("/pupp-dates")
    fun getDates(): Collection<PuppExamDate> {
        return repository.findAll()
    }

    @DatabaseUpdated
    @PostMapping("/pupp-dates")
    fun save(@RequestBody date: PuppExamDate) {
        validator.validate(date)
        repository.save(date.copy(id = UUID.randomUUID().toString()))
    }

    @DatabaseUpdated
    @PutMapping("/pupp-dates")
    fun update(@RequestBody date: PuppExamDate) {
        validator.validate(date)
        repository.save(date)
    }

    @DatabaseUpdated
    @DeleteMapping("/pupp-dates/{dateId}")
    fun delete(@PathVariable dateId: String) {
        repository.deleteById(dateId)
    }

}
