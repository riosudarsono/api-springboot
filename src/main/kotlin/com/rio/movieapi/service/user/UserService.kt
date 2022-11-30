package com.rio.movieapi.service.user

import com.rio.movieapi.data.dto.LoginDTO
import com.rio.movieapi.data.dto.UserDTO

interface UserService {
    fun googleLogin(data: LoginDTO): UserDTO
}