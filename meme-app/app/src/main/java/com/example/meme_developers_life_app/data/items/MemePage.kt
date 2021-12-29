package com.example.meme_developers_life_app.data.items

import android.os.Parcelable
import com.example.meme_developers_life_app.data.items.Meme
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MemePage(
    val result: List<Meme>,
    val totalCount : Int
) : Parcelable