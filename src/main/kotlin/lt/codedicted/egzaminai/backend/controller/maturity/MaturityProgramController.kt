package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityProgramRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityProgramController(private val repository: MaturityProgramRepository,
                                private val validator: ValidatorToExceptionConverter
) {

    @GetMapping("/programs")
    fun getPrograms(): Collection<MaturityProgram> {
        return repository.findAll()
    }

    @PostMapping("/programs")
    fun save(@RequestBody program: MaturityProgram) {
        validator.validate(program)
        repository.save(program.copy(id = UUID.randomUUID().toString()))
    }

    @PutMapping("/programs")
    fun update(@RequestBody program: MaturityProgram) {
        validator.validate(program)
        repository.save(program)
    }

    @DeleteMapping("/programs/{programId}")
    fun delete(@PathVariable programId: String) {
        repository.deleteById(programId)
    }

}
