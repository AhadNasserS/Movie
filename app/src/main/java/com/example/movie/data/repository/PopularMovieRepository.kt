package com.example.movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.data.paging.MoviePagingSource
import com.example.movie.data.remote.MovieAPi
import com.example.movie.model.DetailsResponse
import com.example.movie.model.Results
import com.example.movie.model.UIState
import com.example.movie.model.UserAccount
import com.example.movie.model.UserTokenResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

class PopularMovieRepository @Inject constructor(
    val movieAPi: MovieAPi
){

    suspend fun getUserToken() : UIState<UserTokenResponse>{
        try {
            val response= movieAPi.getUserToken()
            if (response.isSuccessful&&response.body()!=null){
                return UIState.Success(response.body())
            } else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getSessionId(requestToken: String) : UIState<UserTokenResponse>{
        try {
            val response= movieAPi.getSessionId(requestToken = requestToken)
            if (response.isSuccessful&&response.body()!=null){
                return UIState.Success(response.body())
            } else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }

    suspend fun getUserAccount(sessionId: String) : UIState<UserAccount>{
        try {
            val response= movieAPi.getUserAccount(sessionId=sessionId)
            if (response.isSuccessful&&response.body()!=null){
                return UIState.Success(response.body())
            } else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }

      fun getPopularMoiveSearch(query: String):Flow<PagingData<Results>>{
         return Pager(
             config = PagingConfig(pageSize = 15 , prefetchDistance = 2),
             pagingSourceFactory= {
                 MoviePagingSource(movieAPi,isSearchEndPoint = true,query)
             }
         ).flow
     }

    suspend fun getPopularMoviesDetails(id:Int) : UIState<DetailsResponse>{
        try {
            val response= movieAPi.getDetails(id)
            if (response.isSuccessful&&response.body()!=null){
                return UIState.Success(response.body())
            } else{
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }
     fun getPopularMovies() :Flow<PagingData<Results>>{
        return Pager(
            config = PagingConfig(pageSize = 15 , prefetchDistance = 2),
            pagingSourceFactory= {
                MoviePagingSource(movieAPi,isSearchEndPoint = false)
            }
        ).flow
    }

}