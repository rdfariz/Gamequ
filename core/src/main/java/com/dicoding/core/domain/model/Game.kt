package com.dicoding.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val id: String,
    val name: String,
    val name_original: String?,
    val description: String?,
    val released: String?,
    val rating: String,
    val ratings_count: String,
    val reviews_text_count: String,
    val reviews_count: String,
    val image: String,
    val isFavorite: Boolean
) : Parcelable