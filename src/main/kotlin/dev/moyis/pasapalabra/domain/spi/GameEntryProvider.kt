package dev.moyis.pasapalabra.domain.spi

import dev.moyis.pasapalabra.domain.model.Difficulty
import dev.moyis.pasapalabra.domain.model.GameEntry

interface GameEntryProvider {
    fun getWords(difficulty: Difficulty): List<GameEntry>
}
