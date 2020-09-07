package com.kalashnyk.denys.movaapp.di.component

import com.kalashnyk.denys.movaapp.di.module.InteractorModule
import com.kalashnyk.denys.movaapp.di.scope.InteractorScope
import com.kalashnyk.denys.movaapp.usecases.SearchHistoryUseCases
import dagger.Component

@InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
interface InteractorComponent {
    val searchHistoryUseCases: SearchHistoryUseCases
}