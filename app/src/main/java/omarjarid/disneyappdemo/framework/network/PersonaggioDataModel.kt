package omarjarid.disneyappdemo.framework.network

import omarjarid.domain.model.Personaggio

data class PersonaggioDataModel(
    val count: Int? = 0,
    val `data`: List<Data?>? = listOf(),
    val nextPage: String? = "",
    val totalPages: Int? = 0
) {
    data class Data(
        val __v: Int? = 0,
        val _id : Int? = 0,
        val allies: List<String>? = listOf(),
        val createdAt: String? = "",
        val enemies: List<String>? = listOf(),
        val films: List<String>? = listOf(),
        val imageUrl: String? = "",
        val name: String? = "",
        val parkAttractions: List<String>? = listOf(),
        val shortFilms: List<String>? = listOf(),
        val sourceUrl: String? = "",
        val tvShows: List<String>? = listOf(),
        val updatedAt: String? = "",
        val url: String? = "",
        val videoGames: List<String>? = listOf(),
    )
}

fun PersonaggioDataModel.toDomainModel(): List<Personaggio> {
    val personaggi = mutableListOf<Personaggio>()
    this.data?.forEach { personaggio ->
        val character = Personaggio(
            _id = personaggio?._id ?: 0,
            url = personaggio?.url.orEmpty(),
            name = personaggio?.name.orEmpty(),
            sourceUrl = personaggio?.sourceUrl.orEmpty(),
            films = personaggio?.films.orEmpty(),
            shortFilms = personaggio?.shortFilms.orEmpty(),
            tvShows = personaggio?.tvShows.orEmpty(),
            videoGames = personaggio?.videoGames.orEmpty(),
            parkAttractions = personaggio?.parkAttractions.orEmpty(),
            allies = personaggio?.allies.orEmpty(),
            enemies = personaggio?.enemies.orEmpty(),
            imageUrl = personaggio?.imageUrl.orEmpty(),
            totalPages = this.totalPages ?: 0
        )

        personaggi.add(character)
    }

    return personaggi
}