package lt.codedicted.egzaminai.backend.controller

import lt.codedicted.egzaminai.backend.exception.UserNotFoundException
import lt.codedicted.egzaminai.backend.model.ChangePasswordForm
import lt.codedicted.egzaminai.backend.model.PasswordChangeToken
import lt.codedicted.egzaminai.backend.repository.PasswordChangeRepository
import lt.codedicted.egzaminai.backend.repository.TokenRepository
import lt.codedicted.egzaminai.backend.service.EmailSenderService
import lt.codedicted.egzaminai.backend.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class LoginController(
    private val repo: TokenRepository,
    private val emailService: EmailSenderService,
    private val userService: UserService,
    private val changeRepository: PasswordChangeRepository,
    private val encoder: PasswordEncoder
) {

    @PostMapping("/user/login")
    fun login() {
    }

    @PostMapping("/user/logout")
    fun logout(@RequestHeader("Authorization") token: String) {
        repo.deleteById(token)
    }

    @PostMapping("/user/reset-password")
    fun resetPassword(@RequestBody email: String) {
        if (!userService.exists(email))
            throw UserNotFoundException()
        val token = UUID.randomUUID().toString()
        changeRepository.save(PasswordChangeToken(token, email))
        emailService.sendPasswordReset(token)
    }

    @PostMapping("/user/change-password")
    fun changePassword(@RequestBody passwordForm: ChangePasswordForm) {
        val email = changeRepository.findById(passwordForm.token).get().email
        val user = userService.findByEmail(email)
        userService.save(user.copy(password = encoder.encode(passwordForm.password)))
        changeRepository.deleteById(passwordForm.token)
    }

}
