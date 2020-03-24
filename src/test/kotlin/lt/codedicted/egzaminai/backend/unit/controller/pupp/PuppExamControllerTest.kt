package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppExamController
import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PuppExamControllerTest {

    @MockK
    private lateinit var repository: PuppExamRepository

    private lateinit var controller: PuppExamController

    @BeforeEach
    fun setUp() {
        controller = PuppExamController(repository)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams = listOf(PuppExam(PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url"))
        every { repository.findAll() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

}
