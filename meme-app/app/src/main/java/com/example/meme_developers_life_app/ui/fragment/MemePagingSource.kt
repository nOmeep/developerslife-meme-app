package com.example.meme_developers_life_app.ui.fragment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meme_developers_life_app.api.DevelopersLifeApi
import com.example.meme_developers_life_app.data.MemePage
import retrofit2.HttpException
import java.io.IOException

private const val LATEST_MEMES_START_PAGE = 1

class MemePagingSource(
    private val memeApi : DevelopersLifeApi
) : PagingSource<Int, MemePage.Meme>() {
    // ZOCHEM NUZEN ETOT METOD?
    override fun getRefreshKey(state: PagingState<Int, MemePage.Meme>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MemePage.Meme> {
        val position = params.key ?: LATEST_MEMES_START_PAGE

        return try {
            val response = memeApi.getLatestMemes(position)
            val page = response.memePage

            LoadResult.Page(
                data = page.result,
                prevKey = if (position == LATEST_MEMES_START_PAGE) null
                        else position - 1,
                nextKey = if (page.result.isEmpty()) null
                        else position + 1
            )
        } catch(e : IOException) {
            LoadResult.Error(e)
        } catch (e : HttpException) {
            LoadResult.Error(e)
        }
    }
}