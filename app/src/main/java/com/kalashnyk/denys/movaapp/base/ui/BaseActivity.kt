package com.kalashnyk.denys.movaapp.base.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kalashnyk.denys.movaapp.R
import com.kalashnyk.denys.movaapp.MovaIoApp
import com.kalashnyk.denys.movaapp.di.component.ViewModelComponent
import com.kalashnyk.denys.movaapp.ui.navigator.Navigator
import com.kalashnyk.denys.movaapp.ui.navigator.NavigatorImpl
import com.kalashnyk.denys.movaapp.ui.navigator.model.PageNavigationItem
import com.kalashnyk.denys.movaapp.ui.navigator.model.Pages
import com.kalashnyk.denys.movaapp.ui.navigator.model.TransitionAnimation
import com.kalashnyk.denys.movaapp.ui.navigator.model.TransitionBundle
import com.kalashnyk.denys.movaapp.utils.extensions.hideKeyboard

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewBinding: V

    protected lateinit var navigator: Navigator

    open lateinit var messageNecessaryPermissions: String

    private var toolbar: Toolbar?=null

    companion object {
        private const val DEBUG_ENABLED=false
    }

    abstract fun injectDependency(component: ViewModelComponent)

    abstract fun getLayoutId(): Int

    abstract fun setupViewLogic(binder: V)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        navigator = NavigatorImpl(this)
        createDaggerDependencies()
        setupViewLogic(viewBinding)
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
    }

    override fun onBackPressed() {
        hideKeyboard()
        //if (!navigator.back()) {
            super.onBackPressed()
        //}
    }

    fun setupAppBar(title: Int, back: Boolean = false) = supportActionBar?.apply {
        setTitle(title)
        setDisplayHomeAsUpEnabled(back)
    }

    fun setupAppBar(title: String, back: Boolean = false) = supportActionBar?.apply {
        setTitle(title)
        setDisplayHomeAsUpEnabled(back)
    }

    fun openPage(page: Pages) {
        goToPage(PageNavigationItem(page), TransitionBundle(TransitionAnimation.ENTER_FROM_RIGHT))
    }

    protected fun addOrReplaceFragment(fragment: Fragment, id: Int, tag: String) {
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction.add(id, fragment, tag)
        } else {
            fragmentTransaction.replace(id, fragment, tag)
        }
        fragmentTransaction.commit()
    }

    protected fun setWindowFlag(bits: Int, on: Boolean) {
        val win=window
        val winParams=win.attributes
        if (on) {
            winParams.flags=winParams.flags or bits
        } else {
            winParams.flags=winParams.flags and bits.inv()
        }
        win.attributes=winParams
    }


    protected fun goToPage(page: PageNavigationItem) {
        navigator.goToPage(page)
    }

    protected fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        navigator.goToPage(page, transitionBundle)
    }

    protected fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        navigator.goToPageForResult(page, transitionBundle)
    }

    protected fun back(): Boolean {
        if (!navigator.back()) {
            onBackPressed()
        }
        return true
    }

    protected fun reset() {
        navigator.reset()
    }

    open fun isDebugEnabled(): Boolean=
        DEBUG_ENABLED

    private fun createDaggerDependencies() {
        injectDependency((application as MovaIoApp).getViewModelComponent())
    }
}