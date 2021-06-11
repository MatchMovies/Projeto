package com.br.matchmovies.model.modelDatabase

import com.br.matchmovies.model.modelSimilarTvSeries.Result

class FavoriteSeries() {

    var nameSeries: List<Result> = listOf()

    constructor(nameSeries: List<Result>): this(){
        this.nameSeries = nameSeries
    }

}