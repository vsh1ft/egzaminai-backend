package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdated
import lt.codedicted.egzaminai.core.model.pupp.PuppProgram
import lt.codedicted.egzaminai.core.service.pupp.PuppProgramService
import org.springframework.web.bind.annotation.*


@RestController
class PuppProgramController(private val service: PuppProgramService
) {

    @GetMapping("/pupp-programs")
    fun getPrograms(): Collection<PuppProgram> {
        return service.getPrograms()
    }

    @DatabaseUpdated
    @PostMapping("/pupp-programs")
    fun save(@RequestBody program: PuppProgram) {
        service.save(program)
    }

    @DatabaseUpdated
    @PutMapping("/pupp-programs")
    fun update(@RequestBody program: PuppProgram) {
        service.save(program)
    }

    @DatabaseUpdated
    @DeleteMapping("/pupp-programs/{programId}")
    fun delete(@PathVariable programId: String) {
        service.delete(programId)
    }

}
