package com.rio.movieapi.rest

import com.rio.movieapi.data.dto.MovieDTO
import com.rio.movieapi.repository.UserRepository
import com.rio.movieapi.service.movie.MovieService
import com.rio.movieapi.utils.Constant
import com.rio.movieapi.utils.handler.ApiSuccess
import com.rio.movieapi.utils.handler.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movie")
class MovieResource(
    private val userRepository: UserRepository,
    private val movieService: MovieService
) {

    @PostMapping
    fun createMovie(@RequestHeader("Authorization") token: String, @RequestBody movieDTO: MovieDTO) =
        ResponseEntity(ApiSuccess(movieService.createMovie(movieDTO), status = HttpStatus.CREATED), HttpStatus.CREATED)


    @GetMapping
    fun getMovies(@RequestHeader("Authorization") token: String) =
        ResponseEntity.ok(ApiSuccess(movieService.getMovies()))

    @GetMapping("/{id}")
    fun movieDetail(@PathVariable id: Long) = ResponseEntity.ok(ApiSuccess(movieService.movieDetail(id)))

    @PutMapping
    fun updateMovie(@RequestBody movieDTO: MovieDTO) =
        ResponseEntity.ok(ApiSuccess(movieService.updateMovie(movieDTO)))

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: Long) =
        ResponseEntity.ok(ApiSuccess(movieService.deleteMovie(id)))
}