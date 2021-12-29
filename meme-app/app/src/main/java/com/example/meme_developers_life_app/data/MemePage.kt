package com.example.meme_developers_life_app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemePage(
    val result: List<Meme>
) : Parcelable {

    @Parcelize
    data class Meme(
        val author: String,
        val canVote: Boolean,
        val commentsCount: Int,
        val date: String,
        val description: String,
        val fileSize: Int,
        val gifSize: Int,
        val gifURL: String,
        val height: String,
        val id: Int,
        val previewURL: String,
        val type: String,
        val videoPath: String,
        val videoSize: Int,
        val videoURL: String,
        val votes: Int,
        val width: String
    ) : Parcelable
}