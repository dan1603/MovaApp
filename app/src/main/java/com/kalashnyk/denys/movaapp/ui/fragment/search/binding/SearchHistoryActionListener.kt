package com.kalashnyk.denys.movaapp.ui.fragment.search.binding

import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity

interface SearchHistoryActionListener {
    fun showSearchHistoryDetail(query: String)
    fun deleteSearchHistoryItem(searchHistory: SearchHistoryEntity)
}