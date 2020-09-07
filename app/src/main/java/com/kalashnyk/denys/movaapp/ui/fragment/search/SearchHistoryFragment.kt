package com.kalashnyk.denys.movaapp.ui.fragment.search

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.movaapp.R
import com.kalashnyk.denys.movaapp.base.model.BaseCardModel
import com.kalashnyk.denys.movaapp.base.ui.BasePagingFragment
import com.kalashnyk.denys.movaapp.databinding.SearchHistoryDataBinding
import com.kalashnyk.denys.movaapp.di.component.ViewModelComponent
import com.kalashnyk.denys.movaapp.domain.search.SearchHistoryViewModel
import com.kalashnyk.denys.movaapp.ui.activity.main.MainActivity
import com.kalashnyk.denys.movaapp.ui.fragment.search.binding.SearchHistoryActionListener
import com.kalashnyk.denys.movaapp.ui.navigator.model.Pages
import com.kalashnyk.denys.movaapp.usecases.repository.datasource.database.entity.SearchHistoryEntity
import com.kalashnyk.denys.movaapp.utils.error.ErrorParser
import com.kalashnyk.denys.movaapp.utils.logger.Logger
import com.kalashnyk.denys.movaapp.utils.multistate.MultiStateView
import com.kalashnyk.denys.movaapp.utils.settings.FIRST_LIST_POSITION
import com.kalashnyk.denys.movaapp.utils.settings.MIN_LIST_SIZE
import javax.inject.Inject

class SearchHistoryFragment : BasePagingFragment<SearchHistoryDataBinding>(), SearchHistoryActionListener {

    lateinit var viewModel: SearchHistoryViewModel @Inject set

    override fun getLayoutId(): Int = R.layout.fragment_search_history

    override fun getListView(): RecyclerView = viewBinder.multiStateView.listView

    override fun getRefreshView(): SwipeRefreshLayout = viewBinder.swipeRefresh

    override fun getStateView(): MultiStateView = viewBinder.multiStateView.multiState

    override fun setupViewLogic(binder: SearchHistoryDataBinding) {
        observeQuery()
        setupAppBar(R.string.title_search_history)
        setupSwipeRefresh()
        setupSearchView()
    }

    override fun deleteSearchHistoryItem(searchHistory: SearchHistoryEntity) {
        viewModel.deleteById(searchHistory.id)
    }

    override fun showSearchHistoryDetail(query: String) {
        hideKeyboard()
        viewBinder.etSearch.text?.clear()
        getParentActivity().currentSearchQuery = query
        getParentActivity().openPage(Pages.IMAGE_DETAIL)
        viewModel.searchQuery.postValue(null)
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun initListView() {
        getListView().layoutManager = GridLayoutManager(requireContext(), 2)
        getListView().adapter = pagingAdapter.apply {
            setSearchHistoryActionListener(this@SearchHistoryFragment)
            registerAdapterDataObserver(object  : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (positionStart == FIRST_LIST_POSITION && itemCount == MIN_LIST_SIZE) {
                        getListView().scrollToPosition(FIRST_LIST_POSITION)
                    }
                }
            })
        }
        viewModel.getRefreshing().let {
            viewBinder.refreshing = it
        }
        viewModel.isLoading().let {
            viewBinder.loading = it
        }
    }

    override fun initObserver() {
        viewModel.initLiveData( this)
        viewModel.getPagedList().observe(viewLifecycleOwner, Observer { onItemsLoaded(it) })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            showSnack(ErrorParser(requireContext()).parse(it))
        })
    }

    override fun removeObserver() {
        viewModel.getPagedList().removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
    }

    override fun onItemsLoaded(item: PagedList<BaseCardModel>?) {
        if (item.isNullOrEmpty()) {
            getStateView().viewState = MultiStateView.VIEW_STATE_EMPTY
        } else {
            pagingAdapter.submitList(item)
            getStateView().viewState = MultiStateView.VIEW_STATE_CONTENT
        }
    }

    override fun displayProgress() {
        getStateView().viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun onLoadError(errorMessage: String) {
        getStateView().viewState = MultiStateView.VIEW_STATE_ERROR
        showSnack(errorMessage)
    }

    override fun getParentActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    private fun setupSwipeRefresh() = viewBinder.apply {
        swipeRefresh.setOnRefreshListener {
            viewModel.setRefreshing(true)
            viewModel.fetchData()
        }
    }

    private fun setupSearchView() = viewBinder.apply {
        etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchQuery = etSearch.text.toString().trim()
                viewModel.searchGif(searchQuery)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun observeQuery() = viewModel.searchQuery.observe(viewLifecycleOwner, Observer {
        if (it != null) {
            showSearchHistoryDetail(it)
        }
    })

    companion object {
        @JvmStatic
        fun newInstance() = SearchHistoryFragment()
    }
}
