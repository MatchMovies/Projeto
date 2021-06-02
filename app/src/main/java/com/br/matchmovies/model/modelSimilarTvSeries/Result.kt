package com.br.matchmovies.model.modelSimilarTvSeries

data class Result(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Number,
    val poster_path: String,
    val vote_average: Number,
    val vote_count: Int
)