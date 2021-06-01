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
import com.br.matchmovies.model.modelSimilarTvSeries.Result
import com.br.matchmovies.repository.SingletonConfiguration

import com.br.matchmovies.viewmodel.SeriesViewModel
import com.squareup.picasso.Picasso


class SeriesFragment : Fragment() {

    private val listMovie = mutableListOf<Result>()

    private val configuration = SingletonConfiguration.config
    var contador = 0


    private val viewModel by lazy {  activity?.let { ViewModelProviders.of(it).get(SeriesViewModel::class.java)}}

    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgressBar(view)
        showErrorMessage()
        // configMovie()


        viewModel?.getSimilarTvSeries()
        viewModel?.seriesLiveData?.observe(viewLifecycleOwner, Observer { t ->
            t.results.let {
                listMovie.addAll(it)
            }
        })
        val heartMatch = view.findViewById<View>(R.id.button_series_ic_match_check) as Button
        heartMatch.setOnClickListener{
            contador += 1
            poster(listMovie, contador)
        }

    }
    private fun poster(lista : List<Result>, contador : Int){

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
        val imageMovie =  view?.findViewById<ImageView>(R.id.ic_match_movie)
        val imageUrl = "${configuration?.images?.base_url}${configuration?.images?.poster_sizes?.get(5)}${posterPath}"
        Picasso.get().load(imageUrl).into(imageMovie)
    }



}