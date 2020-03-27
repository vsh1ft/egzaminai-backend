package lt.codedicted.egzaminai.backend.controller

import lt.codedicted.egzaminai.backend.repository.TokenRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val repo: TokenRepository
) {

    @PostMapping("/user/login")
    fun login() {
    }

    @PostMapping("/user/logout")
    fun logout(@RequestHeader("Authorization") token: String) {
        repo.deleteById(token)
    }

}
