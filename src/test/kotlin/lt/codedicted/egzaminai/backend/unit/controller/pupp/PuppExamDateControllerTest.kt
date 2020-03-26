package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppExamDateController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class PuppExamDateControllerTest {

    @MockK
    private lateinit var repository: PuppExamDateRepository

    private lateinit var controller: PuppExamDateController

    @BeforeEach
    fun setUp() {
        controller = PuppExamDateController(repository)
    }

    @Test
    fun `Retrieves dates`() {
        val expectedDates = listOf(PuppExamDate("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, LocalDateTime.now()))
        every { repository.findAll() } returns expectedDates

        val actualDates = controller.getDates()

        assertEquals(expectedDates, actualDates)
    }

    @Test
    fun `Saves date`() {
        val expectedDate =
            PuppExamDate("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, LocalDateTime.now())
        every { repository.save(any()) } just Runs

        controller.save(expectedDate)

        verify { repository.save(any()) }
    }

    @Test
    fun `Updates date`() {
        val expectedDate =
            PuppExamDate("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, LocalDateTime.now())
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
