package com.example.meme_developers_life_app.api

import com.example.meme_developers_life_app.data.items.MemePage
import okhttp3.ResponseBody
import retrofit2.Call
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
    ): MemePage
}