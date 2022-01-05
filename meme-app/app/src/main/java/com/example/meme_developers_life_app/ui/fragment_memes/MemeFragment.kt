package com.example.meme_developers_life_app.ui.fragment_memes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.meme_developers_life_app.R
import com.example.meme_developers_life_app.databinding.FragmentMemesBinding
import com.example.meme_developers_life_app.ui.fragment_memes.loading.MemeLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemeFragment: Fragment(R.layout.fragment_memes) {
    private val viewModel by viewModels<MemeViewModel>()

    private var _binding : FragmentMemesBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMemesBinding.bind(view)

        val adapter = MemePagerAdapter(viewModel.memeDao)

        adapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                binding.tvEmptyRequest.isVisible = adapter.itemCount < 1
            }
        }

        binding.apply {
            rvMemes.setHasFixedSize(true)
            rvMemes.adapter = adapter.withLoadStateHeaderAndFooter(
                // loading верху
                header = MemeLoadStateAdapter {
                    // функция пейджера, которая сама перезапустит
                    adapter.retry()
                },
                // loading снизу
                footer = MemeLoadStateAdapter {
                    // функция пейджера, которая сама перезапустит
                    adapter.retry()
                }
            )
        }

        // тут запоминаем мемы
        viewModel.memes.observe(viewLifecycleOwner) {
            binding.tvEmptyRequest.isVisible = false
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.meme_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.miLatest -> {
                viewModel.switchCategory("latest")
            }
            R.id.miHot -> {
                viewModel.switchCategory("hot")
            }
            R.id.miTop -> {
                viewModel.switchCategory("top")
            }
        }

        binding.rvMemes.scrollToPosition(0)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}