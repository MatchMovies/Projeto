package com.br.matchmovies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.br.matchmovies.R
import com.br.matchmovies.model.modelDatabase.FavoriteSeries
import com.br.matchmovies.model.modelDatabase.Subject
import com.br.matchmovies.model.modelDatabase.UserSeries
import com.br.matchmovies.model.modelSimilarTvSeries.Result
import com.br.matchmovies.repository.SingletonConfiguration
import com.br.matchmovies.viewmodel.SeriesViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class SeriesFragment : Fragment() {

    private val listSeries = mutableListOf<Result>()
    private lateinit var firebaseAuth: FirebaseAuth
    private var firestoreDb = Firebase.firestore
    private val matchSeriesList = mutableListOf<Result>()

    private val configuration = SingletonConfiguration.config
    var contador = 0


    private val viewModel by lazy {
        activity?.let {
            ViewModelProviders.of(it).get(SeriesViewModel::class.java)
        }
    }

    lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heartMatch = view.findViewById<View>(R.id.button_series_ic_match_check) as Button
        val returButton = view.findViewById<View>(R.id.button_series_ic_match_return) as Button
        val exitButton = view.findViewById<View>(R.id.button_series_ic_close) as Button

        firebaseAuth = FirebaseAuth.getInstance()
        getUserSeries()
        showProgressBar(view)
        showErrorMessage()
        // configMovie()


        viewModel?.getSimilarTvSeries()
        viewModel?.seriesLiveData?.observe(viewLifecycleOwner, Observer { t ->
            t.results.let {
                listSeries.addAll(it)
                setMoviePoster(listSeries[0].poster_path.toString())
                returButton.isEnabled = true
            }
        })

        heartMatch.setOnClickListener {

            poster(listSeries, contador)
            if (!matchSeriesList.contains(listSeries[contador])) {
                matchSeriesList.add(listSeries[contador])
                addUser()
                Toast.makeText(requireContext(), "Match realizado com sucesso", Toast.LENGTH_SHORT)
                    .show()
                returButton.isEnabled = true
            } else {
                Toast.makeText(requireContext(), "Match já foi realizado", Toast.LENGTH_SHORT)
                    .show()
            }
            contador += 1
        }

        exitButton.setOnClickListener {
            contador += 1
            poster(listSeries, contador)
            returButton.isEnabled = true
        }

        returButton.setOnClickListener {
            if (listSeries[0] != listSeries[contador]) {
                poster(listSeries, contador)
                contador -= 1
            }
        }

    }

    private fun poster(lista: List<Result>, contador: Int) {

        setMoviePoster(lista[contador].poster_path.toString())

    }

    private fun showProgressBar(view: View) {
        progressBar = view.findViewById(R.id.progressBar_list_movie)

        viewModel?.loading?.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun showErrorMessage() {
        viewModel?.errorMessage?.observe(this, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setMoviePoster(posterPath: String) {
        val imageMovie = view?.findViewById<ImageView>(R.id.ic_match_movie)
        val imageUrl =
            "${configuration?.images?.base_url}${configuration?.images?.poster_sizes?.get(5)}${posterPath}"
        Picasso.get().load(imageUrl).into(imageMovie)
    }

    private fun addUser() {
        firebaseAuth.currentUser?.let { user ->
            val subject = Subject("Firebase Database")
            val userDb = UserSeries(
                user.email ?: "",
                user.displayName,
                subject,
                FavoriteSeries(matchSeriesList)
            )

            firestoreDb.collection("users")
                .document(user.uid)
                .collection("series")
                .document("matchSeries")
                .set(userDb)
                .addOnSuccessListener {
                    it
                }.addOnFailureListener {
                    it
                }
        }
    }

    private fun getUserSeries() {
        firebaseAuth.currentUser?.let { user ->
            firestoreDb.collection("users")
                .document(user.uid)
                .collection("series")
                .document("matchSeries")
                .get()
                .addOnSuccessListener {
                    val us = it.toObject(UserSeries::class.java)
                    if (us != null) {
                        us.series?.nameSeries?.let { fav -> matchSeriesList.addAll(fav) }
                    }
                }.addOnFailureListener {
                    it
                }
        }
    }


}