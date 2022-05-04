package omarjarid.domain.repository

import omarjarid.domain.model.Personaggio

interface PersonaggioRepository {
    suspend fun getAllCharacters(): List<Personaggio>
    suspend fun getAllCharacters(page: Int): List<Personaggio>
    suspend fun getOneCharacter(id: Int): Personaggio
}