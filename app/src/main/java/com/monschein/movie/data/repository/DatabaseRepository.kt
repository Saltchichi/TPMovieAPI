package com.monschein.movie.data.repository

import android.content.Context
import com.monschein.movie.data.database.AppDatabase
import com.monschein.movie.data.database.model.Bookmark
import com.monschein.movie.domain.model.MovieDetail
import com.monschein.movie.domain.model.MovieShort
import com.monschein.movie.domain.repository.BookmarkRepository

class DatabaseRepository : BookmarkRepository {

    override suspend fun getBookmark(context: Context): List<MovieShort> {
        return AppDatabase.getInstance(context)?.getBookmarkDao()?.getAllBookmark()?.map {
            it.toMovieShort()
        } ?: emptyList()
    }

    override suspend fun addBookmark(context: Context, movie: MovieDetail) {
        val bookmark = movie.toBookmark()

        AppDatabase.getInstance(context)?.getBookmarkDao()?.addBookmark(bookmark)
    }
}

fun Bookmark.toMovieShort() = MovieShort(id, title, poster)

fun MovieDetail.toBookmark() = Bookmark(id, title, poster)