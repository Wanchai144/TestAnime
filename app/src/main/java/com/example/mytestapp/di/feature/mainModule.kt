package com.example.mytestapp.di.feature

import com.example.mytestapp.data.repository.AnimeRepository
import com.example.mytestapp.data.repository.AnimeRepositoryImpl
import com.example.mytestapp.domain.usecase.AnimeUseCase
import com.example.mytestapp.domain.usecase.AnimeUseCaseImpl
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.viewmodel.share.ShareMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    viewModel {
        HomeMainViewModel(animeUseCase = get())
    }

    factory<AnimeUseCase> {
        AnimeUseCaseImpl(animeRepository = get())
    }


    factory<AnimeRepository> {
        AnimeRepositoryImpl(apiService = get())
    }


    viewModel {
        ShareMainViewModel()
    }


}