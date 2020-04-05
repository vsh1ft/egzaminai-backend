package lt.codedicted.egzaminai.backend.unit.controller

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DatabaseVersionControllerTest {

    @MockK
    private lateinit var repositorySpy: DatabaseVersionRepository

    private lateinit var controller: DatabaseVersionController

    @BeforeEach
    fun setUp() {
        controller = DatabaseVersionController(repositorySpy)
    }


    @Test
    fun `Returns database version`() {
        val expectedVersion = DatabaseVersion("id")
        every { repositorySpy.findAll() } returns listOf(expectedVersion)

        val actualVersion = controller.getDatabaseVersion()

        assertEquals(expectedVersion.id, actualVersion)
    }

}
