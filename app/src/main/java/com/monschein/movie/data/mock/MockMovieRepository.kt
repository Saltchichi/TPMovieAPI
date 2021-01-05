package com.monschein.movie.data.mock

import com.monschein.movie.domain.model.MovieShort
import com.monschein.movie.domain.repository.MovieRepository

class MockMovieRepository {

    suspend fun searchMovie(search: String): List<MovieShort> {
        return listOf(
            MovieShort(
                "1",
                "Titre 1",
                "https://i.kym-cdn.com/entries/icons/mobile/000/020/147/drake.jpg"
            ),
            MovieShort(
                "2",
                "Titre 2",
                "https://i.kym-cdn.com/entries/icons/mobile/000/020/147/drake.jpg"
            ),
            MovieShort(
                "3",
                "Titre 3",
                "https://i.kym-cdn.com/entries/icons/mobile/000/020/147/drake.jpg"
            ),
            MovieShort(
                "4",
                "Titre 4",
                "https://i.kym-cdn.com/entries/icons/mobile/000/020/147/drake.jpg"
            )
        )
    }
}