package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppProgramController
import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import lt.codedicted.egzaminai.backend.repository.pupp.PuppProgramRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class PuppProgramControllerTest {

    @MockK
    private lateinit var repository: PuppProgramRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: PuppProgramController

    @BeforeEach
    fun setUp() {
        controller = PuppProgramController(repository, validator)
    }

    @Test
    fun `Retrieves programs`() {
        val expectedPrograms = listOf(PuppProgram("id", "user", "url"))
        every { repository.findAll() } returns expectedPrograms

        val actualPrograms = controller.getPrograms()

        assertEquals(expectedPrograms, actualPrograms)
    }

    @Test
    fun `Saves program`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.save(expectedProgram)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.save(expectedProgram)

        verify { validator.validate(expectedProgram) }
    }

    @Test
    fun `Updates program`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(expectedProgram) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.update(expectedProgram)

        verify { repository.save(expectedProgram) }
    }

    @Test
    fun `Validates on update`() {
        val expectedProgram =
            PuppProgram("id", "user", "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedProgram) } just Runs

        controller.update(expectedProgram)

        verify { validator.validate(expectedProgram) }
    }

    @Test
    fun `Deletes program`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
