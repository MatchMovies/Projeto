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
    private val btnEvaluate by lazy { findViewById<Button>(R.id.btn_evaluate) }
    private val btnShare by lazy { findViewById<Button>(R.id.btn_share) }
    private val genre by lazy { findViewById<TextView>(R.id.tv_genres) }
    private val director by lazy { findViewById<TextView>(R.id.tv_director) }
    private val cast by lazy { findViewById<TextView>(R.id.tv_cast) }
    private val textOverview by lazy { findViewById<TextView>(R.id.tv_overview) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initViews()

    }

    private fun initViews() {

        val movie = Movie("Annabelle (2014)", "Em Anabelle, John (Ward Horton) encontrou o presente perfeito para sua futura esposa, Mia (Annabelle Wallis): uma rara boneca antiga com um lindo vestido. Mia fica bastante contente com seu presente, porém, sua animação com a boneca não duraria muito tempo.",
                3.0F, "Gênero: Terror", "Elenco:  Ward Horton, Annabelle Wallis, Alfre Woodard","Direção: John R. Leonetti", R.drawable.anabele)

        imageMovie.setImageResource(movie.imageMovie)
        title.text = movie.title
        ratingBar.rating = movie.rating
        textOverview.text = movie.overview
        genre.text = movie.genres
        director.text = movie.director
        cast.text = movie.cast


    }


}