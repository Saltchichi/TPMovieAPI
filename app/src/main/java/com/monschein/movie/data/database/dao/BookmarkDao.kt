package com.monschein.movie.data.database.dao

import androidx.room.*
import com.monschein.movie.data.database.model.Bookmark

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getAllBookmark(): List<Bookmark>

    @Query("SELECT * FROM bookmark WHERE id = :id")
    fun getBookmark(id: String): Bookmark

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(bookmark: Bookmark)

    @Delete
    fun deleteBookmark(bookmark: Bookmark)
}