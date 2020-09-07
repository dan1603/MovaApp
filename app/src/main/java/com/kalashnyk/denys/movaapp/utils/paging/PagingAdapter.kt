package com.kalashnyk.denys.movaapp.utils.paging

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.kalashnyk.denys.movaapp.R
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.databinding.ItemSearchHistoryBinding
import com.kalashnyk.denys.movaapp.ui.fragment.search.binding.SearchHistoryActionListener
import com.kalashnyk.denys.movaapp.ui.fragment.search.binding.SearchHistoryModelBinding
import com.kalashnyk.denys.movaapp.usecases.cards.SearchHistoryCardModel
import com.kalashnyk.denys.movaapp.utils.settings.CARD_SEARCH_HISTORY

class PagingAdapter(diffUtil: DiffUtil.ItemCallback<BaseCardModel>) :
    MultiTypeDataBoundAdapter<BaseCardModel, ViewDataBinding>(diffUtil) {

    private var searchHistoryActionListener: SearchHistoryActionListener? = null

    override fun getItemLayoutId(position: Int): Int {
        getItem(position)?.let {
            return when (it.getCardType()) {
                CARD_SEARCH_HISTORY -> R.layout.item_search_history
                else -> -1
            }
        }
        return -1
    }

    override fun bindItem(holder: DataBoundViewHolder<ViewDataBinding>, position: Int, payloads: List<Any>) {
        super.bindItem(holder, position, payloads)
        val item = getItem(position)
        when (holder.binding) {
            is ItemSearchHistoryBinding -> item?.let {
                holder.binding.bindingModel = SearchHistoryModelBinding(
                    it as SearchHistoryCardModel,
                    searchHistoryActionListener
                )
            }
            else -> { }
        }
    }

    fun setSearchHistoryActionListener(listener: SearchHistoryActionListener) {
        this.searchHistoryActionListener = listener
    }
}