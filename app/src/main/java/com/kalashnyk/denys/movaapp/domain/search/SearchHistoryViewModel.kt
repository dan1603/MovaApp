package com.kalashnyk.denys.movaapp.domain.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.kalashnyk.denys.movaapp.base.domain.BasePagingViewModel
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.usecases.SearchHistoryUseCases
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import com.kalashnyk.denys.movaapp.utils.converter.ConverterFactory
import com.kalashnyk.denys.movaapp.utils.error.SearchDelivery
import com.kalashnyk.denys.movaapp.utils.logger.Logger
import com.kalashnyk.denys.movaapp.utils.paging.ItemsLoadListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchHistoryViewModel(private val searchUseCases: SearchHistoryUseCases): BasePagingViewModel(), SearchDelivery {

    val searchItem = MutableLiveData<SearchHistoryEntity?>()
    val searchQuery = MutableLiveData<String?>()

    init {
        initPagedConfig()
    }

    fun initLiveData(listener: ItemsLoadListener<PagedList<BaseCardModel>>) {
        itemLoadedListener = listener
        initPagedListLiveData(searchUseCases.getDataSourceFactory(ConverterFactory()))
    }

    fun getPagedList(): LiveData<PagedList<BaseCardModel>> = listCards

    fun searchGif(query: String) {
        disposable.add(
            searchUseCases.search(query, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    fun deleteById(id: String) {
        disposable.add(
            searchUseCases.deleteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    fun getItemByQuery(query: String) {
        disposable.add(
            searchUseCases.getItemByQuery(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    searchItem.postValue(it)
                }, {
                    deliverError(it)
                })
        )
    }

    override fun fetchData() {
        disposable.add(
            searchUseCases.fetch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setRefreshing(false)
                    setLoading(false)
                }, {
                    deliverError(it)
                })
        )
    }

    override fun clearCachedItems() { }

    override fun deliverError(t: Throwable) {
        setRefreshing(false)
        setLoading(false)
        handleError(t)
    }

    override fun deliverResult(id: String?) {
        if (id == null) {
            searchQuery.postValue("")
        } else {
            searchQuery.postValue(id)
        }
    }
}