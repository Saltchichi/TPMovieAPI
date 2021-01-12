package com.monschein.movie.presentation.bookmark

import com.monschein.movie.domain.model.MovieShort

sealed class BookmarkState {
    class SuccessState(val movies: List<MovieShort>) : BookmarkState()

    object ErrorState : BookmarkState()

    object LoadingState : BookmarkState()
}