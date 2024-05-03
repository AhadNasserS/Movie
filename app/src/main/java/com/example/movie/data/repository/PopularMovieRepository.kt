package com.example.movie.data.repository

import com.example.movie.data.remote.MovieAPi
import com.example.movie.model.SearchResponse
import com.example.movie.model.UIState
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(
    val movieAPi: MovieAPi
){
    suspend fun getPopularMovies() : UIState<SearchResponse>{
        try {
            val response= movieAPi.getUpcoming()
            if (response.isSuccessful&&response.body()!=null){
                return UIState.Success(response.body())
            } else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }
}