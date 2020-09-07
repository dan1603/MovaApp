package com.kalashnyk.denys.movaapp.utils.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.movaapp.utils.extensions.gone
import com.kalashnyk.denys.movaapp.utils.extensions.visible

object ViewBinding {
    @JvmStatic
    @BindingAdapter("viewVisibility")
    fun bindVisibility(v: View, isVisible: Boolean) {
        if (isVisible) v.visible() else v.gone()
    }
}