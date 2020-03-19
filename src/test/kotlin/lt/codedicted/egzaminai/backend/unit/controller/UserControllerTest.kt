package lt.codedicted.egzaminai.backend.unit.controller

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.controller.UserController
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.service.UserService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class UserControllerTest {

    @MockK
    private lateinit var userService: UserService


    private lateinit var controller: UserController

    @BeforeEach
    fun setUp() {
        controller = UserController(userService)
    }

    @Test
    fun `Creates user`() {
        val user = User("user", "password")
        every { userService.create(user) } just Runs

        controller.createsUser(user)

        verify { userService.create(user) }
    }

    @Test
    fun `Checks user existence`() {
        val user = User("user", "password")
        every { userService.exists(user.email) } returns true

        assertTrue(controller.doesUserExist(user.email))
    }

}
