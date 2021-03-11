package com.br.matchmovies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.model.Movie
import com.br.matchmovies.view.MovieDetailsActivity
import java.io.Serializable

class MovieAdapter(private val movieList: MutableList<Movie>) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val image = holder.imageview
        image.setImageResource(movieList[position].imageMovie)


        holder.itemView.setOnClickListener {
           val intent = Intent(it.context, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movieList[position] as Serializable)
             it.context.startActivity(intent)
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageview: ImageView by lazy { view.findViewById<ImageView>(R.id.imageView) }
    }

}
