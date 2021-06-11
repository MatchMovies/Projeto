package com.br.matchmovies.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.br.matchmovies.R
import com.br.matchmovies.model.modelDatabase.FavoriteMovies
import com.br.matchmovies.model.modelDatabase.Subject
import com.br.matchmovies.model.modelDatabase.UserMovies
import com.br.matchmovies.model.modelSimilar.Result
import com.br.matchmovies.repository.SingletonConfiguration
import com.br.matchmovies.viewmodel.MovieDetailsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class MovieDetailsActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val imageMovie by lazy { findViewById<ImageView>(R.id.img_movie) }
    private val imageBackgroung by lazy { findViewById<ImageView>(R.id.img_background) }
    private val title by lazy { findViewById<TextView>(R.id.tv_title) }
    private val ratingBar by lazy { findViewById<RatingBar>(R.id.rating) }
    private val voteAverage by lazy { findViewById<TextView>(R.id.tv_vote_average) }
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
    private val textProvider by lazy { findViewById<TextView>(R.id.tv_provider) }
    private val layoutProvider by lazy { findViewById<LinearLayout>(R.id.layout_provider) }
    lateinit var videoId: String
    lateinit var movie: Result
    private val configuration = SingletonConfiguration.config

    private var firestoreDb = Firebase.firestore
    private lateinit var firebaseAuth: FirebaseAuth

    private val movieList = mutableListOf<Result>()

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        firebaseAuth = FirebaseAuth.getInstance()

        configToolbar()
        showErrorMessage()

        val info = intent.extras
        movie = info?.getSerializable("movie") as Result

        viewModel.configMovieID(movie.id)

        initViews()
        showButtonTrailer()
        showWatchProviders()
        configureClicks()

    }

    private fun configToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun showErrorMessage() {
        viewModel.errorMessage.observe(this, {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showButtonTrailer() {

        viewModel.trailerLiveData.observe(this) { idTrailer ->
            if (idTrailer.isNotEmpty()) {
                btnTrailer.visibility = VISIBLE
                videoId = idTrailer
            }
        }
    }

    private fun configureClicks() {

        btnTrailer.setOnClickListener {
            val intent = Intent(this, TrailerActivity::class.java)
            intent.putExtra("videoId", videoId)
            startActivity(intent)
        }

        btnUnMatch.setOnClickListener {
            showDialogUnMatch()
        }

        btnEvaluate.setOnClickListener {
            showDialogRate()
        }

        btnShare.setOnClickListener {
            shareText("Já assistiu a ${movie.title}? https://www.themoviedb.org/movie/${movie.id}")
        }
    }

    private fun showDialogUnMatch() {
        AlertDialog.Builder(this, R.style.DialogTheme)
            .setTitle("Tem certeza?")
            .setMessage("Ao desfazer o match ${title.text} sairá da sua lista de filmes. Deseja continuar?")
            .setPositiveButton("Sim, tenho certeza") { _, _ ->
                getUserMovies()
                onBackPressed()
                Toast.makeText(this, "Match com ${title.text} desfeito!", Toast.LENGTH_LONG).show()
            }.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showDialogRate() {
        val rateLayoutDialog: View = layoutInflater.inflate(R.layout.rate_dialog, null)
        val rating = rateLayoutDialog.findViewById<RatingBar>(R.id.rating_dialog)

        var rateUser = 0.0

        rating.onRatingBarChangeListener = OnRatingBarChangeListener { _, v, _ ->
            rateUser = v.toDouble()
        }

        val builder = AlertDialog.Builder(this, R.style.DialogTheme)
        builder.setTitle("Sua Avaliação: ")
        builder.setView(rateLayoutDialog)
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            if (rateUser != 0.0) {
                viewModel.configRateUser(rateUser)
                Toast.makeText(this, "Sua avaliação foi enviada com sucesso!", Toast.LENGTH_LONG)
                    .show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
        builder.create()
        builder.show()
    }

    private fun shareText(text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initViews() {

        setMoviePoster(movie.poster_path)
        setGenre(movie.genre_ids)
        setMovieCredits()

        if (movie.overview.isNotBlank()) {
            textOverview.text = movie.overview
        } else {
            textOverview.text = "Sinopse indisponível."
        }

        ratingBar.rating = movie.vote_average.toFloat()
        voteAverage.text = movie.vote_average.toString()
        title.text = movie.title
        year.text = movie.release_date.subSequence(0, 4)

    }

    private fun setMovieCredits() {
        val listDirector = mutableListOf<String>()
        viewModel.movieCreditsLiveData.observe(this) { movieCredits ->
            for (it in movieCredits.crew) {
                if (it.job == "Director") {
                    listDirector.add(it.name)
                }
            }
            director.text = builder(listDirector).toString()

            val listCast = mutableListOf<String>()
            for (it in movieCredits.cast) {
                if (it.order < 3) {
                    listCast.add(it.original_name)
                }
            }
            cast.text = builder(listCast).toString()
        }
    }

    private fun setGenre(ids: List<Int>) {
        val genres = mutableListOf<String>()

        viewModel.genresLiveData.observe(this) { genreList ->
            genreList.genres.forEach {
                ids.forEach { idGenreMovie ->
                    if (it.id == idGenreMovie) {
                        genres.add(it.name)
                    }
                }
            }
            genre.text = builder(genres).toString()
        }
    }

    private fun setMoviePoster(posterPath: String) {
        val imageUrl = "${configuration?.images?.base_url}${
            configuration?.images?.poster_sizes?.get(5)}${posterPath}"
        Picasso.get().load(imageUrl).into(imageMovie)

        val imageUrl2 = "${configuration?.images?.base_url}${
            configuration?.images?.backdrop_sizes?.get(0)}${movie.backdrop_path}"

        Picasso.get().load(imageUrl2).into(imageBackgroung)
    }

    private fun showWatchProviders() {

        viewModel.watchProvidersLiveData.observe(this) { movieWatchProviders ->
            try {
                for (it in movieWatchProviders.results.BR.flatrate) {
                    val imageUrlProvider = "${configuration?.images?.base_url}${
                        configuration?.images?.logo_sizes?.get(1)}${it.logo_path}"

                    val newImageView = ImageView(this)

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(8, 8, 8, 8)
                    newImageView.layoutParams = params
                    newImageView.contentDescription = it.provider_name

                    layoutProvider.addView(newImageView)
                    Picasso.get().load(imageUrlProvider).into(newImageView)
                }

            } catch (error: Throwable) {
                layoutProvider.visibility = GONE
                textProvider.visibility = GONE
            }

        }
    }

    private fun builder(list: List<String>): StringBuilder {
        val builder = StringBuilder()
        for (item in list) {
            builder.append(item)
            if (list.last() != item) {
                builder.append(", ")
            }
        }
        return builder
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
                                removeData(fav)
                            }
                        }
                    }
                }.addOnFailureListener {
                    it
                }
        }
    }

    private fun removeData(fav: List<Result>) {
        movieList.addAll(fav)
        movieList.remove(movie)
        updateList()
    }

    private fun updateList() {
        firebaseAuth.currentUser?.let { user ->
            val subject = Subject("Firebase Database")
            val userDb = UserMovies(
                user.email ?: "",
                user.displayName,
                subject,
                FavoriteMovies(movieList)
            )

            firestoreDb.collection("users")
                .document(user.uid)
                .collection("movies")
                .document("matchMovies")
                .set(userDb)
                .addOnSuccessListener {
                    it
                }.addOnFailureListener {
                    it
                }
        }
    }
}