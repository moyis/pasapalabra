package dev.moyis.pasapalabra.domain.api

import dev.moyis.pasapalabra.domain.model.Difficulty
import dev.moyis.pasapalabra.domain.model.Game
import dev.moyis.pasapalabra.domain.spi.GameEntryProvider
import org.springframework.stereotype.Service
import java.text.Collator
import java.util.Locale

@Service
class GameService(
    private val gameEntryProvider: GameEntryProvider,
) {
    private val collator = Collator.getInstance(Locale.of("es"))

    fun getGame(difficulty: Difficulty) =
        Game(
            words =
                gameEntryProvider
                    .getWords(difficulty)
                    .sortedWith { a, b -> collator.compare("${a.letter}", "${b.letter}") },
        )
}
