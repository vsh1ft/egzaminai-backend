package lt.codedicted.egzaminai.backend.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailSenderService(private val emailSender: JavaMailSender) {

    fun sendPasswordReset(resetToken: String) {
        emailSender.send(
            SimpleMailMessage().apply {
                setTo("sitytsak@gmail.com")
                subject = "testing email"
                text = "Please reset your password via: http://localhost:4200/change-password?token=${resetToken}"
            }
        )
    }
}
