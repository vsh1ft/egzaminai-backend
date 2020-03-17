package lt.codedicted.egzaminai.backend

import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.repository.UserRepo
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Loader
constructor(private val userRepo: UserRepo) : CommandLineRunner {
    override fun run(vararg args: String) {
        userRepo.deleteAll()
        userRepo.save(User(username = "admin", password = "admin"))
        userRepo.save(User("test", "123"))
    }
}
