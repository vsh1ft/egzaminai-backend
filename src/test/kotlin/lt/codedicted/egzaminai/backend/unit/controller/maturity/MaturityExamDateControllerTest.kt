package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityExamDateController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamDateRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class MaturityExamDateControllerTest {

    @MockK
    private lateinit var repository: MaturityExamDateRepository

    private lateinit var controller: MaturityExamDateController

    @BeforeEach
    fun setUp() {
        controller = MaturityExamDateController(repository)
    }

    @Test
    fun `Retrieves dates`() {
        val expectedDates = listOf(MaturityExamDate("user", "VBE", LocalDateTime.now()))
        every { repository.findAll() } returns expectedDates

        val actualDates = controller.getDates()

        assertEquals(expectedDates, actualDates)
    }

}
