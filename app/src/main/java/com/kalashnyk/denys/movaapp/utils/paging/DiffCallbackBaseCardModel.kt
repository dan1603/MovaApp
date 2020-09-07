package com.kalashnyk.denys.movaapp.utils.paging

import androidx.recyclerview.widget.DiffUtil
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel

class DiffCallbackBaseCardModel : DiffUtil.ItemCallback<BaseCardModel>() {

    companion object {
        val CONTENT = Any()
        val REACTION = Any()
    }

    override fun areItemsTheSame(oldCard: BaseCardModel, newCard: BaseCardModel): Boolean {
        return oldCard.getCardId() == newCard.getCardId()
    }

    override fun areContentsTheSame(oldCard: BaseCardModel, newCard: BaseCardModel): Boolean {
        return oldCard.hashCode() == newCard.hashCode()
    }
}