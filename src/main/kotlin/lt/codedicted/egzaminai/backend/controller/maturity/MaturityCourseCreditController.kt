package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import org.springframework.web.bind.annotation.*
import lt.codedicted.egzaminai.core.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.core.service.maturity.MaturityCourseCreditService

@RestController
class MaturityCourseCreditController(private val service: MaturityCourseCreditService) {

    @GetMapping("/credits")
    fun getCredits(): Collection<MaturityCourseCredit> {
        return service.getCredits()
    }

    @DatabaseUpdated
    @PostMapping("/credits")
    fun save(@RequestBody credit: MaturityCourseCredit) {
        service.save(credit)
    }

    @DatabaseUpdated
    @PutMapping("/credits")
    fun update(@RequestBody credit: MaturityCourseCredit) {
        service.update(credit)
    }

    @DatabaseUpdated
    @DeleteMapping("/credits/{creditId}")
    fun delete(@PathVariable creditId: String) {
        service.delete(creditId)
    }

}
