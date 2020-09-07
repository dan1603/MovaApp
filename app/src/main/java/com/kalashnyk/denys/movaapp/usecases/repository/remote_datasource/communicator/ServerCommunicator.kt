package com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator

import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.pojo.GiphyQueryResponse
import com.kalashnyk.denys.movaapp.utils.logger.Logger
import com.kalashnyk.denys.movaapp.utils.settings.GIPHY_API_KEY
import io.reactivex.Single

interface ServerCommunicator {
    fun fetchSearchQuery(query: String): Single<GiphyQueryResponse>
}

class ServerCommunicatorImpl(
    private val giphyApi: GiphyApiService
) : ServerCommunicator {

    override fun fetchSearchQuery(query: String): Single<GiphyQueryResponse> = giphyApi
        .getGifsBySearchQuery(GIPHY_API_KEY, query)
        .doOnError { Logger.e(this::class, it) }
}