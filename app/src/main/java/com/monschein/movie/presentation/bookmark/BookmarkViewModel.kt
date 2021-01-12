package com.monschein.movie.presentation.bookmark

import android.app.Application
import androidx.lifecycle.*
import com.monschein.movie.data.repository.DatabaseRepository
import com.monschein.movie.domain.repository.BookmarkRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookmarkRepository = DatabaseRepository()

    private val _state = MutableLiveData<BookmarkState>()
    val state: LiveData<BookmarkState> get() = _state

    fun getBookmark() {
        _state.value = BookmarkState.LoadingState

        viewModelScope.launch {
            try {
                _state.value = BookmarkState.SuccessState(getApplication())
            } catch (e: Exception) {
                _state.value = BookmarkState.ErrorState
            }
        }
    }
}