package com.kalashnyk.denys.movaapp.di.component

import com.kalashnyk.denys.movaapp.di.module.ApiModule
import com.kalashnyk.denys.movaapp.di.scope.ApiScope
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.GiphyRemoteDataSource
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val communicator: ServerCommunicator
    val giphyRemote: GiphyRemoteDataSource
}