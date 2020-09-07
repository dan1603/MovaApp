package com.kalashnyk.denys.movaapp.di.module

import com.kalashnyk.denys.movaapp.di.scope.ApiScope
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.GiphyRemoteDataSource
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.GiphyRemoteDataSourceImpl
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator.ServerCommunicator
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator.ServerCommunicatorImpl
import com.kalashnyk.denys.movaapp.usecases.repository.remote_datasource.communicator.GiphyApiService
import com.kalashnyk.denys.movaapp.utils.settings.GIPHY_API_URL
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    @Provides
    @ApiScope
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @ApiScope
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit = builder
        .baseUrl(GIPHY_API_URL).build()

    @Provides
    @ApiScope
    fun provideGiphyApi(retrofit: Retrofit): GiphyApiService = retrofit.create(GiphyApiService::class.java)

    @Provides
    @ApiScope
    fun provideCommunicator(apiService: GiphyApiService): ServerCommunicator =
        ServerCommunicatorImpl(apiService)

    @Provides
    @ApiScope
    fun provideGiphyRemoteDataSource(communicator: ServerCommunicator): GiphyRemoteDataSource =
        GiphyRemoteDataSourceImpl(communicator)

}