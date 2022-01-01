package com.example.meme_developers_life_app.ui.fragment_memes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.meme_developers_life_app.data.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(
    private val repository: MemeRepository
) : ViewModel() {

    val memes = repository.searchPage()
        .cachedIn(viewModelScope)

}