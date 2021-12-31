package com.example.meme_developers_life_app.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.meme_developers_life_app.R
import com.example.meme_developers_life_app.data.items.Meme
import com.example.meme_developers_life_app.databinding.ItemSingleMemeBinding

class MemePagerAdapter : PagingDataAdapter<Meme, MemePagerAdapter.MemeViewHolder>(MEME_DIFFER) {

    companion object {
        private val MEME_DIFFER = object : DiffUtil.ItemCallback<Meme>() {
            override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Meme,
                newItem: Meme
            ): Boolean =
                oldItem == newItem
        }
    }

    inner class MemeViewHolder(private val binding: ItemSingleMemeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meme : Meme) {
            binding.apply {
                Glide.with(itemView)
                    .load(meme.gifURL)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.loading_image)
                    .error(R.drawable.ic_error)
                    .into(ivMemePicture)

                tvMemeName.text = meme.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val binding = ItemSingleMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val currentMeme = getItem(position)
        if (currentMeme != null) {
            holder.bind(currentMeme)
        }
    }
}