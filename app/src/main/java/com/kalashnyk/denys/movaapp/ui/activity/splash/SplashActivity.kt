package com.kalashnyk.denys.movaapp.ui.activity.splash

import android.os.Handler
import com.kalashnyk.denys.movaapp.R

import com.kalashnyk.denys.movaapp.base.ui.BaseActivity
import com.kalashnyk.denys.movaapp.databinding.SplashBinding
import com.kalashnyk.denys.movaapp.di.component.ViewModelComponent
import com.kalashnyk.denys.movaapp.ui.navigator.model.Pages
import com.kalashnyk.denys.movaapp.utils.settings.DELAY_3000

class SplashActivity : BaseActivity<SplashBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun setupViewLogic(binder: SplashBinding) {
        Handler().postDelayed({
            navigator.openMainScreen(Pages.SEARCH_HISTORY)
            finish()
        }, DELAY_3000)
    }
}
