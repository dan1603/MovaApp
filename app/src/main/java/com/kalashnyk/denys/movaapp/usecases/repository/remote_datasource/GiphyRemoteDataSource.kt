package com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource

import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator.ServerCommunicator
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.pojo.GiphyQueryResponse
import com.kalashnyk.denys.movaapp.utils.rx.RxTransformers
import io.reactivex.Single

interface GiphyRemoteDataSource {
    fun fetchSearchQuery(query: String): Single<GiphyQueryResponse>
}

class GiphyRemoteDataSourceImpl(private val communicator: ServerCommunicator): GiphyRemoteDataSource {

    override fun fetchSearchQuery(query: String): Single<GiphyQueryResponse> = communicator
        .fetchSearchQuery(query)
        .compose(RxTransformers().singleTransformer())

}