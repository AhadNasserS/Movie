package com.example.movie.screens.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.popular.GetPopularMovieUseCase
import com.example.movie.model.SearchResponse
import com.example.movie.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val  getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel(){
    var popularMoviesState: MutableState<UIState<SearchResponse>> = mutableStateOf(UIState.Loading())

    init {
        getArtWorks()
    }
private fun getArtWorks(){
    viewModelScope.launch {
        when( val response = getPopularMovieUseCase.invoke()){
            is UIState.Success -> popularMoviesState.value = UIState.Success(response.data)
            is UIState.Error -> popularMoviesState.value = UIState.Error(response.error)
            is UIState.Empty -> popularMoviesState.value = UIState.Empty(title = response.title)
            is UIState.Loading -> popularMoviesState.value = UIState.Loading()
        }
    }
}
}