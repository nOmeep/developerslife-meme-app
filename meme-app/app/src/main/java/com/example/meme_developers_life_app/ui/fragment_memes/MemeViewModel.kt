package com.example.meme_developers_life_app.ui.fragment_memes

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.meme_developers_life_app.data.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(
    private val repository: MemeRepository
) : ViewModel() {

    private val currentCategory = MutableLiveData("latest")

    val memes = currentCategory.switchMap { categoryString ->
        repository.searchPage(categoryString)
            .cachedIn(viewModelScope)
    }

    fun switchCategory(category: String) {
        currentCategory.value = category
    }
}