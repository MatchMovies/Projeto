package com.br.matchmovies.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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
   // private val progressBar by lazy { view?.findViewById<ProgressBar>(R.id.progressBar_list_movie) }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MoviesViewModel::class.java) }

  //  lateinit var progressBar: ProgressBar
    private var firestoreDb = Firebase.firestore
    private lateinit var firebaseAuth: FirebaseAuth

    var shoudRefreshOnResume = false

    private val matchList = mutableListOf<MatchMovieList>()

    override fun onResume() {
        super.onResume()
        if (shoudRefreshOnResume) {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_wrapper, MovieFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onPause() {
        super.onPause()
        shoudRefreshOnResume = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

    //    showProgressBar()
        getUserMovies()
        getUserSeries()

    }

    private fun configMsg() {
        if (matchList.isEmpty()){
            textNoMatch?.visibility  = VISIBLE
        } else{
            textNoMatch?.visibility  = GONE
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
        initRecycler()
        configMsg()
    }

    private fun initRecycler() {
        val adapter = HomeMovieAdapter(matchList) { Item ->
           navigateToMovieDetails(Item)
        }
        recycler?.adapter = adapter
        recycler?.layoutManager = LinearLayoutManager(requireContext())
        adapter.notifyDataSetChanged()
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

    //    private fun showProgressBar() {
//            if (loading) {
//                progressBar?.visibility  = VISIBLE
//            } else {
//                progressBar?.visibility = GONE
//            }
//    }

}