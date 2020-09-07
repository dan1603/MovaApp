package com.kalashnyk.denys.movaapp.ui.navigator

import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kalashnyk.denys.movaapp.R
import com.kalashnyk.denys.movaapp.base.ui.BaseFragment
import com.kalashnyk.denys.movaapp.ui.fragment.detail.ImageDetailFragment
import com.kalashnyk.denys.movaapp.ui.fragment.search.SearchHistoryFragment
import com.kalashnyk.denys.movaapp.ui.navigator.model.*

interface FragmentNavigator {

    fun goToPage(page: PageNavigationItem)

    fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle)

    fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle)

    fun back(): Boolean

    fun reset()
}

class FragmentNavigatorImpl(private val fm: FragmentManager) :
    FragmentNavigator {
    private var pagesStack = ArrayList<Pages>()

    companion object {
        private const val CONTENT_ID = R.id.content_view_group
        private const val FRAGMENT_TAG = "fragment_tag"
    }

    override fun goToPage(page: PageNavigationItem) {
        this.goToPage(page, TransitionBundle(), null)
    }

    override fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        val currentFragment = fm.findFragmentByTag(FRAGMENT_TAG)
        goToPage(page, transitionBundle, currentFragment as ResultListener)
    }

    override fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        goToPage(page, transitionBundle, null)
    }

    @Suppress("ComplexMethod")
    fun goToPage(
        page: PageNavigationItem,
        transitionBundle: TransitionBundle,
        resultListener: ResultListener?
    ) {
        when (page.destination) {
            Pages.SEARCH_HISTORY -> handleRoutFragment(SearchHistoryFragment.newInstance(), page, transitionBundle, resultListener)
            Pages.IMAGE_DETAIL -> handleRoutFragment(ImageDetailFragment.newInstance(), page, transitionBundle, resultListener)
            else -> back()
        }
    }

    override fun back(): Boolean {
        return if (pagesStack.size > 1) {
            fm.popBackStack()
            pagesStack.remove(pagesStack.last())
            true
        } else {
            false
        }
    }

    override fun reset() {
        while (pagesStack.size > 1) {
            fm.popBackStackImmediate()
        }
        (fm.fragments.last() as? BaseFragment<*>)?.reset()
    }

    private fun show(dialog: DialogFragment) {
        dialog.show(fm, "modal")
    }

    private fun handleRoutFragment(
        fragment: Fragment,
        page: PageNavigationItem,
        transitionBundle: TransitionBundle,
        resultListener: ResultListener?
    ) {
        if (checkPagesByPrevies(page)) {
            back()
        } else {
            pagesStack.add(page.destination)
            addOrReplaceFragment(fragment, transitionBundle)
        }
    }

    @Suppress("ComplexMethod")
    private fun addOrReplaceFragment(fragment: Fragment, transitionBundle: TransitionBundle) {
        val existingFragment = fm.findFragmentByTag(FRAGMENT_TAG)
        val transaction = fm.beginTransaction()

        if (pagesStack.size > 1) {
            addCustomAnimations(transaction, transitionBundle)
        }

        transitionBundle.views.forEach {
            val transitionName = ViewCompat.getTransitionName(it)
            transaction.addSharedElement(it, transitionName!!)
        }

        if (existingFragment == null) {
            transaction.add(
                CONTENT_ID, fragment,
                FRAGMENT_TAG
            )
        } else {
            transaction.replace(
                CONTENT_ID, fragment,
                FRAGMENT_TAG
            )
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    private fun addCustomAnimations(
        transaction: FragmentTransaction,
        transitionBundle: TransitionBundle
    ) {
        when (transitionBundle.animation) {
            TransitionAnimation.SLIDE_IN_FROM_RIGHT -> transaction.setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right,
                R.animator.pop_out_right,
                R.animator.pop_in_left
            )
            TransitionAnimation.SLIDE_UP_FROM_BOTTOM -> transaction.setCustomAnimations(
                R.anim.slide_up_bottom,
                R.anim.zoom_out,
                R.anim.fade_in,
                R.anim.slide_out_bottom
            )
            TransitionAnimation.ENTER_FROM_RIGHT -> transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )

            TransitionAnimation.ENTER_FROM_LEFT -> transaction.setCustomAnimations(
                R.animator.pop_out_right,
                R.animator.pop_in_left,
                R.animator.slide_in_left,
                R.animator.slide_out_right
            )
            TransitionAnimation.FADE_IN -> transaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
            )
            TransitionAnimation.NONE -> {
            }
            TransitionAnimation.SCALE_UP_FROM_VIEW -> {
            }
        }
    }

    private fun checkPagesByPrevies(page: PageNavigationItem): Boolean {
        return pagesStack.size >= 2 &&
                pagesStack.lastIndexOf(page.destination).inc() ==
                pagesStack.lastIndexOf(pagesStack.last())
    }
}