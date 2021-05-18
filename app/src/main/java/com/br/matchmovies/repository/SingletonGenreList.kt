package com.br.matchmovies.repository

import com.br.matchmovies.model.modelGenreList.GenreList

object SingletonGenreList {

    var gl: GenreList? = null

    fun setGenreList(genreList: GenreList) {
        gl = genreList
    }
}