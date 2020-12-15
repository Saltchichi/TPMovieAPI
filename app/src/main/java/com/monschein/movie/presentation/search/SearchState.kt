package com.monschein.movie.presentation.search

import com.monschein.movie.domain.model.MovieShort

sealed class SearchState {
    class SuccessState(val movies: List<MovieShort>) : SearchState()

    object ErrorState : SearchState()

    object LoadingState : SearchState()
}