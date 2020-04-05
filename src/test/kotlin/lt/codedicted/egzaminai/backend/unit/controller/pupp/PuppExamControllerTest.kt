package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppExamController
import lt.codedicted.egzaminai.core.model.pupp.PuppExam
import lt.codedicted.egzaminai.core.model.types.PuppExamName
import lt.codedicted.egzaminai.core.service.pupp.PuppExamService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PuppExamControllerTest {

    @MockK
    private lateinit var service: PuppExamService

    private lateinit var controller: PuppExamController

    @BeforeEach
    fun setUp() {
        controller = PuppExamController(service)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams = listOf(PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url"))
        every { service.getExams() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

    @Test
    fun `Saves exam`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { service.save(any()) } just Runs

        controller.save(expectedExam)

        verify { service.save(any()) }
    }

    @Test
    fun `Updates exam`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
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
