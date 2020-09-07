package com.kalashnyk.denys.movaapp.di.module

import com.kalashnyk.denys.movaapp.usecases.repository.datasource.SearchHistoryDataSource
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.SearchHistoryDataSourceImpl
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val db: AppDatabase) {

    @Provides
    internal fun provideAppDatabase(): AppDatabase = db

    @Provides
    internal fun provideCityDataSource(db: AppDatabase): SearchHistoryDataSource =
        SearchHistoryDataSourceImpl(db)
}