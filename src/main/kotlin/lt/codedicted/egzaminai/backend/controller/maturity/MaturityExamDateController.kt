package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.core.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.core.service.maturity.MaturityExamDateService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityExamDateController(private val service: MaturityExamDateService
) {

    @GetMapping("/dates")
    fun getDates(): Collection<MaturityExamDate> {
        return service.getDates()
    }

    @DatabaseUpdated
    @PostMapping("/dates")
    fun save(@RequestBody date: MaturityExamDate) {
        service.save(date)
    }

    @DatabaseUpdated
    @PutMapping("/dates")
    fun update(@RequestBody date: MaturityExamDate) {
        service.save(date)
    }

    @DatabaseUpdated
    @DeleteMapping("/dates/{dateId}")
    fun delete(@PathVariable dateId: String) {
        service.delete(dateId)
    }

}
