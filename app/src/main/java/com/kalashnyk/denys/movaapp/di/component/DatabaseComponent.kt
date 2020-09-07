package com.kalashnyk.denys.movaapp.di.component

import com.kalashnyk.denys.movaapp.di.module.DatabaseModule
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.SearchHistoryDataSource
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
    val searchHistoryDs: SearchHistoryDataSource
}