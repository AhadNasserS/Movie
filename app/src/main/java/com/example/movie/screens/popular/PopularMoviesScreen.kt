package com.example.moviescomposeapp.presentation.screens.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.movie.Constant.MOVIE_IMAGE_BASE_URL
import com.example.movie.R
import com.example.movie.model.BackdropSize
import com.example.movie.model.Results
import com.example.movie.model.SearchResponse
import com.example.movie.model.UIState
import com.example.movie.ui.theme.GreenBlue
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PopularMoviesScreen(
    navController: NavHostController,
    popularMoviesState: MutableStateFlow<PagingData<Results>>
) {
    val moviePagingItems = popularMoviesState.collectAsLazyPagingItems()
    Box{
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
           items(moviePagingItems.itemCount){ index ->
               if(moviePagingItems[index]?.adult==false){
                   AsyncImage(model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.original}/${moviePagingItems[index]?.posterPath}"
                       , contentDescription = "",
                       modifier = Modifier.padding(2.dp),
                       contentScale = ContentScale.FillWidth,
                       error = painterResource(R.drawable.background),
                   placeholder = painterResource(R.drawable.background)
                   )
               }
           }
        }
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {

                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = moviePagingItems.loadState.refresh as LoadState.Error
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = error.error.localizedMessage.orEmpty(),
                            modifier = Modifier,
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = moviePagingItems.loadState.append as LoadState.Error
                    Text(
                        text = error.error.localizedMessage.orEmpty(),
                        modifier = Modifier,
                    )
                }
            }
        }
    }

}




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
//                                model = "${MOVIE_IMAGE_BASE_URL}${BackdropSize.original}/${it.posterPath}",
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
