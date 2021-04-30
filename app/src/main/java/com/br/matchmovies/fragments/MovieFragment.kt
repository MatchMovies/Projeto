package com.br.matchmovies.fragments

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.Serializable
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.adapter.HomeMovieAdapter
import com.br.matchmovies.view.MovieDetailsActivity
import com.br.matchmovies.viewmodel.MoviesViewModel

class MovieFragment : Fragment() {

    private val recycler by lazy { view?.findViewById<RecyclerView>(R.id.rv_list_of_movie_list) }
    private lateinit var viewModel: MoviesViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        configureView()
        initRecycler()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun configureView() {
        recycler?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initRecycler() {

        viewModel.moviesLiveData.observe(this){movieList->

            val adapter = HomeMovieAdapter(movieList){movie->
                navigateToMovieDetails(movie)
            }
            recycler?.adapter = adapter
        }
    }

    private fun navigateToMovieDetails(movie: Movie) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie as Serializable)
        startActivity(intent)
    }

}