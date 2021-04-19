package com.br.matchmovies.repository

import com.br.matchmovies.model.modelConfiguration.MovieConfiguration

object SingletonConfiguration {

    var config: MovieConfiguration? = null

    fun setConfiguration(configuration: MovieConfiguration) {
        config = configuration
    }
}