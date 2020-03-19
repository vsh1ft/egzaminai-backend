package lt.codedicted.egzaminai.backend.controller.pupp

import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import lt.codedicted.egzaminai.backend.repository.pupp.PuppProgramRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class PuppProgramController(private val repository: PuppProgramRepository) {

    @GetMapping("/pupp-programs")
    fun getPrograms(): Collection<PuppProgram> {
        return repository.findAll()
    }

}
