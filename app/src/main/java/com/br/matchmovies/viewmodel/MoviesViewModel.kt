package com.br.matchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.matchmovies.model.*
import com.br.matchmovies.model.modelDatabase.UserMovies
import com.br.matchmovies.model.modelDatabase.UserSeries
import com.br.matchmovies.repository.RepositoryApi
import com.br.matchmovies.repository.SingletonConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.*

class MoviesViewModel : ViewModel() {


    val moviesLiveData = MutableLiveData<List<MatchMovieList>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val repository = RepositoryApi()
    private var firestoreDb = Firebase.firestore
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    private val movieList = mutableListOf<com.br.matchmovies.model.modelSimilar.Result>()
    private val tvShowList = mutableListOf<com.br.matchmovies.model.modelSimilarTvSeries.Result>()
    private val listGenre = mutableListOf<Int>()


    init {
       // match()
       // test()
       // getUserSeries()
    }

//    private fun getConfiguration() = CoroutineScope(IO).launch {
//        loading.postValue(true)
//        try {
//            repository.getMovieConfiguration().let { configuration ->
//                SingletonConfiguration.setConfiguration(configuration)
//                getMovieDetailsList()
//            }
//        }catch (error: Throwable){
//            loading.postValue(false)
//            handleError(error)
//        }
//    }
//
//    private fun getMovieDetailsList()= CoroutineScope(IO).launch {
//        loading.postValue(true)
//        try {
//            for (list in listTest){
//                repository.getMovieDetailsList(list).let { movieListResponse ->
//                    resultTest.add(movieListResponse)
//                }
//            }
//         //   moviesLiveData.postValue(resultTest)
//            loading.postValue(false)
//
//        }catch (error: Throwable){
//            loading.postValue(false)
//            handleError(error)
//        }
//    }

    private fun handleError(error: Throwable) {
        when(error){
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.code()}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }

    private fun getUserMovies() {
        firebaseAuth.currentUser?.let { user ->
            firestoreDb.collection("users")
                .document(user.uid)
                .collection("movies")
                .document("matchMovies")
                .get()
                .addOnSuccessListener {
                    val us = it.toObject(UserMovies::class.java)
                    if (us != null) {
                        us.movies?.nameMovies?.let { fav -> movieList.addAll(fav) }
                    }
                }.addOnFailureListener {
                    it
                }
        }
    }

    private fun getUserSeries() {
        firebaseAuth.currentUser?.let { user ->
            firestoreDb.collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener {
                    val us = it.toObject(UserSeries::class.java)
                    if (us != null) {
                        us.series?.nameSeries?.let { fav -> tvShowList.addAll(fav) }
                    }
                }.addOnFailureListener {
                    it
                }
        }
    }

    private fun test(){
        movieList.forEach { movie ->
            movie.genre_ids.forEach { genre ->
                if(!listGenre.contains(genre)){
                    listGenre.add(genre)
                }
            }
        }
    }

     fun match(){
        getUserMovies()
        moviesLiveData.postValue(listOf(MatchMovieList("Teste", movieList)))
    }

}