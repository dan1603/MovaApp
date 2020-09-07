package com.kalashnyk.denys.movaapp.ui.fragment.search.binding

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.kalashnyk.denys.movaapp.usecases.cards.SearchHistoryCardModel

class SearchHistoryModelBinding(
    private val data: SearchHistoryCardModel,
    private val listener: SearchHistoryActionListener?
): BaseObservable() {

    val id: String
        @Bindable get() = data.getBaseModel().id

    val query: String
        @Bindable get() = data.getBaseModel().query

    val imageRemote: String
        @Bindable get() = data.getBaseModel().imageRemote

    fun onItemClick(model: SearchHistoryModelBinding) {
        listener?.showSearchHistoryDetail(model.data.getBaseModel().query)
    }

    fun onDeleteClick(model: SearchHistoryModelBinding) {
        listener?.deleteSearchHistoryItem(model.data.getBaseModel())
    }

    companion object {
        @JvmStatic
        @BindingAdapter("searchImage")
        fun bindImage(placeholder: ImageView, path: String?) {
            path?.let {
                Glide.with(placeholder)
                    .load(path)
                    .placeholder(CircularProgressDrawable(placeholder.context).apply {
                        strokeWidth = 5f
                        centerRadius = 30f
                        start()
                    })
                    .into(placeholder)
            }
        }
    }
}