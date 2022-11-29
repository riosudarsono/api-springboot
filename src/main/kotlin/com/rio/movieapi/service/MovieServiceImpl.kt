package com.rio.movieapi.service

import com.rio.movieapi.data.dto.MovieDTO
import com.rio.movieapi.data.mapper.MovieMapper
import com.rio.movieapi.repository.MovieRepository
import com.rio.movieapi.utils.ApiSuccess
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : MovieService {

    override fun createMovie(movieDTO: MovieDTO): Long {
        if (movieDTO.id != null)
            throw IllegalArgumentException("ID must be null")
        if (movieDTO.rating == null || movieDTO.name == null)
            throw IllegalArgumentException("Must complete movie object")
        val temp =  movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieMapper.fromEntity(temp).id ?: 0
    }

    override fun getMovies(): List<MovieDTO> {
        val movies = movieRepository.getAllMovies()
        return movies.map {
            movieMapper.fromEntity(it)
        }
    }

    override fun movieDetail(id: Long): MovieDTO {
        val optionalMovie = movieRepository.findById(id)
        val movie = optionalMovie.orElseThrow { IllegalArgumentException("Movie with id $id is not exist") }
        return movieMapper.fromEntity(movie)
    }

    override fun updateMovie(movieDTO: MovieDTO): Long {
        val isExist = movieRepository.existsById(movieDTO.id ?: 0)
        if (!isExist)
            throw IllegalArgumentException("Movie with id ${movieDTO.id} is not exist")
        if (movieDTO.rating == null || movieDTO.name == null)
            throw IllegalArgumentException("Must complete movie object")

        movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieDTO.id ?: 0
    }

    override fun deleteMovie(id: Long): Long {
        val isExist = movieRepository.existsById(id)
        if (!isExist)
            throw IllegalArgumentException("Movie with id $id is not exist")
        movieRepository.deleteById(id)
        return id
    }

}