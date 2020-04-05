package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityExamDateController
import lt.codedicted.egzaminai.core.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.core.model.types.ExamName
import lt.codedicted.egzaminai.core.model.types.ExamType
import lt.codedicted.egzaminai.core.service.maturity.MaturityExamDateService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class MaturityExamDateControllerTest {

    @MockK
    private lateinit var service: MaturityExamDateService

    private lateinit var controller: MaturityExamDateController

    @BeforeEach
    fun setUp() {
        controller = MaturityExamDateController(service)
    }

    @Test
    fun `Retrieves dates`() {
        val expectedDates = listOf(MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"" ,LocalDateTime.now()))
        every { service.getDates() } returns expectedDates

        val actualDates = controller.getDates()

        assertEquals(expectedDates, actualDates)
    }

    @Test
    fun `Saves date`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"" , LocalDateTime.now())
        every { service.save(any()) } just Runs

        controller.save(expectedDate)

        verify { service.save(any()) }
    }

    @Test
    fun `Updates date`() {
        val expectedDate =
            MaturityExamDate("id", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL, "" ,LocalDateTime.now())
        every { service.save(expectedDate) } just Runs

        controller.update(expectedDate)

        verify { service.save(expectedDate) }
    }

    @Test
    fun `Deletes date`() {
        every { service.delete("id") } just Runs

        controller.delete("id")

        verify { service.delete("id") }
    }

}
