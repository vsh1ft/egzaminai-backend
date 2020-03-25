package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityExamDateController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
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
        val expectedDates = listOf(MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL, LocalDateTime.now()))
        every { repository.findAll() } returns expectedDates

        val actualDates = controller.getDates()

        assertEquals(expectedDates, actualDates)
    }

    @Test
    fun `Saves date`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL, LocalDateTime.now())
        every { repository.save(any()) } just Runs

        controller.save(expectedDate)

        verify { repository.save(any()) }
    }

    @Test
    fun `Updates date`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL, LocalDateTime.now())
        every { repository.save(expectedDate) } just Runs

        controller.update(expectedDate)

        verify { repository.save(expectedDate) }
    }

    @Test
    fun `Deletes date`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
