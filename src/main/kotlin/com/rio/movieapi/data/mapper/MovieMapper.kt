package com.rio.movieapi.data.mapper

import com.rio.movieapi.data.dto.MovieDTO
import com.rio.movieapi.data.entity.Movie
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