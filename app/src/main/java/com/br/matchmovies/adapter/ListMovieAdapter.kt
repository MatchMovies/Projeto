package com.br.matchmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.matchmovies.R
import com.br.matchmovies.model.Match

class ListMovieAdapter(private val matchList : MutableList<Match>) : RecyclerView.Adapter<ListMovieAdapter.MatchViewHolder>(){

 //   private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieAdapter.MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movie_item, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount() = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        val title = holder.textView
        title.text = matchList[position].title

        val movies = matchList[position].movies

        holder.recyclerView.apply {
            layoutManager = LinearLayoutManager(holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieAdapter(movies)
            setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }


    inner class MatchViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textView  by lazy { view.findViewById<TextView>(R.id.textView) }
        val recyclerView  by lazy { view.findViewById<RecyclerView>(R.id.rv_child) }
    }
}