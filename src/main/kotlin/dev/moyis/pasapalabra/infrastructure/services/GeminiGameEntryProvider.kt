package dev.moyis.pasapalabra.infrastructure.services

import dev.moyis.pasapalabra.domain.model.Difficulty
import dev.moyis.pasapalabra.domain.model.GameEntry
import dev.moyis.pasapalabra.domain.spi.GameEntryProvider
import dev.moyis.pasapalabra.infrastructure.model.ModelContext
import org.springframework.ai.chat.client.ChatClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service

@Service
class GeminiGameEntryProvider(
    private val chatClient: ChatClient,
    private val modelContext: ModelContext,
) : GameEntryProvider {
    private val type = object : ParameterizedTypeReference<List<GameEntry>>() {}

    override fun getWords(difficulty: Difficulty) =
        chatClient
            .prompt()
            .system(modelContext.value)
            .user("Genera un juego en dificultad ${difficulty.name}")
            .advisors()
            .call()
            .entity(type)
            ?: emptyList()
}
