package com.example.meme_developers_life_app.ui.fragment_memes

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.meme_developers_life_app.data.MemeRepository
import com.example.meme_developers_life_app.data.items.Meme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(
    private val repository: MemeRepository
) : ViewModel() {
    val memeDao = repository.memeDao

    private val currentCategory = MutableLiveData("random")

    fun getMemes() : LiveData<PagingData<Meme>> {
        return currentCategory.switchMap { categoryString ->
            repository.searchPage(categoryString)
                .cachedIn(viewModelScope)
        }
    }

    fun switchCategory(category: String) {
        currentCategory.value = category
    }

    fun getAllSaved(): LiveData<List<Meme>> {
        return repository.memeDao.getAllSavedMemes()
    }
}