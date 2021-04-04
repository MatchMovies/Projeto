package com.br.matchmovies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.model.Item
import com.br.matchmovies.model.ListMovies
import com.br.matchmovies.model.Movie
import com.br.matchmovies.model.MovieDetailsList

class HomeMovieAdapter(private val listMoviesList: List<MovieDetailsList>, val callback: (Item) -> Unit) :
        RecyclerView.Adapter<HomeMovieAdapter.MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieAdapter.MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movie_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount() = listMoviesList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        val title = holder.textView
        title.text = listMoviesList[position].name

        val movies = listMoviesList.elementAt(position).items

        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(movies){movie->
                callback(movie)
            }
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }


    inner class MatchViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textView  by lazy { view.findViewById<TextView>(R.id.tv_title_movie_list) }
        val recyclerView  by lazy { view.findViewById<RecyclerView>(R.id.rv_movie_list) }
    }
}