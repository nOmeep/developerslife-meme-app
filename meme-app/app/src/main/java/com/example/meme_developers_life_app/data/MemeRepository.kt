package com.example.meme_developers_life_app.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.meme_developers_life_app.api.DevelopersLifeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemeRepository @Inject constructor(
    private val memeApi : DevelopersLifeApi
) {
    fun searchPage() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MemePagingSource(memeApi)
            }
        ).liveData
}