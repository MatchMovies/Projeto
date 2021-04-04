package com.br.matchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.matchmovies.R
import com.br.matchmovies.model.ListMovies
import com.br.matchmovies.model.Movie
import com.br.matchmovies.model.MovieConfiguration
import com.br.matchmovies.model.MovieDetailsList
import com.br.matchmovies.repository.RepositoryApi
import com.br.matchmovies.repository.SingletonConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class MoviesViewModel : ViewModel() {


    val moviesLiveData = MutableLiveData<List<MovieDetailsList>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()

    private val listTest = listOf("50941077760ee35e1500000c", "51dcfe13760ee376102ae388", "5399f3e50e0a260c0400030c")
    private val resultTest = mutableListOf<MovieDetailsList>()

    init {
        getConfiguration()
    }

    private fun getConfiguration() = CoroutineScope(IO).launch {
        loading.postValue(true)
        try {
            repository.getMovieConfiguration().let { configuration ->
                SingletonConfiguration.setConfiguration(configuration)
                getMovieDetailsList()
            }
        }catch (error: Throwable){
            loading.postValue(false)
            handleError(error)
        }
    }

    private fun getMovieDetailsList()= CoroutineScope(IO).launch {
        loading.postValue(true)
        try {
            for (list in listTest){
                repository.getMovieDetailsList(list).let { movieListResponse ->
                    resultTest.add(movieListResponse)
                }
            }
            moviesLiveData.postValue(resultTest)
            loading.postValue(false)

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