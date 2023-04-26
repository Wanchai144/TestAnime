package com.example.mytestapp.presentation.feature.anime_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytestapp.databinding.FragmentAnimeDetailBinding
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.domain.model.DataCustomAnime
import com.example.mytestapp.presentation.feature.adapter.AdapterListAnime
import com.example.mytestapp.presentation.feature.base.BaseViewModel.Companion.DATA_CONTEXT_KEY
import com.example.mytestapp.presentation.feature.base.NoInternetState
import com.example.mytestapp.presentation.feature.base.Success
import com.example.mytestapp.presentation.feature.base.ViewState
import com.example.mytestapp.presentation.feature.base.snackbar
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import kotlinx.android.synthetic.main.item_icon.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AnimeDetailFragment : BaseFragment() {
    private val binding by lazy { FragmentAnimeDetailBinding.inflate(layoutInflater) }

    private val data: DataCustomAnime?
        get() = arguments?.getParcelable(DATA_CONTEXT_KEY)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        Glide.with(requireContext())
            .load(data?.image)
            .fitCenter()
            .into(imageView)
        tvDescription.text = data?.title
        tvDetail.text = data?.detail
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}