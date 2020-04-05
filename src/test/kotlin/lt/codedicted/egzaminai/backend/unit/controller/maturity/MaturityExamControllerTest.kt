package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityExamController
import lt.codedicted.egzaminai.core.model.maturity.MaturityExam
import lt.codedicted.egzaminai.core.model.types.ExamName
import lt.codedicted.egzaminai.core.model.types.ExamType
import lt.codedicted.egzaminai.core.service.maturity.MaturityExamService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MaturityExamControllerTest {

    @MockK
    private lateinit var service: MaturityExamService

    private lateinit var controller: MaturityExamController

    @BeforeEach
    fun setUp() {
        controller = MaturityExamController(service)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams =
            listOf(MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url"))
        every { service.getExams() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

    @Test
    fun `Saves exam`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { service.save(any()) } just Runs

        controller.save(expectedExam)

        verify { service.save(any()) }
    }

    @Test
    fun `Updates exam`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { service.save(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { service.save(expectedExam) }
    }

    @Test
    fun `Deletes exam`() {
        every { service.delete("id") } just Runs

        controller.delete("id")

        verify { service.delete("id") }
    }


}
