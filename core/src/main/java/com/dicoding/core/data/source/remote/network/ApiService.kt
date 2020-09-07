package com.dicoding.core.data.source.remote.network

import com.dicoding.core.data.source.remote.response.GameResponse
import com.dicoding.core.data.source.remote.response.ListGamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getList(@Query("page") page: Int, @Query("page_size") page_size: Int): ListGamesResponse
    @GET("games/{id}")
    suspend fun getDetail(@Path("id") id: String): GameResponse
}
