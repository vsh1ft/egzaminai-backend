package lt.codedicted.egzaminai.backend.unit.controller.maturity

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.maturity.MaturityExamController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityExam
import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType
import lt.codedicted.egzaminai.backend.repository.maturity.MaturityExamRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.math.exp

@ExtendWith(MockKExtension::class)
class MaturityExamControllerTest {

    @MockK
    private lateinit var repository: MaturityExamRepository

    private lateinit var controller: MaturityExamController

    @BeforeEach
    fun setUp() {
        controller = MaturityExamController(repository)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams =
            listOf(MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url"))
        every { repository.findAll() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

    @Test
    fun `Saves exam`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { repository.save(any()) } just Runs

        controller.save(expectedExam)

        verify { repository.save(any()) }
    }

    @Test
    fun `Updates exam`() {
        val expectedExam =
            MaturityExam("", ExamName.LITHUANIAN_LANGUAGE, 2020, ExamType.NATIONAL_LEVEL, "url", "url")
        every { repository.save(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { repository.save(expectedExam) }
    }

    @Test
    fun `Deletes exam`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }


}
