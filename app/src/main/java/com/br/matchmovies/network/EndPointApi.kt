package com.br.matchmovies.network

import com.br.matchmovies.model.*
import com.br.matchmovies.model.modelCast.MovieCredits
import com.br.matchmovies.model.modelConfiguration.MovieConfiguration
import com.br.matchmovies.model.modelDetailsList.MovieDetailsList
import com.br.matchmovies.model.modelGenreList.GenreList
import com.br.matchmovies.model.modelMovieDetails.MovieDetails
import com.br.matchmovies.model.modelProvider.MovieWatchProviders
import com.br.matchmovies.model.modelRateMovie.RateMovieRequest
import com.br.matchmovies.model.modelRateMovie.RateMovieResponse
import com.br.matchmovies.model.modelSimilar.SimilarMovies
import com.br.matchmovies.model.modelSimilarTvSeries.SimilarTvSeries
import com.br.matchmovies.model.modelVideoMovie.Trailer
import retrofit2.http.*

interface EndPointApi {



    @GET("tv/{tv_id}/similar")
    suspend fun getResponseTvSeriesSimilar(
        @Path("tv_id") movieId: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : SimilarTvSeries

    @GET("movie/{movie_id}/similar")
    suspend fun getResponseMovieSimilar(
       @Path("movie_id") movieId: String?,
       @Query("api_key") apiKey: String?,
       @Query("language") language: String?
    ) : SimilarMovies

    @GET("configuration")
    suspend fun getResponseMovieConfiguration(
        @Query("api_key") apiKey: String?
    ) : MovieConfiguration

    @GET("authentication/guest_session/new")
    suspend fun getResponseGuestSession(
        @Query("api_key") apiKey: String?
    ) : GuestSession

    @GET("movie/{movie_id}/videos")
    suspend fun getResponseMovieTrailer(
            @Path("movie_id") movieId: Int?,
            @Query("api_key") apiKey: String?,
            @Query("language") language: String?
    ) : Trailer

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getResponseMovieWatchProviders(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?
    ) : MovieWatchProviders

    @GET("movie/{movie_id}/credits")
    suspend fun getResponseMovieCredits(
            @Path("movie_id") movieId: Int?,
            @Query("api_key") apiKey: String?
    ) : MovieCredits

    @Headers("Content-Type: application/json")
    @POST("movie/{movie_id}/rating")
    suspend fun postResponseRateMovie(
            @Path("movie_id") movieId: Int?,
            @Query("api_key") apiKey: String?,
            @Query("guest_session_id") guestSessionId: String?,
            @Body rateMovieRequest: RateMovieRequest
    ) : RateMovieResponse

    @GET("genre/movie/list")
    suspend fun getResponseGenreMovieList(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : GenreList

    //------------------------------------------------------//
    @GET("tv/{tv_id}/videos")
    suspend fun getResponseTvShowTrailer(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : Trailer

    @GET("tv/{tv_id}/watch/providers")
    suspend fun getResponseTvShowWatchProviders(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") apiKey: String?
    ) : MovieWatchProviders

    @GET("tv/{tv_id}/credits")
    suspend fun getResponseTvShowCredits(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") apiKey: String?
    ) : MovieCredits

    @Headers("Content-Type: application/json")
    @POST("tv/{tv_id}/rating")
    suspend fun postResponseRateTvShow(
        @Path("tv_id") tvId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("guest_session_id") guestSessionId: String?,
        @Body rateMovieRequest: RateMovieRequest
    ) : RateMovieResponse

    @GET("genre/tv/list")
    suspend fun getResponseGenreTvList(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ) : GenreList

}