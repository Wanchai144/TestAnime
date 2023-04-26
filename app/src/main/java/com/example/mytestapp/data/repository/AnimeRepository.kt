package com.example.mytestapp.data.repository

import com.example.mytestapp.data.source.remote.api.response.AnimeResponse
import com.example.mytestapp.data.source.remote.api.service.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException



interface AnimeRepository{
    fun getAnime(): Flow<AnimeResponse>
}

class AnimeRepositoryImpl(
    private val apiService: APIService
) : AnimeRepository {

    override fun getAnime(): Flow<AnimeResponse> = flow {
        val response = apiService.getAnime()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(body)
            } else {
                throw HttpException(response)
            }
        } else {
            throw HttpException(response)
        }
    }

}

