package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movie.Constant.MOVIE_IMAGE_BASE_URL
import com.example.movie.model.BackdropSize
import com.example.movie.model.SearchResponse
import com.example.movie.model.UIState
import com.example.movie.screens.OnBoarding.OnBoardingScreen
import com.example.movie.screens.popular.PopularMoviesViewModel
import com.example.movie.ui.theme.GreenBlue
import com.example.movie.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                com.example.movie.navigation.NavGraph()

            }
        }
    }
}



//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun MainScreen(navController: NavController,
//               popularMoviesState:MutableState<UIState<SearchResponse>>){
//
//{
//    when (val result = popularMoviesState.value) {
//        is UIState.Success -> {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.primary)
//            ) {
//                Box(modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(0.4f)
//                    .paint(
//                        painterResource(id = R.drawable.background),
//                        contentScale = ContentScale.FillBounds,
//                        sizeToIntrinsics = false
//                    )
//                )
//
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//
//                ) {
//
//                    items(result.data?.results.orEmpty()) {
//
//                        Text(
//                            text = it.title.orEmpty(),
//                            modifier = Modifier.padding(12.dp)
//                        )
//                        AsyncImage(
//                            model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.original}/${it.posterPath}",
//                            contentDescription = "movie image"
//                       )
//
//
//                    }
//
//                }
//            }
//
//        }
//
//        is UIState.Empty -> {}
//        is UIState.Loading -> {}
//        is UIState.Error -> {}
//    }
//
//}
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(GreenBlue)
//    ) {
//        when (val result = popularMoviesState.value) {
//            is UIState.Success -> {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(GreenBlue),
//                    contentAlignment = Alignment.TopCenter
//                )
//                {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(0.4f)
//                            .background(GreenBlue)
//                            .rotate(-90f)
//                            .paint(
//                                // Replace with your image id
//                                painterResource(id = R.drawable.background),
//                                contentScale = ContentScale.FillWidth,
//                                sizeToIntrinsics = false
//                            )
//                    )
//
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxSize()
//                    ) {
//                        items(result.data?.results.orEmpty()) {
//                            GlideImage(
//                                model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.w300}/${it.posterPath}",
//                                contentDescription = "",
//                                modifier = Modifier
//                                    .padding(12.dp)
//                                    .fillParentMaxWidth(),
//                            )
//
//                            Text(
//                                text = it.title.orEmpty(),
//                                modifier = Modifier.padding(12.dp)
//                            )
//                        }
//                    }
//                }
//
//            }
//
//            is UIState.Empty -> {}
//            is UIState.Error -> {}
//            is UIState.Loading -> {}
//
//        }
//    }
//}