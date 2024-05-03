package com.example.movie.screens.OnBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.popular.onBoarding.GetIsSafeFromDataStoreUseCase
import com.example.movie.domain.popular.onBoarding.SaveIsFirstTimeInDataStoreUseCase
import com.example.movie.navigation.Scrrens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val  saveIsFirstTimeInDataStoreUseCase: SaveIsFirstTimeInDataStoreUseCase,
    private val getIsSafeFromDataStoreUseCase: GetIsSafeFromDataStoreUseCase
): ViewModel() {
    val onBoardingCompleted = MutableStateFlow(false)
    var startDestination: String = Scrrens.OnboardingScreen.rout

    init {
        getOnBoardingState()
    }

    private fun getOnBoardingState(){
        viewModelScope.launch {
            onBoardingCompleted.value= getIsSafeFromDataStoreUseCase().stateIn(viewModelScope).value
            startDestination =
                if (onBoardingCompleted.value) Scrrens.PopularMoviesScreen.rout else Scrrens.OnboardingScreen.rout
        }
    }

    fun saveOnBoardingState(showOnBoardingPage: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            saveIsFirstTimeInDataStoreUseCase(showTipsPage = showOnBoardingPage)
        }
    }
}