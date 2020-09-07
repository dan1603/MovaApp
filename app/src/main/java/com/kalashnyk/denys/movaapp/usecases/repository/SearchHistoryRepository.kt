package com.kalashnyk.denys.movaapp.usecases.repository

import androidx.paging.DataSource
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.SearchHistoryDataSource
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.GiphyRemoteDataSource
import com.kalashnyk.denys.movaapp.utils.converter.ConverterFactory
import com.kalashnyk.denys.movaapp.utils.error.SearchDelivery
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface SearchHistoryRepository {
    fun fetch(): Completable
    fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel>
    fun getItemByQuery(query: String): Single<SearchHistoryEntity>
    fun deleteById(id: String): Completable
    fun deleteByName(name: String): Completable
    fun search(query: String, search: SearchDelivery): Completable
}

class SearchHistoryRepositoryImpl(
    private val remote: GiphyRemoteDataSource,
    private val searchHistoryDataSource: SearchHistoryDataSource
): SearchHistoryRepository {

    override fun fetch(): Completable = Completable.fromAction {
        searchHistoryDataSource.getAll()
    }

    override fun getDataSourceFactory(factory: ConverterFactory): DataSource.Factory<Int, BaseCardModel> =
        searchHistoryDataSource.getDataSource(factory)

    override fun getItemByQuery(query: String): Single<SearchHistoryEntity> = Single.just(searchHistoryDataSource.getItemByQuery(query))

    override fun deleteById(id: String) = Completable.fromAction {
        searchHistoryDataSource.deleteById(id)
    }

    override fun deleteByName(name: String) = Completable.fromAction {
        searchHistoryDataSource.deleteByQuery(name)
    }

    override fun search(query: String, search: SearchDelivery): Completable = Completable.fromAction {
        remote.fetchSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                if (it.data.isNotEmpty()) {
                    val searchResult = it.convert(query)
                    searchHistoryDataSource.insert(searchResult)
                    search.deliverResult(searchResult.id)
                } else {
                    search.deliverResult(null)
                }
            }, {
                search.deliverError(it)
            })
    }
}