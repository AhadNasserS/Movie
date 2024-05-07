package com.example.movie.data.remote

import android.telecom.Call.Details
import com.example.movie.BuildConfig
import com.example.movie.model.DetailsResponse
import com.example.movie.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPi {
    @GET("3/movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language: String= "en-US",
        @Query("page")
        page: Int = 1 ,
    ): Response<SearchResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id")
        movie_id: Int ,
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language")
        language: String= "en-US",

    ): Response<DetailsResponse>

    @GET("3/search/multi")
    suspend fun getSearch(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult")
        includeAdult: Boolean = false,
        @Query("query")
        query: String,
        @Query("language")
        language: String= "en-US",
        @Query("page")
        page: Int = 1 ,
    ): Response<SearchResponse>
}



