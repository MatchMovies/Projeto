package com.br.matchmovies.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.adapter.HomeMovieAdapter
import com.br.matchmovies.adapter.TypeMatch
import com.br.matchmovies.model.MatchMovieList
import com.br.matchmovies.model.modelDatabase.UserMovies
import com.br.matchmovies.model.modelDatabase.UserSeries
import com.br.matchmovies.view.MovieDetailsActivity
import com.br.matchmovies.view.TvShowDetailsActivity
import com.br.matchmovies.viewmodel.MoviesViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable

class MovieFragment : Fragment() {

    private val recycler by lazy { view?.findViewById<RecyclerView>(R.id.rv_list_of_movie_list) }
    private val textNoMatch by lazy { view?.findViewById<TextView>(R.id.textNoMatch) }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MoviesViewModel::class.java) }

    lateinit var progressBar: ProgressBar
    private var firestoreDb = Firebase.firestore
    private lateinit var firebaseAuth: FirebaseAuth


    private val matchList = mutableListOf<MatchMovieList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        getUserMovies()
        getUserSeries()
        configMsg()
//        showProgressBar(view)
//        showErrorMessage()
    }

    private fun configMsg() {
        if (matchList.isNotEmpty()){
            textNoMatch?.visibility  = GONE
        } else{
            textNoMatch?.visibility  = VISIBLE
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
                        us.movies?.nameMovies?.let { fav ->
                            if(fav.isNotEmpty()){
                            configData("Filmes", fav)
                            }
                        }
                    }
                }.addOnFailureListener {
                    it
                }
        }
    }

    private fun getUserSeries()  {

        firebaseAuth.currentUser?.let { user ->
            firestoreDb.collection("users")
                .document(user.uid)
                .collection("series")
                .document("matchSeries")
                .get()
                .addOnSuccessListener {
                    val us = it.toObject(UserSeries::class.java)
                    if (us != null) {
                        us.series?.nameSeries?.let { fav ->
                            if(fav.isNotEmpty()){
                                configData("Séries", fav)
                            }
                        }
                    }
                }.addOnFailureListener {
                    it
                }
        }
    }

    private fun configData(name: String, fav: List<TypeMatch>) {

        matchList.add(MatchMovieList(name, fav))
        configMsg()
        initRecycler()

    }

    private fun initRecycler() {

        val adapter = HomeMovieAdapter(matchList) { Item ->
           navigateToMovieDetails(Item)
        }
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun navigateToMovieDetails(item: TypeMatch) {

      if (item.getType() == 101) {

           val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
            intent.putExtra("movie", item as java.io.Serializable)
            startActivity(intent)
       }
        if (item.getType() == 102) {

            val intent = Intent(requireContext(), TvShowDetailsActivity::class.java)
            intent.putExtra("tvshow", item as Serializable)
            startActivity(intent)
        }
    }

    private fun showErrorMessage() {
        viewModel.errorMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showProgressBar(view: View) {
        progressBar = view.findViewById(R.id.progressBar_list_movie)

        viewModel.loading.observe(this, Observer {
            if (it) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        })
    }
}