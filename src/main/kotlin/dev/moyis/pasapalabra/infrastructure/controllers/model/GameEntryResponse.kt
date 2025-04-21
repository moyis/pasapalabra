package dev.moyis.pasapalabra.infrastructure.controllers.model

import dev.moyis.pasapalabra.domain.model.GameEntry

data class GameEntryResponse(
    val letter: Char,
    val word: String,
    val definition: String,
    val type: String,
) {
    companion object {
        fun fromDomain(entry: GameEntry): GameEntryResponse =
            with(entry) {
                GameEntryResponse(
                    letter = letter,
                    word = word,
                    definition = definition,
                    type = type.name.replace("_", " "),
                )
            }
    }
}
