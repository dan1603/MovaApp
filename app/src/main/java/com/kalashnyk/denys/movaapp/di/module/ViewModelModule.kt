package com.kalashnyk.denys.movaapp.di.module

import com.kalashnyk.denys.movaapp.MovaIoApp
import com.kalashnyk.denys.movaapp.di.scope.ViewModelScope
import com.kalashnyk.denys.movaapp.domain.search.SearchHistoryViewModel
import com.kalashnyk.denys.movaapp.usecases.SearchHistoryUseCases
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private val app: MovaIoApp) {

    @ViewModelScope
    @Provides
    internal fun provideCitiesViewModel(useCases: SearchHistoryUseCases): SearchHistoryViewModel =
        SearchHistoryViewModel(
            useCases
        )
}