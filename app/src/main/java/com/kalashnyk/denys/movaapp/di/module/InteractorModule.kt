package com.kalashnyk.denys.movaapp.di.module

import com.kalashnyk.denys.movaapp.di.scope.InteractorScope
import com.kalashnyk.denys.movaapp.usecases.SearchHistoryUseCases
import com.kalashnyk.denys.movaapp.usecases.SearchHistoryUseCasesImpl
import com.kalashnyk.denys.movaapp.usecases.repository.SearchHistoryRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @InteractorScope
    @Provides
    internal fun provideCityUseCases(repo: SearchHistoryRepository): SearchHistoryUseCases =
        SearchHistoryUseCasesImpl(repo)
}