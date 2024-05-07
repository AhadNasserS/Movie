package com.example.movie.domain.popular

import androidx.paging.PagingData
import com.example.movie.data.repository.PopularMovieRepository
import com.example.movie.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@Reusable
class GetPopularMoiveSearchUseCase @Inject constructor(private val popularMoviesRepository: PopularMovieRepository) {
    operator fun invoke(query: String): Flow<PagingData<Results>> {
        return popularMoviesRepository.getPopularMoiveSearch(query)
    }
}