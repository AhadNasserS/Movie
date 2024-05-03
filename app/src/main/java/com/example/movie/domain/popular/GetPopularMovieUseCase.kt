package com.example.movie.domain.popular

import com.example.movie.data.repository.PopularMovieRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPopularMovieUseCase @Inject constructor(private val popularMoviesRepository: PopularMovieRepository) {
    suspend operator fun invoke() = popularMoviesRepository.getPopularMovies()
}