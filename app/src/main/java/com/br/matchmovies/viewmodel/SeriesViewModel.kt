package com.br.matchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.matchmovies.model.modelSimilarTvSeries.SimilarTvSeries
import com.br.matchmovies.repository.RepositoryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class SeriesViewModel : ViewModel() {

    val seriesLiveData = MutableLiveData<SimilarTvSeries>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()

    init {
        getSimilarTvSeries()
    }

    fun getSimilarTvSeries() = CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getSimilarTvSeries("1405").let { seriesSimilarTvResponse ->
                seriesLiveData.postValue(seriesSimilarTvResponse)
            }

        }catch (error: Throwable){

            handleError(error)
        }finally {
            loading.postValue(false)
        }
    }

    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }

}