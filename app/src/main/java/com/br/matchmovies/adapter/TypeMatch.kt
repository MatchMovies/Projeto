package com.br.matchmovies.adapter

interface TypeMatch {

    companion object {

        const val movie = 101
        const val  tvShow = 102
    }

    fun getType(): Int
}