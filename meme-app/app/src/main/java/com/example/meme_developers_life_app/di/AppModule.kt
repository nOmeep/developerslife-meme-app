package com.example.meme_developers_life_app.di

import android.app.Application
import androidx.room.Room
import com.example.meme_developers_life_app.api.DevelopersLifeApi
import com.example.meme_developers_life_app.db.SavedMemesDatabase
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
    fun provideApi() : DevelopersLifeApi {
        return Retrofit.Builder()
                .baseUrl(DevelopersLifeApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DevelopersLifeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : SavedMemesDatabase {
        return Room.databaseBuilder(app, SavedMemesDatabase::class.java, "saved_memes_database")
            .build()
    }
}