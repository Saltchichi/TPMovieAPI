package com.monschein.movie.domain.repository

import com.monschein.movie.domain.model.MovieDetail
import com.monschein.movie.domain.model.MovieShort

interface MovieRepository {
    suspend fun searchMovie(search: String): List<MovieShort>

    suspend fun getMovieDetail(id: String) : MovieDetail
}