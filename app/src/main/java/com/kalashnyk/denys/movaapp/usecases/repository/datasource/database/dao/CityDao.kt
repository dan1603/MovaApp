package com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM search")
    fun getAll(): List<SearchHistoryEntity>

    @Query("SELECT * FROM search WHERE id = :id")
    fun getById(id: String): SearchHistoryEntity

    @Query("SELECT * FROM search WHERE `query` = :query")
    fun getByQuery(query: String): SearchHistoryEntity

    @Query("SELECT * FROM search")
    fun getDataSource(): DataSource.Factory<Int, SearchHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<SearchHistoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: SearchHistoryEntity)

    @Update
    fun updateList(list: List<SearchHistoryEntity>)

    @Update
    fun update(item: SearchHistoryEntity)

    @Query("DELETE FROM search")
    fun deleteAll()

    @Query("DELETE FROM search WHERE id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM search WHERE `query` = :query")
    fun deleteByQuery(query: String)
}