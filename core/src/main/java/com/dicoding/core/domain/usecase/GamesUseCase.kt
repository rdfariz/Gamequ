package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getAllGames(page: Int = 1, page_size: Int = 25): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGames(game: Game, state: Boolean)
    fun clearFavoriteGames()
    fun getDetailGame(id: String): Flow<Resource<Game>>
}