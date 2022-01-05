package com.example.meme_developers_life_app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meme_developers_life_app.data.items.Meme

@Database(entities = [Meme::class], version = 1)
abstract class SavedMemesDatabase : RoomDatabase() {
    abstract fun getMemeDao(): MemeDao
}