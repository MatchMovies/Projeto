package com.br.matchmovies.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.model.modelSimilar.Result
import com.br.matchmovies.repository.SingletonConfiguration
import com.squareup.picasso.Picasso

class MovieAdapter(private val movieList: List<TypeMatch>, val callback: (TypeMatch) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val configuration = SingletonConfiguration.config
        val movie = movieList[position]

        if (movie.getType() == 101) {
            movie as Result
            holder.imageview.contentDescription = movie.title
            val imageUrl =
                "${configuration?.images?.base_url}${configuration?.images?.poster_sizes?.get(3)}${movie.poster_path}"
            Picasso.get().load(imageUrl).into(holder.imageview)
        }
        if (movie.getType() == 102) {
            movie as com.br.matchmovies.model.modelSimilarTvSeries.Result
            holder.imageview.contentDescription = movie.name
            val imageUrl =
                "${configuration?.images?.base_url}${configuration?.images?.poster_sizes?.get(3)}${movie.poster_path}"
            Picasso.get().load(imageUrl).into(holder.imageview)
        }

        holder.itemView.setOnClickListener {
            callback(movie)
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageview: ImageView by lazy { view.findViewById<ImageView>(R.id.iv_movie_poster) }
    }

}
