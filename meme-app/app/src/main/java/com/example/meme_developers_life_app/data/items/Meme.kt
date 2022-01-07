package com.example.meme_developers_life_app.data.items

import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.meme_developers_life_app.R
import kotlinx.parcelize.Parcelize

@Entity(tableName = "saved_memes")
@Parcelize
data class Meme(
    @PrimaryKey val id: Long,
    val description: String,
    val votes: Int,
    val author: String,
    val date: String,
    val gifURL: String?,
    val gifSize: Int,
    val previewURL: String?,
    val videoURL: String?,
    val videoPath: String?,
    val videoSize: Int,
    val type: String,
    val width: String,
    val height: String,
    val commentsCount: Int,
    val fileSize: Int,
    val canVote: Boolean,
): Parcelable

fun Meme.loadAsGif(itemView : View, toLoad: ImageView) {
    Glide.with(itemView)
        .load(this.gifURL)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(R.drawable.loading_image)
        .error(R.drawable.ic_error)
        .into(toLoad)
}