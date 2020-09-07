package com.kalashnyk.denys.movaapp.di.component

import com.kalashnyk.denys.movaapp.di.module.RepositoryModule
import com.kalashnyk.denys.movaapp.di.scope.RepositoryScope
import com.kalashnyk.denys.movaapp.usecases.repository.SearchHistoryRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val searchHistoryRepo: SearchHistoryRepository
}