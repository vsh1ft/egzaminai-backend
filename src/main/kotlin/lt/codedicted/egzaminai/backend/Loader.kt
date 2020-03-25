package lt.codedicted.egzaminai.backend

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.model.maturity.*
import lt.codedicted.egzaminai.backend.model.pupp.PuppExam
import lt.codedicted.egzaminai.backend.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.backend.model.pupp.PuppProgram
import lt.codedicted.egzaminai.backend.model.types.ExamName
import lt.codedicted.egzaminai.backend.model.types.ExamType.*
import lt.codedicted.egzaminai.backend.model.types.PuppExamName
import lt.codedicted.egzaminai.backend.model.types.Subject
import lt.codedicted.egzaminai.backend.repository.UserRepository
import lt.codedicted.egzaminai.backend.repository.maturity.*
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamDateRepository
import lt.codedicted.egzaminai.backend.repository.pupp.PuppExamRepository
import lt.codedicted.egzaminai.backend.repository.pupp.PuppProgramRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class Loader
constructor(
    private val userRepository: UserRepository,
    private val maturityExamRepository: MaturityExamRepository,
    private val maturityProgramRepository: MaturityProgramRepository,
    private val maturityExamDateRepository: MaturityExamDateRepository,
    private val maturityCourseCreditRepository: MaturityCourseCreditRepository,
    private val puppExamRepository: PuppExamRepository,
    private val puppExamDateRepository: PuppExamDateRepository,
    private val puppProgramRepository: PuppProgramRepository
): CommandLineRunner {

    override fun run(vararg args: String) {
        userRepository.deleteAll()
        maturityExamRepository.deleteAll()
        maturityProgramRepository.deleteAll()
        maturityExamDateRepository.deleteAll()
        maturityCourseCreditRepository.deleteAll()
        puppExamRepository.deleteAll()
        puppExamDateRepository.deleteAll()
        puppProgramRepository.deleteAll()
        userRepository.save(User("a@aa", "\$2a\$10\$NiRRH6KGGtGXcBIyazr9K.rGSzPI6zChZ12haproqnnw1JQuT3p3K"))

        createMaturityExams()
        createMaturityPrograms()
        createMaturityExamDates()
        createMaturityCourseCredits()
        createPuppExams()
        createPuppExamDates()
        createPuppPrograms()
    }

    private fun createMaturityExams() {
        maturityExamRepository.save(
            MaturityExam(
                "123",
                ExamName.ENGLISH_LANGUAGE,
                2017,
                NATIONAL_LEVEL,
                "someUrl",
                "answUrl"
            )
        )
        maturityExamRepository.save(
            MaturityExam(
                "1234",
                ExamName.LITHUANIAN_LANGUAGE,
                2016,
                NATIONAL_LEVEL,
                "someUrl",
                "answUrl"
            )
        )
    }

    private fun createMaturityPrograms() {
        maturityProgramRepository.save(
            MaturityProgram(
                "1234",
                "Lietuviu kalbos programa",
                Subject.LITHUANIAN_LANGUAGE,
                "someUrl"
            )
        )
    }

    private fun createMaturityExamDates() {
        maturityExamDateRepository.save(
            MaturityExamDate("1", ExamName.LITHUANIAN_LANGUAGE, NATIONAL_LEVEL, LocalDateTime.now())
        )
    }

    private fun createMaturityCourseCredits() {
        maturityCourseCreditRepository.save(
            MaturityCourseCredit("1", "Lietuviu kalbos iskaita", 2016, "someUrl")
        )
    }

    private fun createPuppExams() {
        puppExamRepository.save(
            PuppExam(PuppExamName.FOREIGN_LANGUAGE_VERBAL, 2016, "someUrl")
        )
    }

    private fun createPuppExamDates() {
        puppExamDateRepository.save(
            PuppExamDate(PuppExamName.FOREIGN_LANGUAGE_VERBAL, LocalDateTime.now())
        )
    }

    private fun createPuppPrograms() {
        puppProgramRepository.save(
            PuppProgram("Lietuviu kalbos programa", "someUrl")
        )
    }

}
