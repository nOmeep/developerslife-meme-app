package com.example.meme_developers_life_app.ui.fragment_memes.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.meme_developers_life_app.databinding.LoadStateFooterBinding

class MemeLoadStateAdapter(private val retry : () -> Unit) :
    LoadStateAdapter<MemeLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding : LoadStateFooterBinding) : RecyclerView.ViewHolder(binding.root) {

        // такие вещи лучше не делать в onBindViewHolder, тк будут вызываться много раз
        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                // на данный момент идет загрузка новых мемов
                pbProgressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}