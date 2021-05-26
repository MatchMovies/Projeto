package com.br.matchmovies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.repository.SingletonConfiguration
import com.br.matchmovies.viewmodel.MatchMoviesViewModel
import com.br.matchmovies.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MatchFragment : Fragment() {

    private val returButton by lazy { view?.findViewById<ImageView>(R.id.ic_match_return) }
    private val exitButton by lazy { view?.findViewById<ImageView>(R.id.ic_match_fundo_3) }
    private val heartButton by lazy { view?.findViewById<ImageView>(R.id.ic_match_check) }
    private val imageMovie by lazy { view?.findViewById<ImageView>(R.id.ic_match_movie) }
    lateinit var imageUrl: String
    private val configuration = SingletonConfiguration.config

    private val viewModel by lazy { ViewModelProviders.of(this).get(MatchMoviesViewModel::class.java)
    }
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_match, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressBar(view)
        showErrorMessage()
        configMovie()


    }

    private fun showProgressBar(view: View) {
        progressBar = view.findViewById(R.id.progressBar_list_movie)

        viewModel.loading.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun showErrorMessage() {
        viewModel.errorMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun configMovie() {
        viewModel.moviesLiveData.observe(this, Observer{
            it?.let {
                setMoviePoster(it.results[0].poster_path.toString())
            }
        })

    }

    private fun setMoviePoster(posterPath: String) {
        val imageUrl = "${configuration?.images?.base_url}${configuration?.images?.poster_sizes?.get(5)}${posterPath}"
        Picasso.get().load(imageUrl).into(imageMovie)
    }


}