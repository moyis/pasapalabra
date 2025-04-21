package dev.moyis.pasapalabra.infrastructure.configuration

import dev.moyis.pasapalabra.domain.model.Difficulty
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.Locale

@Configuration
class JacksonConfiguration : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(StringToEnumConverter)
    }
}

private object StringToEnumConverter : Converter<String, Difficulty> {
    override fun convert(source: String): Difficulty = Difficulty.valueOf(source.uppercase(Locale.getDefault()))
}
