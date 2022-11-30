package com.rio.movieapi.data.mapper

import com.rio.movieapi.data.dto.UserDTO
import com.rio.movieapi.data.entity.UserData
import org.springframework.stereotype.Component

@Component
class UserMapper: Mapper<UserDTO, UserData> {
    override fun fromEntity(entity: UserData): UserDTO = UserDTO(
        entity.id,
        entity.uid,
        entity.token,
        entity.name,
        entity.email,
        entity.image
    )

    override fun toEntity(domain: UserDTO): UserData = UserData(
        domain.id,
        domain.uid,
        domain.token,
        domain.name,
        domain.email,
        domain.image
    )


}