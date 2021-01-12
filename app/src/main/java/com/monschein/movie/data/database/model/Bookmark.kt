package com.monschein.movie.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class Bookmark(
    @PrimaryKey
    val id: String,
    val title: String,
    val poster: String,
)
