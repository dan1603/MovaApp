package com.kalashnyk.denys.movaapp.utils.converter

import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.base.model.BaseModel
import com.kalashnyk.denys.movaapp.usecases.cards.SearchHistoryCardModel
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import java.util.ArrayList

class ConverterFactory {

    fun convert(list: List<BaseModel>): List<BaseCardModel> {
        val items = ArrayList<BaseCardModel>()
        list.forEach {
            convert(it)?.apply { items.add(this) }
        }
        return items
    }

    private fun convert(item: BaseModel): BaseCardModel? = when (item) {
        is SearchHistoryEntity -> SearchHistoryCardModel(item)
        else -> null
    }

}
