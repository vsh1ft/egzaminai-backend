package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import lt.codedicted.egzaminai.backend.repository.pupp.PuppProgramRepository
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class PuppProgramController(private val repository: PuppProgramRepository) {

    @GetMapping("/pupp-programs")
    fun getPrograms(): Collection<PuppProgram> {
        return repository.findAll()
    }

    @PostMapping("/pupp-programs")
    fun save(@RequestBody program: PuppProgram) {
        repository.save(program.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/pupp-programs")
    fun update(@RequestBody program: PuppProgram) {
        repository.save(program)
    }

    @DeleteMapping("/pupp-programs/{programId}")
    fun delete(@PathVariable programId: String) {
        repository.deleteById(programId)
    }

}
