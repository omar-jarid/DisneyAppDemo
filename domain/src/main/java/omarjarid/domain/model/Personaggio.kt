package omarjarid.domain.model

// Data class che modella il dato da rappresentare.
data class Personaggio(
    val _id: Int = 0,
    val url: String = "",
    val name: String = "",
    val sourceUrl: String = "",
    val films: List<String> = emptyList(),
    val shortFilms: List<String> = emptyList(),
    val tvShows: List<String> = emptyList(),
    val videoGames: List<String> = emptyList(),
    val alignment: String = "",
    val parkAttractions: List<String> = emptyList(),
    val allies: List<String> = emptyList(),
    val enemies: List<String> = emptyList(),
    val imageUrl: String = "",
    val totalPages: Int = 0
)
