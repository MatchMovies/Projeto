package com.br.matchmovies.fragments

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.Serializable
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.adapter.HomeMovieAdapter
import com.br.matchmovies.model.modelDetailsList.Item
import com.br.matchmovies.view.MovieDetailsActivity
import com.br.matchmovies.viewmodel.MoviesViewModel

class MovieFragment : Fragment() {

    private val recycler by lazy { view?.findViewById<RecyclerView>(R.id.rv_list_of_movie_list) }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MoviesViewModel::class.java)}

    lateinit var progressBar : ProgressBar


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressBar(view)
        showErrorMessage()
        initRecycler()
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

    private fun initRecycler() {
        viewModel.moviesLiveData.observe(this, Observer { movieList->
            val adapter = HomeMovieAdapter(movieList){movie->
                navigateToMovieDetails(movie)
            }
            recycler?.adapter = adapter
            recycler?.layoutManager = LinearLayoutManager(requireContext())
        })
    }

    private fun navigateToMovieDetails(item: Item) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("movie", item as Serializable)
        startActivity(intent)
    }

}