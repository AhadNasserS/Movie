package com.example.movie.screens.OnBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.movie.R
import com.example.movie.navigation.Scrrens
import com.example.movie.navigation.popUpToTop
import com.example.movie.ui.theme.DarkGreenBlue
import com.example.movie.ui.theme.LightYellow

//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun OnBoardingScreen(navController: NavHostController) {
//    val pagerState = rememberPagerState {3}
//    var contentPaddingValues by remember { mutableStateOf(PaddingValues(end = 70.dp)) }
//
//    LaunchedEffect(pagerState.currentPage ) {
//        contentPaddingValues = if (pagerState.currentPage == 0) PaddingValues(end = 70.dp) else PaddingValues(start = 70.dp)
//    }
//
//    HorizontalPager(state = pagerState,
//        contentPadding = contentPaddingValues,) {
//
//    }
//}

//
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun OnBoardingScreen(onBoardingViewModel: OnBoardingViewModel,navController: NavHostController) {
//    val onBoardingCompleted by onBoardingViewModel.onBoardingCompleted.collectAsState()
//    if (onBoardingCompleted) {
//        navController.navigate(Scrrens.mainScreen.rout){
//            popUpToTop(navController)
//        }
//    } else {
//        val pagerState = rememberPagerState { 3 }
//        HorizontalPager(
//            state = pagerState,
//            modifier = Modifier.fillMaxSize(),
//            pageSize = PageSize.Fill,
//
//            ) {
//            when (pagerState.currentPage) {
//                0 -> FirstScren(navController)
//                1 -> SecondScren(navController)
//                2 -> ThirdScren(navController)
//            {
//                onBoardingViewModel.saveOnBoardingState((true))
//            }
//            }
//        }
//
//        Row(
//            Modifier
//                .wrapContentHeight()
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(pagerState.pageCount) { iteration ->
//                val color =
//                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer
//                Box(
//                    modifier = Modifier
//                        .padding(2.dp)
//                        .clip(CircleShape)
//                        .background(color)
//                        .size(16.dp)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun FirstScren(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
//            .fillMaxSize()
//    ) {
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.4f)
//                .paint(
//                    painterResource(id = R.drawable.movie_night_amico),
//                    contentScale = ContentScale.FillBounds,
//                    sizeToIntrinsics = false
//                )
//                .padding(20.dp)
//        )
//
//        Text(text = "Welcome to CineSpectra!",
//            modifier = Modifier.fillMaxSize(),
//            color = Color.Black,
//            fontSize = 20.sp,
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Bold)
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .rotate(-90f)
//                .fillMaxSize()
//                .align(Alignment.End)
//                .paint(painterResource(id = R.drawable.brutalist_10))
//                //.padding(horizontal = 80.dp),
//
//
//        )
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//
//        onClick = {
//            navController.navigate(Scrrens.mainScreen.rout)
//            }) {
//            Text(text = "Next")
//        }
//    }
//
//}
//
//@Composable
//fun SecondScren(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
//            .fillMaxSize()
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.4f)
//                .paint(
//                    painterResource(id = R.drawable.home_cinema_amico__1_),
//                    contentScale = ContentScale.FillBounds,
//                    sizeToIntrinsics = false
//                )
//                .padding(12.dp)
//        )
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .rotate(-90f)
//                .fillMaxSize()
//                .align(Alignment.End)
//                .paint(painterResource(id = R.drawable.brutalist_10))
//
//        )
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//                navController.navigate(Scrrens.mainScreen.rout)
//            }) {
//            Text(text = "Next")
//        }
//    }
//
//}
//
//@Composable
//fun ThirdScren(navController: NavHostController, onFinishClick:() -> Unit) {
//
//    Column(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(30.dp),
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.4f)
//                .paint(
//                    painterResource(id = R.drawable.horror_movie_amico),
//                    contentScale = ContentScale.FillBounds,
//                    sizeToIntrinsics = false
//                )
//        )
//
//        Row(verticalAlignment = Alignment.Bottom,
//            modifier
//            = Modifier.fillMaxSize()) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .align(Alignment.Bottom)
//                , contentAlignment = Alignment.Center
//            )
//            {
//
//                Image(
//                    painter = painterResource(id = R.drawable.brutalist_10),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .rotate(-90f)
//                        .fillMaxSize()
//
//                )
//                Button(
//                    modifier = Modifier.fillMaxWidth(),
//                            onClick = { navController.navigate(Scrrens.mainScreen.rout){
//                                popUpToTop(navController)
//                            }
//                            onFinishClick.invoke()}) {
//                    Text(text = "Finish")
//                }
//
//                }
//            }
//        }
//
//    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    val onBoardingCompleted by onBoardingViewModel.onBoardingCompleted.collectAsState()

    if (onBoardingCompleted) {
        navController.navigate(Scrrens.PopularMoviesScreen.rout){
            popUpToTop(navController)
        }
    } else {
        val pagerState = rememberPagerState(
            0,
            pageCount = {
                3
            })
        HorizontalPager(state = pagerState) { page ->
            Column {
                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(DarkGreenBlue)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    (0..2).forEach { index ->
                        val color =
                            if (pagerState.currentPage == index) LightYellow else Color.White
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(color, CircleShape)
                                .size(10.dp)
                        )
                    }
                }
                when (page) {
                    0 -> {
                        FirstScreen()
                    }

                    1 -> {
                        SecondScreen()
                    }

                    2 -> {
                        ThirdScreen(navController) {
                            onBoardingViewModel.saveOnBoardingState(true)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun FirstScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreenBlue),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brutalist_10),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.movie_night_amico),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun SecondScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreenBlue),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brutalist_10),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.home_cinema_amico__1_),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun ThirdScreen(navController: NavHostController, onFinishClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGreenBlue),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brutalist_10),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.horror_movie_amico),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Row(modifier = Modifier
                          .padding(80.dp)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = true
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Scrrens.PopularMoviesScreen.rout){
                                    popUpToTop(navController)
                                }
                                onFinishClick.invoke()
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White
                            ),
                            modifier = Modifier .padding(90.dp),
                                    shape = RoundedCornerShape(5.dp)
                        ) {
                            Text(text = "Finish", modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}

