package lt.codedicted.egzaminai.backend.unit.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepository
import lt.codedicted.egzaminai.backend.model.SecurityUserDetails
import lt.codedicted.egzaminai.backend.service.SecurityUserDetailsService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class SecurityUserDetailsServiceTest {

    @MockK
    private lateinit var repository: UserRepository

    @Test
    fun `loads user by username`() {
        val user = User("email", "pswd")
        every { repository.findByEmail(user.email) } returns user

        assertEquals(SecurityUserDetails(user).username, SecurityUserDetailsService(repository).loadUserByUsername(user.email).username)
        assertEquals(SecurityUserDetails(user).password, SecurityUserDetailsService(repository).loadUserByUsername(user.email).password)
    }

}
