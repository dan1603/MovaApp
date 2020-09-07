package com.kalashnyk.denys.movaapp.usecases.cards

import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import com.kalashnyk.denys.movaapp.utils.settings.CARD_SEARCH_HISTORY

class SearchHistoryCardModel(private var searchHistory: SearchHistoryEntity) : BaseCardModel() {

    override fun getCardId(): String = searchHistory.id.toString()

    override fun getCardType(): String = CARD_SEARCH_HISTORY

    override fun getBaseModel(): SearchHistoryEntity = searchHistory

}