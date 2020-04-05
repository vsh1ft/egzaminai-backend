package lt.codedicted.egzaminai.backend.unit.aspect

import io.mockk.*
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.aspect.DatabaseUpdateAspect
import lt.codedicted.egzaminai.backend.exception.UserAlreadyExistException
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.DatabaseVersionRepository
import lt.codedicted.egzaminai.backend.repository.UserRepository
import lt.codedicted.egzaminai.backend.service.EmailSenderService
import lt.codedicted.egzaminai.backend.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockKExtension::class)
class DatabaseAspectTest {

    @MockK
    private lateinit var repository: DatabaseVersionRepository

    private lateinit var aspect: DatabaseUpdateAspect

    @BeforeEach
    fun setUp() {
        aspect = DatabaseUpdateAspect(repository)
    }

    @Test
    fun `Deletes previous database version id`() {
        every { repository.deleteAll() } just Runs
        every { repository.save(any()) } just Runs

        aspect.updateDatabaseVersion()

        verify { repository.deleteAll() }
    }

    @Test
    fun `Saves new database version`() {
        every { repository.deleteAll() } just Runs
        every { repository.save(any()) } just Runs

        aspect.updateDatabaseVersion()

        verify { repository.save(any()) }
    }

}