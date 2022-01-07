package com.example.meme_developers_life_app.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meme_developers_life_app.api.DevelopersLifeApi
import com.example.meme_developers_life_app.data.items.Meme
import retrofit2.HttpException
import java.io.IOException

private const val LATEST_MEMES_START_PAGE = 0

class MemePagingSource(
    private val memeApi : DevelopersLifeApi,
    private val category : String
) : PagingSource<Int, Meme>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meme> {
        val position = params.key ?: LATEST_MEMES_START_PAGE

        return try {

            if (category == "random") {
                val randomMemes = mutableSetOf<Meme>()
                repeat(10) {
                    val response = memeApi.getRandomMeme()
                    randomMemes.add(response)
                }
                LoadResult.Page(
                    data = randomMemes.toList(),
                    prevKey = if (position == LATEST_MEMES_START_PAGE) null
                    else position - 1,
                    nextKey = if (randomMemes.isEmpty()) null
                    else position + 1
                )
            } else {
                val response = memeApi.getLatestMemes(category, position)

                LoadResult.Page(
                    data = response.result,
                    prevKey = if (position == LATEST_MEMES_START_PAGE) null
                    else position - 1,
                    nextKey = if (response.result.isEmpty()) null
                    else position + 1
                )
            }
        } catch(e : IOException) {
            Log.d("ERROR", "$e")
            return LoadResult.Error(e)
        } catch (e : HttpException) {
            Log.d("ERROR", "$e")
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Meme>): Int? {
        TODO("Not yet implemented")
    }
}