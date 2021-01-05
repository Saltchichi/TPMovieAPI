package com.monschein.movie.data.model

import com.google.gson.annotations.SerializedName

data class OmdbMovieDetail(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbId")
    val imdbId: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Plot")
    val description: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Metascore")
    val metascore: String,
)
