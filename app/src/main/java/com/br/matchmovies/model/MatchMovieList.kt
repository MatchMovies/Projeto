package com.br.matchmovies.model

import com.br.matchmovies.adapter.TypeMatch
import com.br.matchmovies.model.modelSimilar.Result

data class MatchMovieList (val name: String, val items: List<TypeMatch>) {
}