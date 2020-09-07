package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.Game
import com.dicoding.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository): GamesUseCase {
    override fun getAllGames(page: Int, page_size: Int) = gamesRepository.getAllGames(page, page_size)
    override fun getFavoriteGames() = gamesRepository.getFavoriteGames()
    override fun setFavoriteGames(game: Game, state: Boolean) = gamesRepository.setFavoriteGames(game, state)
    override fun clearFavoriteGames() = gamesRepository.clearFavoriteGames()
    override fun getDetailGame(id: String): Flow<Resource<Game>> = gamesRepository.getDetailGame(id)
}