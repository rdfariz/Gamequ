package com.dicoding.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GAMEQU")
data class GamesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "name_original")
    var name_original: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "released")
    var released: String?,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "ratings_count")
    var ratings_count: String,

    @ColumnInfo(name = "reviews_text_count")
    var reviews_text_count: String,

    @ColumnInfo(name = "reviews_count")
    var reviews_count: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
