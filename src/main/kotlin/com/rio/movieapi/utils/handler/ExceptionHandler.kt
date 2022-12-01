package com.rio.movieapi.utils.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }
    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorizedExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message, HttpStatus.UNAUTHORIZED)
        return ResponseEntity(error, HttpStatus.UNAUTHORIZED)
    }

}


