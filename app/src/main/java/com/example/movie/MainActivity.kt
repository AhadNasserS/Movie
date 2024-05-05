package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movie.navigation.NavGraph
import com.example.movie.navigation.popUpToTop
import com.example.movie.screens.OnBoarding.BottonNavigationItems
import com.example.movie.screens.OnBoarding.Screens
import com.example.movie.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                val  navController = rememberNavController()
                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val  navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when ( navBackStackEntry?.destination?.route){
                    Screens.OnBoarding.route -> false
                    else -> true
                }
                val navigationSelectedItem = rememberSaveable{
                    mutableIntStateOf(0)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar){
                            NavigationBar {
                                BottomNavigationBar(navigationSelectedItem, navController)
                            }
                       }
                    }
                ) {paddingValues ->
                    Box (modifier = Modifier.padding(paddingValues)){
                        NavGraph(navController)
                    }


                }
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationBar(
    navigationSelectedItem: MutableState<Int>,
    navController: NavHostController
) {
    BottonNavigationItems().bottomNavigationItems()
        .forEachIndexed { index, navigationItem ->
            //iterating all items with their respective indexes
            NavigationBarItem(
                selected = index == navigationSelectedItem.value,
                label = {
                    Text(navigationItem.label)
                },
                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.label
                    )
                },
                onClick = {
                    navigationSelectedItem.value = index
                    navController.navigate(navigationItem.route) {
                        popUpToTop(navController)
                    }
                }
            )
        }
}

