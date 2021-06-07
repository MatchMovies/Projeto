package com.br.matchmovies.model.modelSimilar

data class SimilarMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)