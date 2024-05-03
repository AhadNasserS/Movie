package com.example.movie.domain.popular.onBoarding

import com.example.movie.data.dataStor.MovieAppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetIsSafeFromDataStoreUseCase @Inject constructor(
    private val dataStorse : MovieAppDataStore)
    {
      operator fun invoke() : Flow<Boolean> {
          return dataStorse.readOnBoardingState()

      }
    }