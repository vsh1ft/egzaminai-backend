package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamDateRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityExamDateController(private val repository: MaturityExamDateRepository) {

    @GetMapping("/dates")
    fun getDates(): Collection<MaturityExamDate> {
        return repository.findAll()
    }

    @PostMapping("/dates")
    fun save(@RequestBody date: MaturityExamDate) {
        repository.save(date.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/dates")
    fun update(@RequestBody date: MaturityExamDate) {
        repository.save(date)
    }

    @DeleteMapping("/dates/{dateId}")
    fun delete(@PathVariable dateId: String) {
        repository.deleteById(dateId)
    }

}
