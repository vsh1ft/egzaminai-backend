package lt.codedicted.egzaminai.backend.controller

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController
constructor(
    private val userService: UserService
) {

    @PostMapping("/create")
    fun createsUser(@RequestBody user: User) {
        userService.create(user)
    }

    @GetMapping("/exist/{email}")
    fun doesUserExist(@PathVariable email: String) = userService.exists(email)

}
