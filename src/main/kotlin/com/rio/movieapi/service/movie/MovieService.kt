package com.rio.movieapi.service.movie

import com.rio.movieapi.data.dto.MovieDTO

interface MovieService {
    fun createMovie(movieDTO: MovieDTO): Long
    fun getMovies(): List<MovieDTO>
    fun movieDetail(id: Long): MovieDTO
    fun updateMovie(movieDTO: MovieDTO): Long
    fun deleteMovie(id: Long): Long
}