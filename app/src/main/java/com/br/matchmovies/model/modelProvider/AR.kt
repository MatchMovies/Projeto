package com.br.matchmovies.model.modelProvider

data class AR(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)