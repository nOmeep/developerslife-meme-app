package com.example.meme_developers_life_app.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersLifeApi {

    companion object {
        const val BASE_URL = "https://developerslife.ru/"
    }

    @GET("/latest/{page}?json=true")
    suspend fun getLatestMemes(
        @Path("page") page: Int
    ): Response<DevelopersLifeResponse>
}