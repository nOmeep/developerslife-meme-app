package com.example.meme_developers_life_app.di

import com.example.meme_developers_life_app.api.DevelopersLifeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDevelopersLifeApi() : DevelopersLifeApi =
        Retrofit.Builder()
            .baseUrl(DevelopersLifeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DevelopersLifeApi::class.java)
}