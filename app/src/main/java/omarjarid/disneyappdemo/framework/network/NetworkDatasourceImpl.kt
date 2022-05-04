package omarjarid.disneyappdemo.framework.network

import omarjarid.data.NetworkDatasource
import omarjarid.domain.model.Personaggio
import javax.inject.Inject

class NetworkDatasourceImpl @Inject constructor(
    private val disneyApiInterface: DisneyApiInterface
): NetworkDatasource {
    override suspend fun getAllCharacters(): List<Personaggio> {
        val response = disneyApiInterface.getAllCharacters()
        return if(response.isSuccessful) response.body()!!.toDomainModel() else emptyList()
    }

    override suspend fun getAllCharacters(page: Int): List<Personaggio> {
        val response = disneyApiInterface.getAllCharacters(page)
        return if(response.isSuccessful) response.body()!!.toDomainModel() else emptyList()
    }

    override suspend fun getOneCharacter(id: Int): Personaggio {
        val response = disneyApiInterface.getOneCharacter(id)
        return if(response.isSuccessful) {
            response.body()!!.toDomainModel().first { id == it._id }
        } else { Personaggio() }
    }
}