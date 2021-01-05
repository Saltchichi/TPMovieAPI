package com.monschein.movie.data.api

import com.monschein.movie.data.model.OmdbMovieDetail
import com.monschein.movie.data.model.OmdbSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface OmdbApi {
    companion object {
        const val BASE_URL = "https://omdbapi.com"
        const val API_KEY = "d4ffa006"
    }

    @GET("/")
    suspend fun searchMovie(
        @Query("s") title: String,
        @Query("apiKey") apiKey: String
    ): OmdbSearchResponse

    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") title: String,
        @Query("apiKey") apiKey: String
    ): OmdbMovieDetail
}