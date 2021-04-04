package com.br.matchmovies.repository

import android.util.Log
import com.br.matchmovies.model.*
import com.br.matchmovies.network.EndPointApi
import com.br.matchmovies.network.RetrofitInit

class RepositoryApi {

    private var url = "https://api.themoviedb.org/3/"
    private val API_KEY: String = "e12325e26b08a2022c51ffe8e8c6dd23"
    private var service = EndPointApi::class

    private val serviceMovies = RetrofitInit(url).create(service)

    suspend fun getMovieConfiguration(): MovieConfiguration = serviceMovies.getResponseMovieConfiguration(API_KEY)

    suspend fun getMovieDetailsList(listId: String): MovieDetailsList {
        return serviceMovies.getResponseMovieDetailsList(listId, API_KEY)
    }

}