package com.monschein.movie.domain.repository

import android.content.Context
import android.graphics.Movie
import com.monschein.movie.domain.model.MovieDetail
import com.monschein.movie.domain.model.MovieShort

interface BookmarkRepository {
    suspend fun getBookmark(context: Context): List<MovieShort>

    suspend fun addBookmark(context: Context, movieDetail: MovieDetail)
}