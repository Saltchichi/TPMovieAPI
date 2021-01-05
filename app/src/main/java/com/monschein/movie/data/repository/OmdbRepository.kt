package com.monschein.movie.data.repository

import com.monschein.movie.data.api.OmdbApi
import com.monschein.movie.data.model.OmdbMovieDetail
import com.monschein.movie.data.model.OmdbMovieShort
import com.monschein.movie.domain.model.MovieDetail
import com.monschein.movie.domain.model.MovieShort
import com.monschein.movie.domain.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OmdbRepository : MovieRepository {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(OmdbApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val api = retrofit.create(OmdbApi::class.java)

    override suspend fun searchMovie(search: String): List<MovieShort> {
        return api.searchMovie(search, OmdbApi.API_KEY).movies.map {
            it.toMovieShort()
        }
    }

    override suspend fun getMovieDetail(id: String): MovieDetail {
        return api.getMovieDetail(id, OmdbApi.API_KEY).toMovieDetail()
    }
}

fun OmdbMovieShort.toMovieShort() = MovieShort(this.imdbId, this.title, this.poster)

fun OmdbMovieDetail.toMovieDetail() = MovieDetail(
    this.imdbId,
    this.title,
    this.description,
    this.year,
    this.poster,
    this.director,
    this.actors,
    this.metascore
)