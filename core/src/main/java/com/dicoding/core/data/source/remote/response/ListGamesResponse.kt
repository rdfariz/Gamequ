package com.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGamesResponse(

    @field:SerializedName("count")
    val count: Number,

    @field:SerializedName("results")
    val results: List<GameResponse>
)