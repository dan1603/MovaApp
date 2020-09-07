package com.kalashnyk.denys.movaapp.usecases.repository.datasource

import androidx.paging.DataSource
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.AppDatabase
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import com.kalashnyk.denys.movaapp.utils.converter.ConverterFactory

interface SearchHistoryDataSource {
    fun getDataSource(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun getAll(): List<SearchHistoryEntity>
    fun getItemByQuery(query: String): SearchHistoryEntity
    fun insert(item: SearchHistoryEntity)
    fun deleteById(id: String)
    fun deleteByQuery(name: String)
}

class SearchHistoryDataSourceImpl(private val db: AppDatabase): SearchHistoryDataSource {

    override fun getDataSource(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        db.searchHistoryDao()
            .getDataSource()
            .mapByPage(factory::convert)

    override fun getAll(): List<SearchHistoryEntity> = db.searchHistoryDao().getAll()

    override fun getItemByQuery(query: String): SearchHistoryEntity = db.searchHistoryDao().getById(query)

    override fun insert(item: SearchHistoryEntity) {
        db.searchHistoryDao().insert(item)
    }

    override fun deleteById(id: String) {
        db.searchHistoryDao().deleteById(id)
    }

    override fun deleteByQuery(name: String) {
        db.searchHistoryDao().deleteByQuery(name)
    }

}