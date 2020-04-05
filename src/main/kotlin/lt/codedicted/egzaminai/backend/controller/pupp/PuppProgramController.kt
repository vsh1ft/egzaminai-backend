package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import lt.codedicted.egzaminai.backend.repository.pupp.PuppProgramRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class PuppProgramController(private val repository: PuppProgramRepository,
                            private val validator: ValidatorToExceptionConverter
) {

    @GetMapping("/pupp-programs")
    fun getPrograms(): Collection<PuppProgram> {
        return repository.findAll()
    }

    @DatabaseUpdated
    @PostMapping("/pupp-programs")
    fun save(@RequestBody program: PuppProgram) {
        validator.validate(program)
        repository.save(program.copy(id = UUID.randomUUID().toString()))
    }

    @DatabaseUpdated
    @PutMapping("/pupp-programs")
    fun update(@RequestBody program: PuppProgram) {
        validator.validate(program)
        repository.save(program)
    }

    @DatabaseUpdated
    @DeleteMapping("/pupp-programs/{programId}")
    fun delete(@PathVariable programId: String) {
        repository.deleteById(programId)
    }

}
