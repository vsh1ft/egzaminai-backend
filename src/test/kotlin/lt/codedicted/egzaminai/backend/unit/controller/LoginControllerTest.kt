package lt.codedicted.egzaminai.backend.unit.controller

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.LoginController
import lt.codedicted.egzaminai.backend.controller.UserController
import lt.codedicted.egzaminai.backend.repository.TokenRepository
import lt.codedicted.egzaminai.backend.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoginControllerTest {

    @MockK
    private lateinit var repositorySpy: TokenRepository


    private lateinit var controller: LoginController

    @BeforeEach
    fun setUp() {
        controller = LoginController(repositorySpy)
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

}
