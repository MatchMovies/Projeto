package com.br.matchmovies.model.modelDatabase

class UserSeries() {

    var email: String = ""
    var name: String = ""
    var subject: Subject? = null
    var series: FavoriteSeries? = null


    constructor(email: String, name: String, subject: Subject, series: FavoriteSeries?) : this() {
        this.email = email
        this.name = name
        this.subject = subject
        this.series = series
    }

}