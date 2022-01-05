package com.example.meme_developers_life_app.ui.fragment_saved

import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.meme_developers_life_app.R
import com.example.meme_developers_life_app.databinding.FragmentSavedBinding
import com.example.meme_developers_life_app.ui.fragment_memes.MemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentSaved: Fragment(R.layout.fragment_saved) {
    private val viewModel by viewModels<MemeViewModel>()

    private var _binding : FragmentSavedBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)

        viewModel.getAllSaved().observe(viewLifecycleOwner) { savedMemes ->
            binding.apply {
                tvEmptyList.isVisible = savedMemes.isEmpty()
                rvSaved.adapter = SavedMemesAdapter(savedMemes, viewModel.memeDao)
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.saved_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.miDeleteAll -> lifecycleScope.launch {
                viewModel.memeDao.deleteAllSavings()
            }
        }
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}