package com.rio.movieapi.service

import com.rio.movieapi.dto.MovieDTO
import com.rio.movieapi.utils.ApiSuccess

interface MovieService {
    fun createMovie(movieDTO: MovieDTO): Long
    fun getMovies(): List<MovieDTO>
    fun movieDetail(id: Long): MovieDTO
    fun updateMovie(movieDTO: MovieDTO): Long
}