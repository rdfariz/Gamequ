package com.dicoding.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.source.local.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 3, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}