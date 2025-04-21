package dev.moyis.pasapalabra

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@ActiveProfiles("integration")
class AbstractIT {
    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setUpRestAssured() {
        RestAssured.port = port
    }
}
