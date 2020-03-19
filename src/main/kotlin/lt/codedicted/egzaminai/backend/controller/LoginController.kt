package lt.codedicted.egzaminai.backend.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @PostMapping("/user/login")
    fun login() {
    }

}
