package com.br.matchmovies.repository

import com.br.matchmovies.model.*
import com.br.matchmovies.model.modelCast.MovieCredits
import com.br.matchmovies.model.modelConfiguration.MovieConfiguration
import com.br.matchmovies.model.modelDetailsList.MovieDetailsList
import com.br.matchmovies.model.modelGenreList.GenreList
import com.br.matchmovies.model.modelProvider.MovieWatchProviders
import com.br.matchmovies.model.modelRateMovie.RateMovieRequest
import com.br.matchmovies.model.modelRateMovie.RateMovieResponse
import com.br.matchmovies.model.modelTESTE.ListTest
import com.br.matchmovies.model.modelSimilar.SimilarMovies
import com.br.matchmovies.model.modelSimilarTvSeries.SimilarTvSeries
import com.br.matchmovies.model.modelVideoMovie.Trailer
import com.br.matchmovies.network.EndPointApi
import com.br.matchmovies.network.RetrofitInit

class RepositoryApi {

    private var url = "https://api.themoviedb.org/3/"
    private val API_KEY: String = "e12325e26b08a2022c51ffe8e8c6dd23"
    private var service = EndPointApi::class

    private val serviceMovies = RetrofitInit(url).create(service)

    suspend fun getMovieConfiguration(): MovieConfiguration =
        serviceMovies.getResponseMovieConfiguration(API_KEY)


    suspend fun getGuestSession(): GuestSession {
        return serviceMovies.getResponseGuestSession(API_KEY)
    }


        suspend fun getSimilarTvSeries(seriesId: String): SimilarTvSeries {
            return serviceMovies.getResponseTvSeriesSimilar(seriesId, API_KEY, "pt-BR")
        }

        suspend fun getSimilarMovieDetails(movieId: String): SimilarMovies {
            return serviceMovies.getResponseMovieSimilar(movieId, API_KEY, "pt-BR")
        }

        suspend fun getMovieTrailer(movieId: Int): Trailer {
            return serviceMovies.getResponseMovieTrailer(movieId, API_KEY, "pt-BR")
        }

        suspend fun getMovieWatchProviders(movieId: Int): MovieWatchProviders {
            return serviceMovies.getResponseMovieWatchProviders(movieId, API_KEY)
        }

        suspend fun getMovieCredits(movieId: Int): MovieCredits {
            return serviceMovies.getResponseMovieCredits(movieId, API_KEY)
        }

        suspend fun postRateMovie(
            movieId: Int,
            guestSessionId: String,
            rateMovieRequest: RateMovieRequest
        ): RateMovieResponse {
            return serviceMovies.postResponseRateMovie(
                movieId,
                API_KEY,
                guestSessionId,
                rateMovieRequest
            )
        }

        suspend fun getGenreList(): GenreList {
            return serviceMovies.getResponseGenreMovieList(API_KEY, "pt-BR")
        }

        suspend fun getTvShowTrailer(tvId: Int): Trailer {
            return serviceMovies.getResponseTvShowTrailer(tvId, API_KEY, "pt-BR")
        }

        suspend fun getTVShowWatchProviders(tvId: Int): MovieWatchProviders {
            return serviceMovies.getResponseTvShowWatchProviders(tvId, API_KEY)
        }

        suspend fun getTVShowCredits(tvId: Int): MovieCredits {
            return serviceMovies.getResponseTvShowCredits(tvId, API_KEY)
        }

        suspend fun postRateTVShow(
            tvID: Int,
            guestSessionId: String,
            rateMovieRequest: RateMovieRequest
        ): RateMovieResponse {
            return serviceMovies.postResponseRateTvShow(
                tvID,
                API_KEY,
                guestSessionId,
                rateMovieRequest
            )
        }

        suspend fun getGenreTVShowList(): GenreList {
            return serviceMovies.getResponseGenreTvList(API_KEY, "pt-BR")
        }

        //-----------------------------------------------------------------------------//
        suspend fun getMovieDetailsList(listId: String): ListTest {
            return serviceMovies.getResponseMovieDetailsList(listId, API_KEY, "pt-BR")
        }
    }
