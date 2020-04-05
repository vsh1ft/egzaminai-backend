package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityCourseCreditController
import lt.codedicted.egzaminai.core.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.core.service.maturity.MaturityCourseCreditService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityCourseCreditControllerTest {


    @MockK
    private lateinit var service: MaturityCourseCreditService

    private lateinit var controller: MaturityCourseCreditController

    @BeforeEach
    fun setUp() {
        controller = MaturityCourseCreditController(service)
    }

    @Test
    fun `Retrieves course credits`() {
        val expectedCredits = listOf(MaturityCourseCredit("id", "user", 2020, "url"))
        every { service.getCredits() } returns expectedCredits

        val actualCredits = controller.getCredits()

        assertEquals(expectedCredits, actualCredits)
    }

    @Test
    fun `Saves credit`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { service.save(any()) } just Runs

        controller.save(expectedCredit)

        verify { service.save(any()) }
    }

    @Test
    fun `Updates credit`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { service.update(expectedCredit) } just Runs

        controller.update(expectedCredit)

        verify { service.update(expectedCredit) }
    }

    @Test
    fun `Deletes credit`() {
        every { service.delete("id") } just Runs

        controller.delete("id")

        verify { service.delete("id") }
    }

}
