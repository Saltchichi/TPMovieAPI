package com.monschein.movie.presentation.detail

import android.app.Application
import androidx.lifecycle.*
import com.monschein.movie.data.repository.DatabaseRepository
import com.monschein.movie.data.repository.OmdbRepository
import com.monschein.movie.domain.model.MovieDetail
import com.monschein.movie.domain.repository.BookmarkRepository
import com.monschein.movie.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = OmdbRepository()

    private val repositoryBookmark: BookmarkRepository = DatabaseRepository()

    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState> get() = _state

    fun getMovieDetail(id: String) {
        _state.value = DetailState.LoadingState

        viewModelScope.launch {
            try {
                _state.value = DetailState.SuccessState(repository.getMovieDetail(id))
            } catch (e: Exception) {
                _state.value = DetailState.ErrorState
            }
        }
    }

    fun addBookmark(movieDetail: MovieDetail) {
        viewModelScope.launch {
            repositoryBookmark.addBookmark(getApplication(), movieDetail)
        }
    }
}