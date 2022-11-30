package com.rio.movieapi.utils.handler

import org.springframework.http.HttpStatus

data class ApiSuccess<T>(
    val data: T,
    val message: String = "Success",
    val status: HttpStatus = HttpStatus.OK,
    val code: Int = status.value()
)
