package com.monschein.movie.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monschein.movie.data.repository.OmdbRepository
import com.monschein.movie.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val repository: MovieRepository = OmdbRepository()

    private val _state = MutableLiveData<DetailState>()
    val state : LiveData<DetailState> get() = _state

    fun getMovieDetail(id: String) {
        _state.value = DetailState.LoadingState

        viewModelScope.launch {
            try{
                _state.value = DetailState.SuccessState(repository.getMovieDetail(id))
            } catch (e: Exception) {
                _state.value = DetailState.ErrorState
            }
        }
    }
}