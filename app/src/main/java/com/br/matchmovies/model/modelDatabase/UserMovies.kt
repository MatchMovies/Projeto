package com.br.matchmovies.model.modelDatabase

class UserMovies() {
    var email: String = ""
    var name: String = ""
    var subject: Subject? = null
    var movies: FavoriteMovies? = null

    constructor(email: String, name: String, subject: Subject, movies: FavoriteMovies?) : this() {
        this.email = email
        this.name = name
        this.subject = subject
        this.movies = movies
    }

}