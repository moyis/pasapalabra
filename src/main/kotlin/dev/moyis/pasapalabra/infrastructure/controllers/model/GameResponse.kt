package dev.moyis.pasapalabra.infrastructure.controllers.model

import dev.moyis.pasapalabra.domain.model.Game

data class GameResponse(
    val game: List<GameEntryResponse>,
) {
    companion object {
        fun fromDomain(game: Game) = GameResponse(game.words.map { GameEntryResponse.fromDomain(it) })
    }
}
