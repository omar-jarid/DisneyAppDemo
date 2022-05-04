package omarjarid.disneyappdemo.framework.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DisneyApiInterface {
    @GET("characters")
    suspend fun getAllCharacters(): Response<PersonaggioDataModel>

    @GET("characters")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<PersonaggioDataModel>

    @GET("characters")
    suspend fun getOneCharacter(@Path(":id") id: Int): Response<PersonaggioDataModel>
}