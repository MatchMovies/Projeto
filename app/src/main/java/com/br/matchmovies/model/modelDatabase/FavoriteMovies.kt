package com.br.matchmovies.model.modelDatabase

import com.br.matchmovies.model.modelSimilar.Result

class FavoriteMovies(){
    var nameMovies: List<Result> = listOf()


    constructor(nameMovies: List<Result>): this(){
        this.nameMovies = nameMovies
    }
}