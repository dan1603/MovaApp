package com.kalashnyk.denys.movaapp.di.component

import com.kalashnyk.denys.movaapp.di.module.ViewModelModule
import com.kalashnyk.denys.movaapp.di.scope.ViewModelScope
import com.kalashnyk.denys.movaapp.ui.activity.main.MainActivity
import com.kalashnyk.denys.movaapp.ui.activity.splash.SplashActivity
import com.kalashnyk.denys.movaapp.ui.fragment.detail.ImageDetailFragment
import com.kalashnyk.denys.movaapp.ui.fragment.search.SearchHistoryFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: SearchHistoryFragment)
    fun inject(fragment: ImageDetailFragment)
}