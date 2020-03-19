package lt.codedicted.egzaminai.backend.service

import lt.codedicted.egzaminai.backend.exception.UserAlreadyExistException
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {

    fun create(user: User){
        if (userRepository.existsByEmail(user.email))
            throw UserAlreadyExistException("User ${user.email} already exists")

        userRepository.save(user.copy(password = encoder.encode(user.password)))
    }

    fun exists(email: String) = userRepository.existsByEmail(email)

}
