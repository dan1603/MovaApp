package com.kalashnyk.denys.movaapp.di.module

import com.kalashnyk.denys.movaapp.di.scope.RepositoryScope
import com.kalashnyk.denys.movaapp.usecases.repository.SearchHistoryRepository
import com.kalashnyk.denys.movaapp.usecases.repository.SearchHistoryRepositoryImpl
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.SearchHistoryDataSource
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.GiphyRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    internal fun provideCityRepository(
        remote: GiphyRemoteDataSource,
        searchHistoryDataSource: SearchHistoryDataSource
    ): SearchHistoryRepository = SearchHistoryRepositoryImpl(remote, searchHistoryDataSource)
}