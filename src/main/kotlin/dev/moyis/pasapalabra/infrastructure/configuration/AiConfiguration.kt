package dev.moyis.pasapalabra.infrastructure.configuration

import dev.moyis.pasapalabra.infrastructure.model.ModelContext
import org.springframework.ai.chat.client.ChatClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class AiConfiguration(
    @Value("classpath:model-context.md")
    private val resource: Resource,
) {
    @Bean
    fun chatClient(builder: ChatClient.Builder): ChatClient = builder.build()

    @Bean
    fun modelContext() = ModelContext(value = resource.file.useLines { it.joinToString("\n") })
}
