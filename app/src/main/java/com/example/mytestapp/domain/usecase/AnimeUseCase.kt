package com.example.mytestapp.domain.usecase

import com.example.mytestapp.data.repository.AnimeRepository
import com.example.mytestapp.data.source.remote.api.response.AnimeResponse
import com.example.mytestapp.data.source.remote.api.response.Data
import com.example.mytestapp.domain.model.DataCustomAnime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow



interface AnimeUseCase {
    fun execute(): Flow<List<DataCustomAnime>>
}

class AnimeUseCaseImpl(
    private val animeRepository: AnimeRepository,
) : AnimeUseCase {

    override fun execute(): Flow<List<DataCustomAnime>> = flow {
        animeRepository.getAnime().collect { data ->
            emit(data.data.toImageAnime())
        }
    }

    private fun List<Data>.toImageAnime(): List<DataCustomAnime> {
        return this.map {
            DataCustomAnime(
                image =  it.images.jpg.large_image_url,
                title =  it.title,
                detail = it.synopsis
            )
        }
    }
}
