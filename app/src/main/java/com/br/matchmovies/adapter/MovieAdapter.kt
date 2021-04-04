package com.br.matchmovies.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.model.Item
import com.br.matchmovies.repository.SingletonConfiguration
import com.squareup.picasso.Picasso

class MovieAdapter(private val movieList: List<Item>, val callback: (Item) -> Unit) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]

        val configuration = SingletonConfiguration.config
        var imageUrl = "${configuration?.images?.base_url}${configuration?.images?.poster_sizes?.get(3)}${movie.poster_path}"

        Picasso.get().load(imageUrl).into(holder.imageview)

        holder.itemView.setOnClickListener {
            callback(movie)
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageview: ImageView by lazy { view.findViewById<ImageView>(R.id.iv_movie_poster) }
    }

}
