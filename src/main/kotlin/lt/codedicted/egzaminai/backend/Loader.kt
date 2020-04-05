package lt.codedicted.egzaminai.backend

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepository
import lt.codedicted.egzaminai.core.model.maturity.*
import lt.codedicted.egzaminai.core.model.pupp.PuppExam
import lt.codedicted.egzaminai.core.model.pupp.PuppExamDate
import lt.codedicted.egzaminai.core.model.pupp.PuppProgram
import lt.codedicted.egzaminai.core.model.types.*
import lt.codedicted.egzaminai.core.service.maturity.*
import lt.codedicted.egzaminai.core.service.pupp.PuppExamDateService
import lt.codedicted.egzaminai.core.service.pupp.PuppExamService
import lt.codedicted.egzaminai.core.service.pupp.PuppProgramService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class Loader
constructor(
    private val userRepository: UserRepository,
    private val maturityExamRepository: MaturityExamService,
    private val maturityProgramRepository: MaturityProgramService,
    private val maturityExamDateRepository: MaturityExamDateService,
    private val maturityCourseCreditService: MaturityCourseCreditService,
    private val puppExamRepository: PuppExamService,
    private val puppExamDateRepository: PuppExamDateService,
    private val puppProgramRepository: PuppProgramService
): CommandLineRunner {

    override fun run(vararg args: String) {
        userRepository.deleteAll()
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
                ExamType.NATIONAL_LEVEL,
                "http://www.something.com/8027_2019_LKL_VBE_PG.pdf",
                "http://www.something.com/8027_2019_LKL_VBE_PG.pdf"
            )
        )
        maturityExamRepository.save(
            MaturityExam(
                "1234",
                ExamName.LITHUANIAN_LANGUAGE,
                2016,
                ExamType.NATIONAL_LEVEL,
                "http://www.something.com/8027_2019_LKL_VBE_PG.pdf",
                "http://www.something.com/8027_2019_LKL_VBE_PG.pdf"
            )
        )
    }

    private fun createMaturityPrograms() {
        maturityProgramRepository.save(
            MaturityProgram(
                "1234",
                "Lietuviu kalbos programa",
                Subject.LITHUANIAN_LANGUAGE,
                "http://www.something.com/8027_2019_LKL_VBE_PG.pdf"
            )
        )
    }

    private fun createMaturityExamDates() {
        maturityExamDateRepository.save(
            MaturityExamDate("1", ExamName.LITHUANIAN_LANGUAGE, ExamType.NATIONAL_LEVEL,"#fff500", LocalDateTime.parse("2020-05-04T16:52"))
        )
    }

    private fun createMaturityCourseCredits() {
        maturityCourseCreditService.save(
            MaturityCourseCredit("1", "Lietuviu kalbos iskaita", 2015, "http://www.something.com/8027_2019_LKL_VBE_PG.pdf")
        )
    }

    private fun createPuppExams() {
        puppExamRepository.save(
            PuppExam("1", PuppExamName.FOREIGN_LANGUAGE_VERBAL, 2016,  "http://www.something.com/8027_2019_LKL_VBE_PG.pdf")
        )
    }

    private fun createPuppExamDates() {
        puppExamDateRepository.save(
            PuppExamDate("i", PuppExamName.FOREIGN_LANGUAGE_VERBAL, "#fff500", LocalDateTime.parse("2020-05-04T16:52"))
        )
    }

    private fun createPuppPrograms() {
        puppProgramRepository.save(
            PuppProgram("id", "Lietuviu kalbos programa", "http://www.something.com/8027_2019_LKL_VBE_PG.pdf")
        )
    }

}
