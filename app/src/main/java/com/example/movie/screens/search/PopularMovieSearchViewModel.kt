package com.example.movie.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movie.domain.popular.GetPopularMoiveSearchUseCase
import com.example.movie.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieSearchViewModel @Inject constructor(
    private val  getPopularMoiveSearchUseCase: GetPopularMoiveSearchUseCase
) : ViewModel(){
    var popularMoviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())


     fun searchIntMovies(query:String){
        viewModelScope.launch {
            getPopularMoiveSearchUseCase.invoke(query).distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                    popularMoviesState.value = it
                }
        }
    }
}