package com.example.meme_developers_life_app.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DevelopersLifeApi {

    companion object {
        const val BASE_URL = "https://developerslife.ru/"
    }

    @GET("/latest/{page}")
    suspend fun getLatestMemes(
        @Path("page") page : Int,
        @Query("json") isJsonFormat : Boolean = true
    ) : DevelopersLifeResponse
}