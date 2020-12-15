package com.monschein.movie.domain.repository

import com.monschein.movie.domain.model.MovieShort

interface SearchRepository {
    suspend fun searchMovie(search: String): List<MovieShort>
}