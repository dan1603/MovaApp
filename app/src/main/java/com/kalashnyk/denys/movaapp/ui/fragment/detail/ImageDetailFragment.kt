package com.kalashnyk.denys.movaapp.ui.fragment.detail

import androidx.lifecycle.Observer
import com.kalashnyk.denys.movaapp.R
import com.kalashnyk.denys.movaapp.base.ui.BaseFragment
import com.kalashnyk.denys.movaapp.databinding.ImageDetailBinding
import com.kalashnyk.denys.movaapp.di.component.ViewModelComponent
import com.kalashnyk.denys.movaapp.domain.search.SearchHistoryViewModel
import com.kalashnyk.denys.movaapp.utils.extensions.gone
import com.kalashnyk.denys.movaapp.utils.extensions.load
import com.kalashnyk.denys.movaapp.utils.extensions.visible
import javax.inject.Inject

class ImageDetailFragment : BaseFragment<ImageDetailBinding>() {

    lateinit var viewModel: SearchHistoryViewModel @Inject set

    override fun getLayoutId(): Int = R.layout.fragment_image_detail

    override fun setupViewLogic(binder: ImageDetailBinding) {
        setupAppBar(R.string.title_search_performing, true)
        observeImage()
        showQueryResult()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun showQueryResult() = getSearchQuery()?.let {
        if (it.isNotEmpty()) {
            viewModel.getItemByQuery(it)
        } else viewBinder.apply {
            stateLoading.gone()
            stateImage.gone()
            stateNotFound.visible()
            setupAppBar(R.string.error_not_found, true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.searchItem.postValue(null)
    }

    private fun observeImage() = viewModel.searchItem.observe(viewLifecycleOwner, Observer {
        if (it != null) viewBinder.apply {
            stateLoading.gone()
            stateNotFound.gone()
            stateImage.visible()
            ivImage.load(it.imageRemote)
            setupAppBar(it.query, true)
        } else viewBinder.apply {
            stateLoading.gone()
            stateImage.gone()
            stateNotFound.visible()
            setupAppBar(R.string.error_not_found, true)
        }
    })

    companion object {
        @JvmStatic
        fun newInstance() = ImageDetailFragment()
    }
}