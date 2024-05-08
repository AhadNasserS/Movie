package com.example.movie.domain.popular.User

import com.example.movie.data.repository.PopularMovieRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(private val popularMovieRepository: PopularMovieRepository) {
    suspend operator fun invoke(sessionId: String) =
        popularMovieRepository.getUserAccount(sessionId)
}