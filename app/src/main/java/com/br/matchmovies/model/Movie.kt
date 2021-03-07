package com.br.matchmovies.model

data class Movie (val title: String, val overview: String, val rating: Float, val genres: List<String>,
                  val cast: List<String>, val director: List<String>, val time: String, val releaseDate: String,
                  val imageMovie: Int) {
}