package lt.codedicted.egzaminai.backend.service

import lt.codedicted.egzaminai.backend.model.SecurityUserDetails
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService
constructor(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByEmail(username)
        return SecurityUserDetails(user)
    }
}
