package com.dicoding.core.data.source.remote

import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.network.ApiService
import com.dicoding.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGames(page: Int, page_size: Int): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getList(page, page_size)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Something went wrong.\nReconnect to the Internet and Try again"))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(id: String): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Something went wrong.\nReconnect to the Internet and Try again"))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}

