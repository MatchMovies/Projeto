package com.br.matchmovies.repository

import com.br.matchmovies.model.MovieConfiguration
import com.br.matchmovies.network.EndPointApi
import com.br.matchmovies.network.RetrofitInit

class RepositoryApi {

    private var url = "https://api.themoviedb.org/3/"
    val API_KEY: String? ="e12325e26b08a2022c51ffe8e8c6dd23"
    private var service = EndPointApi::class

    private val serviceMovies = RetrofitInit(url).create(service)

   suspend fun getMovieService(): MovieConfiguration = serviceMovies.getMovieConfiguration(API_KEY)

}