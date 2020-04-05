package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.core.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.core.service.maturity.MaturityProgramService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class MaturityProgramController(private val service: MaturityProgramService) {

    @GetMapping("/programs")
    fun getPrograms(): Collection<MaturityProgram> {
        return service.getPrograms()
    }

    @DatabaseUpdated
    @PostMapping("/programs")
    fun save(@RequestBody program: MaturityProgram) {
        service.save(program)
    }

    @DatabaseUpdated
    @PutMapping("/programs")
    fun update(@RequestBody program: MaturityProgram) {
        service.save(program)
    }

    @DatabaseUpdated
    @DeleteMapping("/programs/{programId}")
    fun delete(@PathVariable programId: String) {
        service.delete(programId)
    }

}
