package com.example.movie.domain.popular


import com.example.movie.data.repository.PopularMovieRepository
import com.example.movie.model.DetailsResponse
import com.example.movie.model.UIState
import javax.inject.Inject

class GetPopularMovieDetailsUseCase @Inject constructor(private val popularMoviesRepository: PopularMovieRepository) {
    suspend operator fun invoke(id:Int): UIState<DetailsResponse> {
        return popularMoviesRepository.getPopularMoviesDetails(id)
    }
}