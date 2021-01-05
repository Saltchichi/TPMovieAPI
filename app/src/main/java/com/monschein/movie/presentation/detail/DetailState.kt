package com.monschein.movie.presentation.detail

import com.monschein.movie.domain.model.MovieDetail

sealed class DetailState {
    class SuccessState(val movie:MovieDetail) : DetailState()

    object ErrorState : DetailState()

    object LoadingState : DetailState()
}