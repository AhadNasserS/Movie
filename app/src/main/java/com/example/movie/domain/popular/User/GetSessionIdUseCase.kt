package com.example.movie.domain.popular.User

import com.example.movie.data.repository.PopularMovieRepository
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(private val popularMovieRepository: PopularMovieRepository) {

    suspend operator fun invoke(requstToken: String)=
        popularMovieRepository.getSessionId(requstToken)
}