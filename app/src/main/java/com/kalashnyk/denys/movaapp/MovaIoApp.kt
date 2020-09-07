package com.kalashnyk.denys.movaapp

import android.app.Application
import androidx.room.Room
import com.kalashnyk.denys.movaapp.di.component.*
import com.kalashnyk.denys.movaapp.di.module.*
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.AppDatabase
import com.kalashnyk.denys.movaapp.utils.settings.APP_DATABASE

class MovaIoApp : Application() {

    private lateinit var viewModelComponent: ViewModelComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun getViewModelComponent(): ViewModelComponent = viewModelComponent

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val dbComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(getAppDatabase()))
            .build()

        val repoComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(dbComponent)
            .repositoryModule(RepositoryModule())
            .build()

        val interactorComponent = DaggerInteractorComponent.builder()
            .repositoryComponent(repoComponent)
            .interactorModule(InteractorModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .interactorComponent(interactorComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    private fun getAppDatabase() = Room.databaseBuilder(this, AppDatabase::class.java, APP_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}