package com.br.matchmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.adapter.HomeMovieAdapter
import com.br.matchmovies.model.ListMovies
import com.br.matchmovies.model.Movie

class HomeMovieActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    val recycler by lazy { findViewById<RecyclerView>(R.id.rv_list_of_movie_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_movie)

        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        toolbar.setNavigationOnClickListener { onBackPressed() }

        initRecycler()
    }

    private fun initRecycler() {

        val movieList = getMovieList()

        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = HomeMovieAdapter(movieList)
        recycler.adapter = adapter

    }

    private fun getMovieList(): MutableList<ListMovies> {
        val listMovieList = mutableListOf<ListMovies>()
        listMovieList.add(ListMovies("Terror", getMovie()))
        listMovieList.add(ListMovies("Suspense", getMovie()))
        listMovieList.add(ListMovies("Mistério", getMovie()))
        listMovieList.add(ListMovies("Thriller", getMovie()))

        return listMovieList
    }


    private fun getMovie(): MutableList<Movie> {
        val movieList = mutableListOf<Movie>()
        movieList.add(Movie(
                1,
                "Annabelle",
                "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
                3.0F,
                listOf("Terror", "Mistério", "Thriller"),
                listOf("Ward Horton, Annabelle Wallis, Alfre Woodard"),
                listOf("John R. Leonetti"),
                " 135 min",
                " 2014",
                R.drawable.anabele,
                "kHl6aU30pIo"
        ))
        movieList.add(Movie(
                2,
                "Annabelle",
                "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
                3.0F,
                listOf("Terror", "Mistério", "Thriller"),
                listOf("Ward Horton, Annabelle Wallis, Alfre Woodard"),
                listOf("John R. Leonetti"),
                " 135 min",
                " 2014",
                R.drawable.anabele,
                "kHl6aU30pIo"
        ))
        movieList.add(Movie(
                3,
                "Annabelle",
                "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
                3.0F,
                listOf("Terror", "Mistério", "Thriller"),
                listOf("Ward Horton, Annabelle Wallis, Alfre Woodard"),
                listOf("John R. Leonetti"),
                " 135 min",
                " 2014",
                R.drawable.anabele,
                "kHl6aU30pIo"
        ))
        movieList.add(Movie(
                4,
                "Annabelle",
                "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
                3.0F,
                listOf("Terror", "Mistério", "Thriller"),
                listOf("Ward Horton, Annabelle Wallis, Alfre Woodard"),
                listOf("John R. Leonetti"),
                " 135 min",
                " 2014",
                R.drawable.anabele,
                "kHl6aU30pIo"
        ))
        movieList.add(Movie(
                5,
                "Annabelle",
                "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
                3.0F,
                listOf("Terror", "Mistério", "Thriller"),
                listOf("Ward Horton, Annabelle Wallis, Alfre Woodard"),
                listOf("John R. Leonetti"),
                " 135 min",
                " 2014",
                R.drawable.anabele,
                "kHl6aU30pIo"
        ))
        return movieList
    }
}