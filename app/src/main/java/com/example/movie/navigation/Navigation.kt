package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apimovie.presentation.screens.search.SearchScreen
import com.example.movie.screens.OnBoarding.OnBoardingScreen
import com.example.movie.screens.OnBoarding.OnBoardingViewModel
import com.example.movie.screens.OnBoarding.Screens
import com.example.movie.screens.search.PopularMovieSearchViewModel
import com.example.movie.screens.details.PopularMoviesDetailsViewModel
import com.example.movie.screens.popular.PopularMoviesViewModel
import com.example.movie.screens.profile.ProfileScreen
import com.example.movie.screens.profile.UserProfileViewModel
import com.example.moviescomposeapp.presentation.screens.detail.MovieDetailsScreen
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

        composable("${Screens.MovieDetails.route}/{id}", arguments = listOf(
            navArgument("id"){
                type= NavType.IntType
            }
        )){
            val viewModel = hiltViewModel<PopularMoviesDetailsViewModel>()
            MovieDetailsScreen(viewModel=viewModel, int = it.arguments?.getInt("id"),)
        }

        composable(Screens.Search.route) {
            val viewModel= hiltViewModel<PopularMovieSearchViewModel>()
            SearchScreen(navController , viewModel.popularMoviesState){
                viewModel.searchIntMovies(it)
            }
        }
        composable(Screens.Profile.route) {
            val viewModel= hiltViewModel<UserProfileViewModel>()
            ProfileScreen(viewModel,navController)
        }

    }

}

fun NavOptionsBuilder.popUpToTop(navController: NavHostController){
    popUpTo(navController.currentBackStackEntry?.destination?.route?: return){
        inclusive = true
        saveState = true
    }
}

