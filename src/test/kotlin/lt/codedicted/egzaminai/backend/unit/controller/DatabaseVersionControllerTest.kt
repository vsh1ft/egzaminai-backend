package lt.codedicted.egzaminai.backend.unit.controller

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.DatabaseVersionController
import lt.codedicted.egzaminai.backend.controller.LoginController
import lt.codedicted.egzaminai.backend.exception.UserNotFoundException
import lt.codedicted.egzaminai.backend.model.*
import lt.codedicted.egzaminai.backend.repository.DatabaseVersionRepository
import lt.codedicted.egzaminai.backend.repository.PasswordChangeRepository
import lt.codedicted.egzaminai.backend.repository.TokenRepository
import lt.codedicted.egzaminai.backend.service.EmailSenderService
import lt.codedicted.egzaminai.backend.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class DatabaseVersionControllerTest {

    @MockK
    private lateinit var repositorySpy: DatabaseVersionRepository

    private lateinit var controller: DatabaseVersionController

    @BeforeEach
    fun setUp() {
        controller = DatabaseVersionController(repositorySpy)
    }


    @Test
    fun `Returns database version`() {
        val expectedVersion = DatabaseVersion("id")
        every { repositorySpy.findAll() } returns listOf(expectedVersion)

        val actualVersion = controller.getDatabaseVersion()

        assertEquals(expectedVersion.id, actualVersion)
    }

}
