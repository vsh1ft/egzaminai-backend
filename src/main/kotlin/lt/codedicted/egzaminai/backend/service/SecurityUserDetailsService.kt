package lt.codedicted.egzaminai.backend.service

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService
constructor(private val userRepo: UserRepo): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepo.findByUsername(username) ?: throw(UsernameNotFoundException("Username not found"))
        return SecurityUserDetails(user)
    }
}
