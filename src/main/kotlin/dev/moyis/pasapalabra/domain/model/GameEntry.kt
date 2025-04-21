package dev.moyis.pasapalabra.domain.model

data class GameEntry(
    val letter: Char,
    val word: String,
    val definition: String,
) {
    val type = if (word.startsWith(letter)) EntryType.STARTS_WITH else EntryType.CONTAINS
}
