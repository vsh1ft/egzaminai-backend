package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityProgramController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.backend.model.types.Subject
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityProgramRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityProgramControllerTest {

    @MockK
    private lateinit var repository: MaturityProgramRepository

    private lateinit var controller: MaturityProgramController

    @BeforeEach
    fun setUp() {
        controller = MaturityProgramController(repository)
    }

    @Test
    fun `Retrieves programs`() {
        val expectedPrograms = listOf(MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url"))
        every { repository.findAll() } returns expectedPrograms

        val actualPrograms = controller.getPrograms()

        assertEquals(expectedPrograms, actualPrograms)
    }

    @Test
    fun `Saves program`() {
        val expectedProgram =
            MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url")
        every { repository.save(any()) } just Runs

        controller.save(expectedProgram)

        verify { repository.save(any()) }
    }

    @Test
    fun `Updates program`() {
        val expectedProgram =
            MaturityProgram("", "user", Subject.LITHUANIAN_LANGUAGE, "url")
        every { repository.save(expectedProgram) } just Runs

        controller.update(expectedProgram)

        verify { repository.save(expectedProgram) }
    }

    @Test
    fun `Deletes program`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
