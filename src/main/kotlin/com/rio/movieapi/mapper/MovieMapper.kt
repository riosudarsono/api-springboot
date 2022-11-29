package com.rio.movieapi.mapper

import com.rio.movieapi.dto.MovieDTO
import com.rio.movieapi.entity.Movie
import org.springframework.stereotype.Component

@Component
class MovieMapper: Mapper<MovieDTO, Movie> {

    override fun fromEntity(entity: Movie): MovieDTO = MovieDTO(
        entity.id,
        entity.name,
        entity.rating
    )

    override fun toEntity(domain: MovieDTO): Movie = Movie(
        domain.id ?: 0,
        domain.name ?: "",
        domain.rating ?: 0.0
    )
}