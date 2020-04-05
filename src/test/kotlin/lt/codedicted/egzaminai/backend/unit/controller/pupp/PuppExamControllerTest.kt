package lt.codedicted.egzaminai.backend.unit.controller.pupp

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.pupp.PuppExamController
import lt.codedicted.egzaminai.backend.model.maturity.MaturityProgram
import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import lt.codedicted.egzaminai.backend.model.types.Subject
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamRepository
import lt.codedicted.egzaminai.backend.service.ValidatorToExceptionConverter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PuppExamControllerTest {

    @MockK
    private lateinit var repository: PuppExamRepository

    @MockK
    private lateinit var validator: ValidatorToExceptionConverter

    private lateinit var controller: PuppExamController

    @BeforeEach
    fun setUp() {
        controller = PuppExamController(repository, validator)
    }

    @Test
    fun `Retrieves exams`() {
        val expectedExams = listOf(PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url"))
        every { repository.findAll() } returns expectedExams

        val actualExams = controller.getExams()

        assertEquals(expectedExams, actualExams)
    }

    @Test
    fun `Saves exam`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.save(expectedExam)

        verify { repository.save(any()) }
    }

    @Test
    fun `Validates on save`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.save(expectedExam)

        verify { validator.validate(expectedExam) }
    }

    @Test
    fun `Updates exam`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(expectedExam) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { repository.save(expectedExam) }
    }

    @Test
    fun `Validates on update`() {
        val expectedExam =
            PuppExam("id", PuppExamName.LITHUANIAN_LANGUAGE_WRITING, 2020, "url")
        every { repository.save(any()) } just Runs
        every { validator.validate(expectedExam) } just Runs

        controller.update(expectedExam)

        verify { validator.validate(expectedExam) }
    }

    @Test
    fun `Deletes exam`() {
        every { repository.deleteById("id") } just Runs

        controller.delete("id")

        verify { repository.deleteById("id") }
    }

}
