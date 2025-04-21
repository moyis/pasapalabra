package dev.moyis.pasapalabra.infrastructure.controllers.model

import com.opencsv.bean.CsvBindByPosition
import dev.moyis.pasapalabra.domain.model.GameEntry

data class GameCsvRow(
    @CsvBindByPosition(position = 0)
    val letter: Char,
    @CsvBindByPosition(position = 1)
    val type: String,
    @CsvBindByPosition(position = 2)
    val definition: String,
    @CsvBindByPosition(position = 3)
    val word: String,
) {
    companion object {
        fun fromDomain(entry: GameEntry): GameCsvRow =
            GameCsvRow(
                letter = entry.letter,
                type = entry.type.name,
                definition = entry.definition,
                word = entry.word,
            )
    }
}
