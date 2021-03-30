package com.br.matchmovies.network

import com.br.matchmovies.model.MovieConfiguration
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointApi {

    @GET("configuration")
    suspend fun getMovieConfiguration(
        @Query("apikey") apiKey: String?
    ) : MovieConfiguration
}