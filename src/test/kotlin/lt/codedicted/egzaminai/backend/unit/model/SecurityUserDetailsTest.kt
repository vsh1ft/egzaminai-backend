package lt.codedicted.egzaminai.backend.unit.model

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.model.SecurityUserDetails
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.security.core.authority.SimpleGrantedAuthority

class SecurityUserDetailsTest {

    @Test
    fun `Sets username`() {
        val user = User("email", "pswd")

        assertEquals(user.password, SecurityUserDetails(user).password)
        assertEquals(user.email, SecurityUserDetails(user).username)
    }

    @Test
    fun `Confirms that account is not expired`() {
        val user = User("email", "pswd")

        assertTrue(SecurityUserDetails(user).isAccountNonExpired)
    }

    @Test
    fun `Confirms that account is not locked`() {
        val user = User("email", "pswd")

        assertTrue(SecurityUserDetails(user).isAccountNonLocked)
    }

    @Test
    fun `Confirms that credentials are not expired`() {
        val user = User("email", "pswd")

        assertTrue(SecurityUserDetails(user).isCredentialsNonExpired)
    }

    @Test
    fun `Confirms that account is enabled`() {
        val user = User("email", "pswd")

        assertTrue(SecurityUserDetails(user).isEnabled)
    }

    @Test
    fun `Confirms that account is admin`() {
        val user = User("email", "pswd")

        assertEquals(mutableListOf(SimpleGrantedAuthority("ADMIN")), SecurityUserDetails(
            user
        ).authorities)
    }

}
