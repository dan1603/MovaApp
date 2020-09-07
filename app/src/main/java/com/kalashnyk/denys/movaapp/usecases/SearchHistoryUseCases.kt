package com.kalashnyk.denys.movaapp.usecases

import androidx.paging.DataSource
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.usecases.repository.SearchHistoryRepository
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import com.kalashnyk.denys.movaapp.utils.converter.ConverterFactory
import com.kalashnyk.denys.movaapp.utils.error.SearchDelivery
import io.reactivex.Completable
import io.reactivex.Single

interface SearchHistoryUseCases {
    fun fetch(): Completable
    fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun getItemByQuery(query: String): Single<SearchHistoryEntity>
    fun deleteById(id: String): Completable
    fun deleteByName(name: String): Completable
    fun search(name: String, search: SearchDelivery): Completable
}

class SearchHistoryUseCasesImpl(private val repo: SearchHistoryRepository): SearchHistoryUseCases {

    override fun fetch(): Completable = repo.fetch()

    override fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        repo.getDataSourceFactory(factory)

    override fun getItemByQuery(query: String): Single<SearchHistoryEntity> = repo.getItemByQuery(query)

    override fun deleteById(id: String): Completable = repo.deleteById(id)

    override fun deleteByName(name: String): Completable = repo.deleteByName(name)

    override fun search(name: String, search: SearchDelivery): Completable = repo.search(name, search)
}
