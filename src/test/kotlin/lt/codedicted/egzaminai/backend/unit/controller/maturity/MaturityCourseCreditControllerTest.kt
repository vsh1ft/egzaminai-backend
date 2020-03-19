package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityCourseCreditController
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityCourseCreditRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityCourseCreditControllerTest {

    @MockK
    private lateinit var repository: MaturityCourseCreditRepository

    private lateinit var controller: MaturityCourseCreditController

    @BeforeEach
    fun setUp() {
        controller = MaturityCourseCreditController(repository)
    }

    @Test
    fun `Retrieves course credits`() {
        val expectedCredits = listOf(MaturityCourseCredit("user", 2020, "url"))
        every { repository.findAll() } returns expectedCredits

        val actualCredits = controller.getCredits()

        assertEquals(expectedCredits, actualCredits)
    }

}
