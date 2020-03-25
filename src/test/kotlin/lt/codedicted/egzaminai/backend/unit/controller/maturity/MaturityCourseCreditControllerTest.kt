package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityCourseCreditController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityCourseCredit
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExamDate
import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityCourseCreditRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

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
        val expectedCredits = listOf(MaturityCourseCredit("id", "user", 2020, "url"))
        every { repository.findAll() } returns expectedCredits

        val actualCredits = controller.getCredits()

        assertEquals(expectedCredits, actualCredits)
    }

    @Test
    fun `Saves credit`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { repository.save(any()) } just Runs

        controller.save(expectedCredit)

        verify { repository.save(any()) }
    }

    @Test
    fun `Updates credit`() {
        val expectedCredit =
            MaturityCourseCredit("id", "user", 2020, "url")
        every { repository.save(expectedCredit) } just Runs

        controller.update(expectedCredit)

        verify { repository.save(expectedCredit) }
    }

    @Test
    fun `Deletes credit`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
