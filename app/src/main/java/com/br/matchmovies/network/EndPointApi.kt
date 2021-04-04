package com.br.matchmovies.network

import com.br.matchmovies.model.MovieConfiguration
import com.br.matchmovies.model.MovieDetailsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointApi {

    @GET("configuration")
    suspend fun getResponseMovieConfiguration(
        @Query("api_key") apiKey: String?
    ) : MovieConfiguration

    @GET("list/{list_id}")
    suspend fun getResponseMovieDetailsList(
            @Path("list_id") listId: String?,
            @Query("api_key") apiKey: String?
    ) : MovieDetailsList


}