package com.kalashnyk.denys.movaapp.base.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.kalashnyk.denys.movaapp.utils.logger.Logger
import com.kalashnyk.denys.movaapp.utils.state.LoadingState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()
    val macroLoadingState = MediatorLiveData<LoadingState>()
    val error = MediatorLiveData<Throwable>()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun handleError(t: Throwable) {
        Logger.e(this::class, t)
        error.postValue(t)
    }
}