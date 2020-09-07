package com.kalashnyk.denys.movaapp.base.domain

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.utils.paging.ItemsLoadListener
import com.kalashnyk.denys.movaapp.utils.settings.CONTENT_PAGE_SIZE
import com.kalashnyk.denys.movaapp.utils.settings.DEFAULT_INITIAL_LOADED_KEY

abstract class BasePagingViewModel : BaseViewModel() {

    protected lateinit var listCards: LiveData<PagedList<BaseCardModel>>
    protected lateinit var itemLoadedListener: ItemsLoadListener<PagedList<BaseCardModel>>

    private val refreshing = ObservableBoolean()
    private val loading = ObservableBoolean()

    private lateinit var pagedListConfiguration: PagedList.Config

    abstract fun fetchData()

    //abstract fun rangeData(screenType: String, itemId: String)

    abstract fun clearCachedItems()

    fun initPagedConfig() {
        pagedListConfiguration = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            //.setProgress(true)
            .setPageSize(CONTENT_PAGE_SIZE)
            .setPrefetchDistance(CONTENT_PAGE_SIZE / 2)
            .build()
    }

    fun initPagedListLiveData(factory: DataSource.Factory<Int, BaseCardModel>) {
        listCards = initPagedList(factory, DEFAULT_INITIAL_LOADED_KEY)
    }

    fun getRefreshing(): ObservableBoolean {
        return refreshing
    }

    fun setRefreshing(isRefreshing: Boolean) {
        refreshing.set(isRefreshing)
    }

    fun isLoading(): ObservableBoolean {
        return loading
    }

    protected fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }

    private fun initPagedList(
        factory: DataSource.Factory<Int, BaseCardModel>,
        initialLoadKey: Int
    ): LiveData<PagedList<BaseCardModel>> {
        setLoading(false)
        return LivePagedListBuilder(factory, pagedListConfiguration)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<BaseCardModel>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                }

                override fun onItemAtFrontLoaded(itemAtFront: BaseCardModel) {
                    super.onItemAtFrontLoaded(itemAtFront)
                }

                override fun onItemAtEndLoaded(itemAtEnd: BaseCardModel) {
                    super.onItemAtEndLoaded(itemAtEnd)

                    //lastItemId?.let {
                        // todo add after remove moc logic
                        //if(it != itemAtEnd.getCardId()){
                        //rangeData(it, itemAtEnd.getCardId())
                        //lastItemId=itemAtEnd.getCardId()
                        //}
                    //}
                }
            })
            .setInitialLoadKey(initialLoadKey)
            .build()
    }
}