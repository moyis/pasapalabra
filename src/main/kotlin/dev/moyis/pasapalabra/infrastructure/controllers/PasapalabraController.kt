package dev.moyis.pasapalabra.infrastructure.controllers

import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsvBuilder
import dev.moyis.pasapalabra.domain.api.GameService
import dev.moyis.pasapalabra.domain.model.Difficulty
import dev.moyis.pasapalabra.infrastructure.controllers.model.GameCsvRow
import dev.moyis.pasapalabra.infrastructure.controllers.model.GameResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pasapalabra")
class PasapalabraController(
    private val gameService: GameService,
) {
    @GetMapping
    fun game(
        @RequestParam difficulty: Difficulty,
    ): ResponseEntity<GameResponse> {
        val game = gameService.getGame(difficulty)
        return ResponseEntity.ok(GameResponse.fromDomain(game))
    }

    @GetMapping("/csv")
    fun gameCsv(
        @RequestParam difficulty: Difficulty,
        response: HttpServletResponse,
    ) {
        val gameCsvRow =
            gameService
                .getGame(difficulty)
                .words
                .map { GameCsvRow.fromDomain(it) }

        with(response) {
            contentType = "text/csv"
            setHeader(HttpHeaders.CONTENT_DISPOSITION, """attachment; filename="data.csv"""")
            val csvWriter =
                StatefulBeanToCsvBuilder<GameCsvRow>(writer)
                    .withQuotechar('\'')
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build()

            csvWriter.write(gameCsvRow)
        }
    }
}
