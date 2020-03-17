package lt.codedicted.egzaminai.backend.service

import lt.codedicted.egzaminai.backend.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUserDetails(val user: User): UserDetails {

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getAuthorities(): MutableCollection<GrantedAuthority>
        = mutableListOf(SimpleGrantedAuthority("ADMIN"))

    override fun isEnabled(): Boolean = true
}
