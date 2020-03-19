package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppProgramController
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import lt.codedicted.egzaminai.backend.repository.pupp.PuppProgramRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PuppProgramControllerTest {

    @MockK
    private lateinit var repository: PuppProgramRepository

    private lateinit var controller: PuppProgramController

    @BeforeEach
    fun setUp() {
        controller = PuppProgramController(repository)
    }

    @Test
    fun `Retrieves programs`() {
        val expectedPrograms = listOf(PuppProgram("user", "url"))
        every { repository.findAll() } returns expectedPrograms

        val actualPrograms = controller.getPrograms()

        assertEquals(expectedPrograms, actualPrograms)
    }

}
