package lt.codedicted.egzaminai.backend.aspect

import lt.codedicted.egzaminai.core.model.DatabaseVersion
import lt.codedicted.egzaminai.core.repository.DatabaseVersionRepository
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Order(0)
@Component
class DatabaseUpdateAspect(
    private val repository: DatabaseVersionRepository
) {

    @Before("@annotation(DatabaseUpdated)")
    fun updateDatabaseVersion() {
            repository.deleteAll()
            repository.save(DatabaseVersion(UUID.randomUUID().toString()))
    }

}
