package com.br.matchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.matchmovies.model.modelCast.MovieCredits
import com.br.matchmovies.model.modelGenreList.GenreList
import com.br.matchmovies.model.modelProvider.MovieWatchProviders
import com.br.matchmovies.model.modelRateMovie.RateMovieRequest
import com.br.matchmovies.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class TvShowDetailsViewModel : ViewModel(){

    val trailerLiveData = MutableLiveData<String>()
    val watchProvidersLiveData = MutableLiveData<MovieWatchProviders>()
    val rateMovieLiveData = MutableLiveData<Int>()
    val movieCreditsLiveData = MutableLiveData<MovieCredits>()
    val genresLiveData = MutableLiveData<GenreList>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()
    var tvId = 0
    var rateUser = 0.0


    fun configMovieID(id: Int){
        tvId = id
        getTrailer()
        getMovieWatchProviders()
        getMovieCredits()
        getGetGenreList()
    }

    fun configRateUser(rate: Double){
        rateUser = rate
        getGuestSession()
    }

    private fun getTrailer()= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getTvShowTrailer(tvId).let { trailer ->
                for (it in trailer.results){
                    if(it.type == "Trailer"){
                        trailerLiveData.postValue(it.key)
                        break
                    }
                }
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun getMovieWatchProviders()= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getTVShowWatchProviders(tvId).let { wp ->
                watchProvidersLiveData.postValue(wp)
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun getMovieCredits()= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getTVShowCredits(tvId).let { mc ->
                movieCreditsLiveData.postValue(mc)
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun getGuestSession()= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getGuestSession().let { gs ->
                if (gs.success){
                    postRateMovie(gs.guest_session_id)
                }
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun postRateMovie(guestSessionID: String)= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            val rateMovie  = RateMovieRequest(rateUser)
            repository.postRateTVShow(tvId, guestSessionID, rateMovie).let { rm ->
                rateMovieLiveData.postValue(rm.status_code)
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun getGetGenreList()= CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getGenreTVShowList().let { genreList ->
                genresLiveData.postValue(genreList)
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }
}