package com.br.matchmovies.model.modelSimilarTvSeries

data class SimilarTvSeries(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)