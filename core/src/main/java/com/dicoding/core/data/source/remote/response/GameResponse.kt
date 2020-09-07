package com.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("name_original")
    val name_original: String?,

    @field:SerializedName("description_raw")
    val description: String?,

    @field:SerializedName("released")
    val released: String?,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("ratings_count")
    val ratings_count: String,

    @field:SerializedName("reviews_text_count")
    val reviews_text_count: String,

    @field:SerializedName("reviews_count")
    val reviews_count: String,

    @field:SerializedName("background_image")
    val image: String
)

