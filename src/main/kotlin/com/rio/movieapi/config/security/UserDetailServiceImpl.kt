package com.rio.movieapi.config.security

import com.rio.movieapi.data.entity.UserData
import com.rio.movieapi.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.getUserByEmail(username ?: "")
        user ?: throw IllegalArgumentException("User Not Found")
        return UserDetailsImpl().build(user)
    }

    fun checkByToken(token: String?): UserDetails {
        val userData = userRepository.getUserByToken(token ?: "")
        return UserDetailsImpl().build(userData)
    }
}