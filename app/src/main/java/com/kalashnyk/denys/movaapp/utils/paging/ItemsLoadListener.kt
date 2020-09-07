package com.kalashnyk.denys.movaapp.utils.paging

interface ItemsLoadListener<T> {

    fun onItemsLoaded(item: T?)

    fun displayProgress()

    fun onLoadError(errorMessage: String)
}