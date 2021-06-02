package com.br.matchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.matchmovies.repository.RepositoryApi
import com.br.matchmovies.repository.SingletonConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {

    val loading = MutableLiveData<Boolean>()
    private val repository = RepositoryApi()

    init {
        getConfiguration()
    }
    private fun getConfiguration() = CoroutineScope(Dispatchers.IO).launch {
        loading.postValue(true)
        try {
            repository.getMovieConfiguration().let { configuration ->
                SingletonConfiguration.setConfiguration(configuration)

            }
        }catch (error: Throwable){

        }finally {
            loading.postValue(false)
        }
    }



}