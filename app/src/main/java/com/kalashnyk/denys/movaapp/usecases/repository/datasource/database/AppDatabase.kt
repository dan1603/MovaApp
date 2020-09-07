package com.kalashnyk.denys.movaapp.usecases.repository.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.dao.CityDao
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity

@Database(
    entities = [
        SearchHistoryEntity::class
    ], version = 1
)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun searchHistoryDao(): CityDao
}