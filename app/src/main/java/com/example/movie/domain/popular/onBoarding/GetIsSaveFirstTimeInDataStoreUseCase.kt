package com.example.movie.domain.popular.onBoarding;

import com.example.movie.data.dataStor.MovieAppDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class SaveIsFirstTimeInDataStoreUseCase @Inject constructor(
    private val dataStorse :MovieAppDataStore
){

    suspend operator fun invoke(showTipsPage: Boolean){
        dataStorse.saveOnBoardingState(showTipsPage = showTipsPage)
    }

}
