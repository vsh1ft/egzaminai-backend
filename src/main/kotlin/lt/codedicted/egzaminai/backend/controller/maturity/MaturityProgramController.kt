package lt.codedicted.egzaminai.backend.controller.maturity

import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityProgramRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MaturityProgramController(private val repository: MaturityProgramRepository) {

    @GetMapping("/programs")
    fun getPrograms(): Collection<MaturityProgram> {
        return repository.findAll()
    }

}
