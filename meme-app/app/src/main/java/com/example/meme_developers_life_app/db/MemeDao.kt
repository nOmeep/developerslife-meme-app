package com.example.meme_developers_life_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meme_developers_life_app.data.items.Meme

@Dao
interface MemeDao {
    @Query("Select * from saved_memes")
    fun getAllSavedMemes(): LiveData<List<Meme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSingleMeme(meme: Meme)

    @Query("Delete from saved_memes")
    suspend fun deleteAllSavings()

    @Query("Delete from saved_memes where id = :memeId")
    suspend fun deleteSingleSavedMeme(memeId: Long)
}