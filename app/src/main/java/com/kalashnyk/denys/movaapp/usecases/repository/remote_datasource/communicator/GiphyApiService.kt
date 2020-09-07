package com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator

import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.pojo.GiphyQueryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApiService {

    @GET("/v1/stickers/search")
    fun getGifsBySearchQuery(
        @Query("api_key") apiKey: String,
        @Query("q") query: String
    ): Single<GiphyQueryResponse>

}