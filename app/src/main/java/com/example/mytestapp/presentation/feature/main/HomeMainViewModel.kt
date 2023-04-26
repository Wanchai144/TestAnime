package com.example.mytestapp.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapp.R
import com.example.mytestapp.domain.model.DataCustomAnime
import com.example.mytestapp.domain.usecase.AnimeUseCase
import com.example.mytestapp.presentation.extension.toNetworkException
import com.example.mytestapp.presentation.feature.base.*
import kotlinx.coroutines.flow.catch


class HomeMainViewModel(private val animeUseCase: AnimeUseCase,) : BaseViewModel<Any, ViewEffect>() {

    private var _showAnimeSuccess: MutableLiveData<ViewState<List<DataCustomAnime>>> = MutableLiveData()
    val showAnimeSuccess: LiveData<ViewState<List<DataCustomAnime>>>
        get() = _showAnimeSuccess


    init {
        loadData()
    }


    private fun loadData() = executeUseCase(
        action = {
            animeUseCase.execute()
                .catch { exception ->
                    val networkException = exception.toNetworkException()
                    _showAnimeSuccess.value = Error(networkException)
                }.collect {
                    _showAnimeSuccess.value = Success(it)
                }
        }, noInternetAction = {
            _showAnimeSuccess.value = NoInternetState()
        })


}