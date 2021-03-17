package com.br.matchmovies.model
import java.io.Serializable

data class Movie (val idMovie: Int, val title: String, val overview: String, val rating: Float, val genres: List<String>,
                  val cast: List<String>, val director: List<String>, val time: String, val releaseDate: String,
                  val imageMovie: Int, val trailer: String) : Serializable {
}