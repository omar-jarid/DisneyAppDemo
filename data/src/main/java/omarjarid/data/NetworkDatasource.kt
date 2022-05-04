package omarjarid.data

import omarjarid.domain.model.Personaggio

interface NetworkDatasource {
    suspend fun getAllCharacters(): List<Personaggio>
    suspend fun getAllCharacters(page: Int): List<Personaggio>
    suspend fun getOneCharacter(id: Int): Personaggio
}