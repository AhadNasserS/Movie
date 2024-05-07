package com.example.movie.screens.OnBoarding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val  route: String){
    object OnBoarding : Screens("on_boarding")
    object Home : Screens("popular_movie_screen")
    object Search : Screens("search_route")
    object Profile: Screens("profile_route")
    object MovieDetails : Screens( "Movie_details")
}

data class BottonNavigationItems(
    val  label :String= "",
    val icon : ImageVector = Icons.Filled.Home,
    val  route: String= ""
){
    fun bottomNavigationItems(): List<BottonNavigationItems>{
        return  listOf(
            BottonNavigationItems(
                label= " Home ",
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottonNavigationItems(
                label= " Search ",
                icon = Icons.Filled.Search,
                route = Screens.Search.route
            ),
            BottonNavigationItems(
                label= " Profile ",
                icon = Icons.Filled.AccountCircle,
                route = Screens.Profile.route
            )
        )
    }

}

