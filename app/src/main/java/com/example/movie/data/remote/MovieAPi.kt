package com.example.movie.data.remote

import android.telecom.Call.Details
import com.example.movie.BuildConfig
import com.example.movie.model.DetailsResponse
import com.example.movie.model.SearchResponse
import com.example.movie.model.UserAccount
import com.example.movie.model.UserTokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("3/authentication/token/new")
    suspend fun getUserToken(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Response<UserTokenResponse>

    @POST("3/authentication/session/new")
    suspend fun getSessionId(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("request_token")
        requestToken: String
        ): Response<UserTokenResponse>

    @GET("3/account")
    suspend fun getUserAccount(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id")
        sessionId: String
        ): Response<UserAccount>

}



