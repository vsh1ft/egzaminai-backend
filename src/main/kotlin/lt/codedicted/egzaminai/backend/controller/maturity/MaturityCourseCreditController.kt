package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityCourseCreditRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityCourseCreditController(private val repository: MaturityCourseCreditRepository,
                                     private val validator: ValidatorToExceptionConverter
) {

    @GetMapping("/credits")
    fun getCredits(): Collection<MaturityCourseCredit> {
        return repository.findAll()
    }

    @PostMapping("/credits")
    fun save(@RequestBody credit: MaturityCourseCredit) {
        validator.validate(credit)
        repository.save(credit.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/credits")
    fun update(@RequestBody credit: MaturityCourseCredit) {
        validator.validate(credit)
        repository.save(credit)
    }

    @DeleteMapping("/credits/{creditId}")
    fun delete(@PathVariable creditId: String) {
        repository.deleteById(creditId)
    }

}
