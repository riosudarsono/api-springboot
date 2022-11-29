package com.rio.movieapi.repository

import com.rio.movieapi.data.entity.Movie
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MovieRepository: CrudRepository<Movie, Long>{

    @Query("SELECT m FROM Movie as m")
    fun getAllMovies(): List<Movie>
}