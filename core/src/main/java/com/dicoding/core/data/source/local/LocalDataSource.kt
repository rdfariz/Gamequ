package com.dicoding.core.data.source.local

import com.dicoding.core.data.source.local.entity.GamesEntity
import com.dicoding.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gamesDao: GamesDao) {

    fun getAllGames(): Flow<List<GamesEntity>> = gamesDao.getAllGames()
    fun getFavoriteGames(): Flow<List<GamesEntity>> = gamesDao.getFavoriteGames()

    suspend fun insertGames(gamesList: List<GamesEntity>) = gamesDao.insertGames(gamesList)
    suspend fun updateGame(game: GamesEntity) = gamesDao.updateGame(game)

    fun setFavoriteGames(games: GamesEntity, newState: Boolean) {
        games.isFavorite = newState
        gamesDao.updateFavoriteGames(games)
    }

    suspend fun clearFavoriteGames() = gamesDao.clearFavoriteGames()

    fun getDetailGame(id: String): Flow<GamesEntity> = gamesDao.getDetailGame(id)
}