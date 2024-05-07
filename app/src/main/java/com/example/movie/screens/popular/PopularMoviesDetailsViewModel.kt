package com.example.movie.screens.popular

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movie.domain.popular.GetPopularMovieDetailsUseCase
import com.example.movie.model.DetailsResponse
import com.example.movie.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class PopularMoviesDetailsViewModel @Inject constructor(
    private val getPopularMovieDetailsUseCase: GetPopularMovieDetailsUseCase
): ViewModel(){

    val popularMoviesState : MutableStateFlow<UIState<DetailsResponse>> =
        MutableStateFlow(UIState.Loading())
    fun getDetails(id:Int){
        viewModelScope.launch {
        when( val response = getPopularMovieDetailsUseCase.invoke(id)){
            is UIState.Success -> popularMoviesState.value = UIState.Success(response.data)
            is UIState.Error -> popularMoviesState.value = UIState.Error(response.error)
            is UIState.Empty -> popularMoviesState.value = UIState.Empty(title = response.title)
            is UIState.Loading -> popularMoviesState.value = UIState.Loading()

        }
    }

}
}

