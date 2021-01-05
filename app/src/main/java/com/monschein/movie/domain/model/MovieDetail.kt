package com.monschein.movie.domain.model

data class MovieDetail(
    val id: String,
    val title: String,
    val description: String,
    val year: String,
    val poster: String,
    val director: String,
    val actors: String,
    val metascore: String,
)