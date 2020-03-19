package lt.codedicted.egzaminai.backend.unit.service

import io.mockk.*
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.exception.UserAlreadyExistException
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepository
import lt.codedicted.egzaminai.backend.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockKExtension::class)
class UserServiceTest {

    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var encoder: PasswordEncoder

    private lateinit var service: UserService

    @BeforeEach
    fun setUp() {
        service = UserService(userRepository, encoder)
    }

    @Test
    fun `Creates user`() {
        val user = User("user", "password")
        every { userRepository.save(any()) } just Runs
        every { userRepository.existsByEmail(user.email) } returns false
        every { encoder.encode(user.password) } returns "encodedPassword"

        service.create(user)

        userRepository.save(User(user.email, "encodedPassword"))
    }

    @Test
    fun `Throws exception if user already exists`() {
        val user = User("user", "password")
        every { userRepository.existsByEmail(user.email) } returns true

        Assertions.assertThrows(UserAlreadyExistException::class.java) {
            service.create(user)
        }
    }

    @Test
    fun `Checks user existence`() {
        val user = User("user", "password")
        every { userRepository.existsByEmail(user.email) } returns true

        assertTrue(service.exists(user.email))
    }

}
