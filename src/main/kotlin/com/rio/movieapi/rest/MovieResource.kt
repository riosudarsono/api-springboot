package com.rio.movieapi.rest

import com.rio.movieapi.dto.MovieDTO
import com.rio.movieapi.service.MovieService
import com.rio.movieapi.utils.ApiSuccess
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movie")
class MovieResource(
    private val movieService: MovieService
) {

    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO) =
        ResponseEntity(ApiSuccess(movieService.createMovie(movieDTO)), HttpStatus.CREATED)

    @GetMapping
    fun getMovies() = ResponseEntity.ok(ApiSuccess(movieService.getMovies()))

    @GetMapping("/{id}")
    fun movieDetail(@PathVariable id: Long) = ResponseEntity.ok(ApiSuccess(movieService.movieDetail(id)))

    @PutMapping
    fun updateMovie(@RequestBody movieDTO: MovieDTO) =
        ResponseEntity.ok(ApiSuccess(movieService.updateMovie(movieDTO)))

}