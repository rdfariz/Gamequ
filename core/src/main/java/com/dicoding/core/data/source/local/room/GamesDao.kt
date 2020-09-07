package com.dicoding.core.data.source.local.room

import androidx.room.*
import com.dicoding.core.data.source.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM GAMEQU")
    fun getAllGames(): Flow<List<GamesEntity>>

    @Query("SELECT * FROM GAMEQU where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GamesEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: GamesEntity)

    @Update
    fun updateFavoriteGames(games: GamesEntity)

    @Query("UPDATE GAMEQU SET isFavorite = 0 where isFavorite = 1")
    suspend fun clearFavoriteGames()

    @Query("SELECT * FROM GAMEQU WHERE id = :id")
    fun getDetailGame(id: String): Flow<GamesEntity>
}
