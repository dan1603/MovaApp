package com.kalashnyk.denys.movaapp.ui.activity.main

import android.view.MenuItem
import com.kalashnyk.denys.movaapp.R
import com.kalashnyk.denys.movaapp.base.ui.BaseActivity
import com.kalashnyk.denys.movaapp.databinding.MainDataBinding
import com.kalashnyk.denys.movaapp.di.component.ViewModelComponent
import com.kalashnyk.denys.movaapp.ui.navigator.model.Pages
import com.kalashnyk.denys.movaapp.utils.extensions.hideKeyboard
import com.kalashnyk.denys.movaapp.utils.settings.MAIN_TYPE_SCREEN

class MainActivity : BaseActivity<MainDataBinding>() {

    var currentSearchQuery: String = ""

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setupViewLogic(binder: MainDataBinding) {
        showRootChild()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun showRootChild() {
        openPage(handlePage())
        hideKeyboard()
    }

    private fun handlePage() = intent?.extras?.getSerializable(MAIN_TYPE_SCREEN) as Pages
}
