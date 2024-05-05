package com.example.movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.data.paging.MoviePagingSource
import com.example.movie.data.remote.MovieAPi
import com.example.movie.model.Results
import com.example.movie.model.SearchResponse
import com.example.movie.model.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(
    val movieAPi: MovieAPi
){
//    suspend fun getPopularMovies() : UIState<SearchResponse>{
//        try {
//            val response= movieAPi.getUpcoming()
//            if (response.isSuccessful&&response.body()!=null){
//                return UIState.Success(response.body())
//            } else{
//                return UIState.Empty(message = response.message().toString())
//            }
//        } catch (e: Exception){
//            return UIState.Error(e.message.toString())
//        }
//    }
     fun getPopularMovies() :Flow<PagingData<Results>>{
        return Pager(
            config = PagingConfig(pageSize = 15 , prefetchDistance = 2),
            pagingSourceFactory= {
                MoviePagingSource(movieAPi)
            }
        ).flow
    }
}