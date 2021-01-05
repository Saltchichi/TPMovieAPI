package com.monschein.movie.data.model

import com.google.gson.annotations.SerializedName

data class OmdbSearchResponse(
    @SerializedName("Search")
    val movies: List<OmdbMovieShort>,
    @SerializedName("totalResults")
    val resultNumber: Int,
)
