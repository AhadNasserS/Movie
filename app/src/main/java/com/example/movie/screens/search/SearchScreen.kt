
package com.example.apimovie.presentation.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.movie.Constant.MOVIE_IMAGE_BASE_URL
import com.example.movie.R
import com.example.movie.model.BackdropSize
import com.example.movie.model.Results
import com.example.movie.screens.OnBoarding.Screens
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun SearchScreen(
    navController: NavHostController,
    popularMoviesState: MutableStateFlow<PagingData<Results>>,
    onSearch: (String) ->Unit
) {
    var text by remember { mutableStateOf("") }

    var moviePagingItems = popularMoviesState.collectAsLazyPagingItems()
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = text,
                onValueChange = {
                    text = it
                    onSearch.invoke(text)
                },
                leadingIcon = { Icon(Icons.Filled.Search, "icon") },
                label = { Text(text = "Search") },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch.invoke(text)
                    }
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.DarkGray,
                    unfocusedContainerColor = Color.Gray
                )
            )
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
                                modifier = Modifier.padding(2.dp)
                                    .clickable {
                                        navController.navigate(Screens.MovieDetails.route + "/${moviePagingItems[index]?.id}")
                                    },
                                contentScale = ContentScale.FillWidth,
                                error = painterResource(R.drawable.no_poster),
                                placeholder = painterResource(R.drawable.no_poster)
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

    }
}



