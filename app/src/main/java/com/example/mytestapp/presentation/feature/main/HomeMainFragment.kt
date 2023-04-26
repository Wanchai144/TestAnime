package com.example.mytestapp.presentation.feature.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.domain.model.DataCustomAnime
import com.example.mytestapp.presentation.feature.adapter.AdapterListAnime
import com.example.mytestapp.presentation.feature.base.*
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeMainFragment : BaseFragment(){
    private val binding by lazy { FragmentHomeMainBinding.inflate(layoutInflater) }
    private val viewModel: HomeMainViewModel by viewModel()

    private val adapter: AdapterListAnime by lazy {
        AdapterListAnime()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initRecyclerView()
        callbackAdapter()
    }

    private fun callbackAdapter(){
        adapter.apply {
            onSelectItem = { data ->
                findNavController().navigate(R.id.action_homeMainFragment_to_animeDetailFragment,
                    bundleOf(BaseViewModel.DATA_CONTEXT_KEY to data))
            }
        }
    }

    private fun observeViewModel() = with(viewModel){
        showAnimeSuccess.observe(
            viewLifecycleOwner,
            Observer(this@HomeMainFragment::handleInitialLoadDataViewState)
        )
    }

    private fun handleInitialLoadDataViewState(viewState: ViewState<List<DataCustomAnime>>) {
        when (viewState) {
            is Success -> {
                viewState.data.let {
                    adapter.data = it
                }
            }

            is Error -> {
                snackbar("Data not found", requireView())
            }
            is NoInternetState -> {
                snackbar("Internet not found", requireView())
            }
            else -> {}
        }
    }

    private fun initRecyclerView() = with(binding) {
        rvListAnime.layoutManager =
            LinearLayoutManager(rvListAnime.context, RecyclerView.VERTICAL, false)
        rvListAnime.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}