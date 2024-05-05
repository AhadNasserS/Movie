package com.example.movie.screens.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movie.domain.popular.GetPopularMovieUseCase
import com.example.movie.model.Results
import com.example.movie.model.SearchResponse
import com.example.movie.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val  getPopularMovieUseCase: GetPopularMovieUseCase
) : ViewModel(){
    var popularMoviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())

    init {
        getArtWorks()
    }
private fun getArtWorks(){
    viewModelScope.launch {
//        when( val response = getPopularMovieUseCase.invoke()){
//            is UIState.Success -> popularMoviesState.value = UIState.Success(response.data)
//            is UIState.Error -> popularMoviesState.value = UIState.Error(response.error)
//            is UIState.Empty -> popularMoviesState.value = UIState.Empty(title = response.title)
//            is UIState.Loading -> popularMoviesState.value = UIState.Loading()
        getPopularMovieUseCase.invoke().distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect{
                popularMoviesState.value = it
        }
    }
  }
}