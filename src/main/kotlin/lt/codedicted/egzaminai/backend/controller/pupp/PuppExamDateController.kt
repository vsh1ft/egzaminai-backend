package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.core.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.core.service.pupp.PuppExamDateService
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class PuppExamDateController(private val service: PuppExamDateService
) {

    @GetMapping("/pupp-dates")
    fun getDates(): Collection<PuppExamDate> {
        return service.getDates()
    }

    @DatabaseUpdated
    @PostMapping("/pupp-dates")
    fun save(@RequestBody date: PuppExamDate) {
        service.save(date)
    }

    @DatabaseUpdated
    @PutMapping("/pupp-dates")
    fun update(@RequestBody date: PuppExamDate) {
        service.save(date)
    }

    @DatabaseUpdated
    @DeleteMapping("/pupp-dates/{dateId}")
    fun delete(@PathVariable dateId: String) {
        service.delete(dateId)
    }

}
