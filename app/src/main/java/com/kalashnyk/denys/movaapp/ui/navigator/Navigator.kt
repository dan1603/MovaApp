package com.kalashnyk.denys.movaapp.ui.navigator

import android.content.Intent
import com.kalashnyk.denys.movaapp.base.ui.BaseActivity
import com.kalashnyk.denys.movaapp.ui.activity.main.MainActivity
import com.kalashnyk.denys.movaapp.ui.activity.splash.SplashActivity
import com.kalashnyk.denys.movaapp.ui.navigator.model.PageNavigationItem
import com.kalashnyk.denys.movaapp.ui.navigator.model.Pages
import com.kalashnyk.denys.movaapp.ui.navigator.model.TransitionBundle
import com.kalashnyk.denys.movaapp.utils.settings.MAIN_TYPE_SCREEN

interface Navigator : FragmentNavigator {
    var navigatorSource: BaseActivity<*>
    fun openSplashScreen()
    fun openMainScreen(typeScreen: Pages)
}

class NavigatorImpl(override var navigatorSource: BaseActivity<*>): Navigator {

    private val fragmentNavigator: FragmentNavigator

    init {
        fragmentNavigator = FragmentNavigatorImpl(navigatorSource.supportFragmentManager)
    }

    override fun openSplashScreen() {
        navigatorSource.startActivity(Intent(navigatorSource, SplashActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    override fun openMainScreen(typeScreen: Pages) {
        navigatorSource.startActivity(Intent(navigatorSource, MainActivity::class.java).apply {
            putExtra(MAIN_TYPE_SCREEN, typeScreen)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }

    override fun goToPage(page: PageNavigationItem) {
        fragmentNavigator.goToPage(page)
    }

    override fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        fragmentNavigator.goToPage(page, transitionBundle)
    }

    override fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        fragmentNavigator.goToPageForResult(page, transitionBundle)
    }

    override fun back(): Boolean {
        return fragmentNavigator.back()
    }

    override fun reset() {
        fragmentNavigator.reset()
    }
}