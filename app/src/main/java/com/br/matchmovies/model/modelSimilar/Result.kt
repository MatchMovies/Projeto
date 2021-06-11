package com.br.matchmovies.model.modelSimilar

import com.br.matchmovies.adapter.TypeMatch
import java.io.Serializable

data class Result(
    val adult: Boolean,
    val backdrop_path: Any,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): TypeMatch, Serializable
{
    constructor() : this(false, "", emptyList(),
        0, "", "", "", 0.0, "","", "",
        false, 0.0, 0
    )

    override fun getType(): Int {
        return TypeMatch.movie
    }
}