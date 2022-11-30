package com.rio.movieapi.rest

import com.rio.movieapi.data.dto.LoginDTO
import com.rio.movieapi.service.user.UserService
import com.rio.movieapi.utils.handler.ApiSuccess
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthResource(
    private val userService: UserService
) {

    @PostMapping("/googleLogin")
    fun loginGoogle(@RequestBody data: LoginDTO) =
        ResponseEntity(ApiSuccess(userService.googleLogin(data)), HttpStatus.OK)
}