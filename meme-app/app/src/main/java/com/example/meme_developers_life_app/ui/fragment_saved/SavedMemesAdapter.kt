package com.example.meme_developers_life_app.ui.fragment_saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.meme_developers_life_app.data.items.Meme
import com.example.meme_developers_life_app.data.items.loadAsGif
import com.example.meme_developers_life_app.databinding.ItemSavedMemeBinding
import com.example.meme_developers_life_app.db.MemeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedMemesAdapter(
    private val memeDao : MemeDao
) : RecyclerView.Adapter<SavedMemesAdapter.SavedMemeViewHolder>() {

    private val asyncDiffer = AsyncListDiffer(this, DIFF_CALLBACK)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meme>() {
            override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean =
                oldItem == newItem
        }
    }

    fun submitList(list : List<Meme>) {
        asyncDiffer.submitList(list)
    }

    inner class SavedMemeViewHolder(private val binding: ItemSavedMemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meme: Meme) {
            binding.apply {
                meme.loadAsGif(itemView, ivImageSaved)

                cardView.setOnLongClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        memeDao.deleteSingleSavedMeme(meme.id)
                    }
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMemeViewHolder {
        val binding = ItemSavedMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedMemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedMemeViewHolder, position: Int) {
        val currentMeme = asyncDiffer.currentList[position]
        holder.bind(currentMeme)
    }

    override fun getItemCount(): Int {
        return asyncDiffer.currentList.size
    }
}