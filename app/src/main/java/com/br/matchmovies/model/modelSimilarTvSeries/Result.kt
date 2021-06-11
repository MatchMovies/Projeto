package com.br.matchmovies.model.modelSimilarTvSeries

import com.br.matchmovies.adapter.TypeMatch
import java.io.Serializable

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
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
): TypeMatch, Serializable {
    constructor() : this( "", "", emptyList(),
        0, "", emptyList(), "", "", "",0.0, "",
        0.0,  0
    )

    override fun getType(): Int {
        return TypeMatch.tvShow
    }
}