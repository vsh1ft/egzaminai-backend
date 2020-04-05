package lt.codedicted.egzaminai.backend.service

import lt.codedicted.egzaminai.backend.exception.InvalidTypeException
import org.springframework.stereotype.Component
import javax.validation.Validator

@Component
class ValidatorToExceptionConverter(private val validator: Validator) {

    fun <T> validate(entity: T) {
        if (validator.validate(entity).isNotEmpty()) {
            throw InvalidTypeException(validator.validate(entity).iterator().next().message)
        }
    }
}
