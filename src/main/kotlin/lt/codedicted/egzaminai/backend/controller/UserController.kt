package lt.codedicted.egzaminai.backend.controller

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
class UserController
constructor(
    private val userRepo: UserRepo,
    private val encoder: PasswordEncoder
) {

    @GetMapping("/user")
    fun getUser(principal: Principal?): String {
        return principal?.name ?: "Please log in"
    }

    @PostMapping("/user/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        if (userRepo.findByUsername(user.username) != null) {
            return ResponseEntity("User already exists", HttpStatus.CONFLICT)
        }
        userRepo.insert(User(user.username, encoder.encode(user.password)))
        return ResponseEntity.ok("created")
    }
}
