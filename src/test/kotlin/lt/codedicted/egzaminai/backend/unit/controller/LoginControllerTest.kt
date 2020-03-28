package lt.codedicted.egzaminai.backend.unit.controller

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.LoginController
import lt.codedicted.egzaminai.backend.exception.UserNotFoundException
import lt.codedicted.egzaminai.backend.model.ChangePasswordForm
import lt.codedicted.egzaminai.backend.model.PasswordChangeToken
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.PasswordChangeRepository
import lt.codedicted.egzaminai.backend.repository.TokenRepository
import lt.codedicted.egzaminai.backend.service.EmailSenderService
import lt.codedicted.egzaminai.backend.service.UserService
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class LoginControllerTest {

    @MockK
    private lateinit var repositorySpy: TokenRepository

    @MockK
    private lateinit var emailServiceSpy: EmailSenderService

    @MockK
    private lateinit var userServiceSpy: UserService

    @MockK
    private lateinit var passwordChangeRepoSpy: PasswordChangeRepository

    @MockK
    private lateinit var passwordEncoderStub: PasswordEncoder

    private lateinit var controller: LoginController

    @BeforeEach
    fun setUp() {
        controller =
            LoginController(repositorySpy, emailServiceSpy, userServiceSpy, passwordChangeRepoSpy, passwordEncoderStub)
    }


    @Test
    fun `Creates login controller`() {
        controller.login()
    }

    @Test
    fun `Deletes jwt token`() {
        every { repositorySpy.deleteById("token") } just Runs

        controller.logout("token")

        verify { repositorySpy.deleteById("token") }
    }

    @Test
    fun `Saves password reset token`() {
        every { passwordChangeRepoSpy.save(any()) } just Runs
        every { emailServiceSpy.sendPasswordReset(any()) } just Runs
        every { userServiceSpy.exists("email") } returns true

        controller.resetPassword("email")

        verify { passwordChangeRepoSpy.save(any()) }
    }

    @Test
    fun `Sends confirmation email`() {
        every { passwordChangeRepoSpy.save(any()) } just Runs
        every { userServiceSpy.exists("email") } returns true
        every { emailServiceSpy.sendPasswordReset(any()) } just Runs

        controller.resetPassword("email")

        verify { emailServiceSpy.sendPasswordReset(any()) }
    }

    @Test
    fun `Throws exception if user is not found`() {
        every { userServiceSpy.exists("email") } returns false

        assertThrows(UserNotFoundException::class.java) {
            controller.resetPassword("email")
        }
    }

    @Test
    fun `Saves user with changed password`() {
        every { passwordChangeRepoSpy.findById(any()) } returns Optional.of(PasswordChangeToken("id", "email"))
        every { passwordChangeRepoSpy.deleteById(any()) } just Runs
        every { userServiceSpy.findByEmail("email") } returns User("email", "pswd")
        every { userServiceSpy.save(any()) } just Runs
        every { passwordEncoderStub.encode(any()) } returns "encoded"

        controller.changePassword(ChangePasswordForm("id", "newPassword"))

        every { userServiceSpy.save(any()) } just Runs
    }

    @Test
    fun `Deletes token from password repo`() {
        every { passwordChangeRepoSpy.findById(any()) } returns Optional.of(PasswordChangeToken("id", "email"))
        every { passwordChangeRepoSpy.deleteById(any()) } just Runs
        every { userServiceSpy.findByEmail("email") } returns User("email", "pswd")
        every { userServiceSpy.save(any()) } just Runs
        every { passwordEncoderStub.encode(any()) } returns "encoded"

        controller.changePassword(ChangePasswordForm("id", "newPassword"))

        every { passwordChangeRepoSpy.deleteById(any()) } just Runs
    }

}
