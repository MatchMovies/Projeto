package com.br.matchmovies.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.br.matchmovies.R
import com.br.matchmovies.model.Movie



class MovieDetailsActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val imageMovie by lazy { findViewById<ImageView>(R.id.img_movie) }
    private val title by lazy { findViewById<TextView>(R.id.tv_title) }
    private val ratingBar by lazy { findViewById<RatingBar>(R.id.rating) }
    private val btnTrailer by lazy { findViewById<Button>(R.id.btn_trailer) }
    private val btnUnMatch by lazy { findViewById<Button>(R.id.btn_match) }
    private val btnEvaluate by lazy { findViewById<Button>(R.id.btn_evaluate) }
    private val btnShare by lazy { findViewById<Button>(R.id.btn_share) }
    private val genre by lazy { findViewById<TextView>(R.id.tv_genres) }
    private val director by lazy { findViewById<TextView>(R.id.tv_director) }
    private val cast by lazy { findViewById<TextView>(R.id.tv_cast) }
    private val time by lazy { findViewById<TextView>(R.id.tv_time) }
    private val year by lazy { findViewById<TextView>(R.id.tv_year) }
    private val textOverview by lazy { findViewById<TextView>(R.id.tv_overview) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val info = intent.extras
        val movie = info?.getSerializable("movie") as Movie

        initViews(movie)

        btnTrailer.setOnClickListener {
            val intent = Intent(this, TrailerActivity::class.java)
            intent.putExtra("videoId", movie.trailer)
            startActivity(intent)
        }

        btnUnMatch.setOnClickListener {
            ShowDialogUnMatch()
        }

        btnEvaluate.setOnClickListener {
            ShowDialogRate()
        }

        btnShare.setOnClickListener {
            shareText(movie.title)
        }

    }

    private fun ShowDialogUnMatch() {
       AlertDialog.Builder(this, R.style.DialogTheme)
                .setTitle("Tem certeza?")
                .setMessage("Ao desfazer o match ${title.text} sairá da sua lista de filmes. Deseja continuar?")
                .setPositiveButton("Sim, tenho certeza") { _, _ ->
                    onBackPressed()
                    Toast.makeText(this, "Match com ${title.text} desfeito!", Toast.LENGTH_LONG).show()
                }.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }.show()

    }

    private fun ShowDialogRate() {
        val popDialog = AlertDialog.Builder(this, R.style.DialogTheme)
        val linearLayout = LinearLayout(this)
        val rating = RatingBar(this)

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        linearLayout.addView(rating);
        popDialog.setTitle("Sua avaliação: ")
        popDialog.setView(linearLayout)


        rating.onRatingBarChangeListener = OnRatingBarChangeListener { ratingBar, v, b -> println("Rated val:$v") }

        popDialog.setPositiveButton(android.R.string.ok) { dialog, which -> dialog.dismiss() }
                .setNegativeButton("Cancelar") { dialog, id -> dialog.cancel() }

        popDialog.create()
        popDialog.show()
    }

    private fun shareText(text: String){
        val textShare = "Hei, o que você acha de $text?"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textShare)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initViews(movie: Movie) {

        imageMovie.setImageResource(movie.imageMovie)
        title.text = movie.title
        ratingBar.rating = movie.rating
        textOverview.text = movie.overview
        genre.text = builder(movie.genres)
        director.text = builder(movie.director)
        cast.text  = builder(movie.cast)
        time.text = movie.time
        year.text = movie.releaseDate
    }

    private fun builder(list: List<String>) : StringBuilder{
        val builder = StringBuilder()
        for (item in list) {
            builder.append(item)
                if(list.last() != item){
                    builder.append(", ")
                 }
        }
        return builder
    }
}

