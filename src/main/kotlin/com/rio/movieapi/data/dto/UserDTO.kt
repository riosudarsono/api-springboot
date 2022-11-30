package com.rio.movieapi.data.dto

data class UserDTO(
    val id: Long = 0,
    val uid: String,
    val token: String,
    val name: String,
    val email: String,
    val image: String
)
