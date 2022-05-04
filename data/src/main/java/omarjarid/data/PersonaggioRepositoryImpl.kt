package omarjarid.data

import omarjarid.domain.model.Personaggio
import omarjarid.domain.repository.PersonaggioRepository
import javax.inject.Inject

/*
    Implementazione concreta della classe PersonaggioRepository con costruttore iniettabile.
    La dipendenza da passare sarà il NetworkDatasource, di cui verranno chiamati i metodi.
*/
class PersonaggioRepositoryImpl @Inject constructor(
    private val networkDatasource: NetworkDatasource
) : PersonaggioRepository {
    /*** METODI ***/
    override suspend fun getAllCharacters(): List<Personaggio> {
        return networkDatasource.getAllCharacters()
    }

    override suspend fun getAllCharacters(page: Int): List<Personaggio> {
        return networkDatasource.getAllCharacters(page)
    }

    override suspend fun getOneCharacter(id: Int): Personaggio {
        return networkDatasource.getOneCharacter(id)
    }
}