package com.br.matchmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.br.matchmovies.R
import com.br.matchmovies.model.Movie


class MovieDetailsActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val imageMovie by lazy { findViewById<ImageView>(R.id.img_movie) }
    private val title by lazy { findViewById<TextView>(R.id.tv_title) }
    private val ratingBar by lazy { findViewById<RatingBar>(R.id.rating) }
    private val btnTrailer by lazy { findViewById<Button>(R.id.btn_trailer) }
    private val btnMatch by lazy { findViewById<Button>(R.id.btn_match) }
    private val btnBack by lazy { findViewById<Button>(R.id.btn_back) }
    private val textOverview by lazy { findViewById<TextView>(R.id.tv_overview) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = Movie("Annabelle (2014)", "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
            3.0F, R.drawable.anabele)
        initViews(movie)
    }

    private fun initViews(movie: Movie) {
        imageMovie.setImageResource(movie.imageMovie)
        title.text = movie.title
        ratingBar.rating = movie.rating
        textOverview.text = movie.overview


    }


}