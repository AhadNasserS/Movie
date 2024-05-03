package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.screens.OnBoarding.OnBoardingScreen
import com.example.movie.screens.OnBoarding.OnBoardingViewModel
import com.example.movie.screens.popular.PopularMoviesViewModel
import com.example.moviescomposeapp.presentation.screens.popular.PopularMoviesScreen

sealed class Scrrens(val rout: String) {
    object endOfONboardingScreen : Scrrens("EndOfOnBoarding")
    object PopularMoviesScreen : Scrrens("PopularMoviesScreen")
    object OnboardingScreen : Scrrens("OnBoarding")
}




@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val OnBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = OnBoardingViewModel.startDestination)
    {
        composable(Scrrens.OnboardingScreen.rout){
            OnBoardingScreen(OnBoardingViewModel,navController)
        }
        composable(Scrrens.PopularMoviesScreen.rout) {
           val viewModel= hiltViewModel<PopularMoviesViewModel>()
            PopularMoviesScreen(navController , viewModel.popularMoviesState)
        }

    }

}

fun NavOptionsBuilder.popUpToTop(navController: NavHostController){
    popUpTo(navController.currentBackStackEntry?.destination?.route?: return){
        inclusive = true
    }
}

