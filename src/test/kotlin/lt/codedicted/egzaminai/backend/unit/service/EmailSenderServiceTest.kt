package lt.codedicted.egzaminai.backend.unit.service

import io.mockk.*
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.exception.UserAlreadyExistException
import lt.codedicted.egzaminai.backend.model.User
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
class EmailSenderServiceTest {

    @MockK
    private lateinit var senderSpy: JavaMailSender

    private lateinit var service: EmailSenderService

    @BeforeEach
    fun setUp() {
        service = EmailSenderService(senderSpy)
    }

    @Test
    fun `Sends an email`() {
        every { senderSpy.send(any<SimpleMailMessage>()) } just Runs

        service.sendPasswordReset("token")

        verify { senderSpy.send(any<SimpleMailMessage>()) }
    }

}
