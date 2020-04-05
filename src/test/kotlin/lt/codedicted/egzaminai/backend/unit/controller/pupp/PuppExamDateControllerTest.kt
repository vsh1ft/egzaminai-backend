package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppExamDateController
import lt.codedicted.egzaminai.core.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.core.model.types.PuppExamName
import lt.codedicted.egzaminai.core.service.pupp.PuppExamDateService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class PuppExamDateControllerTest {

    @MockK
    private lateinit var service: PuppExamDateService

    private lateinit var controller: PuppExamDateController

    @BeforeEach
    fun setUp() {
        controller = PuppExamDateController(service)
    }

    @Test
    fun `Retrieves dates`() {
        val expectedDates = listOf(PuppExamDate("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, "", LocalDateTime.now()))
        every { service.getDates() } returns expectedDates

        val actualDates = controller.getDates()

        assertEquals(expectedDates, actualDates)
    }

    @Test
    fun `Saves date`() {
        val expectedDate =
            PuppExamDate("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING,"", LocalDateTime.now())
        every { service.save(any()) } just Runs

        controller.save(expectedDate)

        verify { service.save(any()) }
    }

    @Test
    fun `Updates date`() {
        val expectedDate =
            PuppExamDate("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING,"", LocalDateTime.now())
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
