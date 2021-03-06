package com.br.matchmovies.model.modelDetailsList

import com.br.matchmovies.adapter.TypeMatch
import java.io.Serializable


data class Item(
    val adult: Boolean,
    val backdrop_path: Any,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
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
) : Serializable, TypeMatch {

    override fun getType(): Int {
        return TypeMatch.movie
    }
}