package com.example.meme_developers_life_app.api

import com.example.meme_developers_life_app.data.items.Meme
import com.example.meme_developers_life_app.data.items.MemePage
import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersLifeApi {

    companion object {
        const val BASE_URL = "https://developerslife.ru/"
    }

    @GET("/random?json=true")
    suspend fun getRandomMeme() : Meme

    @GET("/{category}/{page}?json=true")
    suspend fun getLatestMemes(
        @Path("category") category : String,
        @Path("page") page: Int
    ): MemePage
}