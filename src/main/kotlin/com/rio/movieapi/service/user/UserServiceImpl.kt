package com.rio.movieapi.service.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.rio.movieapi.data.dto.LoginDTO
import com.rio.movieapi.data.dto.UserDTO
import com.rio.movieapi.data.mapper.UserMapper
import com.rio.movieapi.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserService {

    override fun googleLogin(data: LoginDTO): UserDTO {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        try {
            val decodedToken = auth.verifyIdToken(data.idToken)
            val userRecord = auth.getUser(decodedToken.uid)
            val customToken = auth.createCustomToken(userRecord.uid);
            val token = passwordEncoder().encode(customToken)

            var userExist = userRepository.getUserByEmail(userRecord.email)
            val userNew = UserDTO(uid = userRecord.uid, token = token, name = userRecord.displayName ?: "", email = userRecord.email, image = userRecord.photoUrl ?: "")
            if (userExist == null){
                userExist = userRepository.save(userMapper.toEntity(userNew))
            } else {
                userExist.token = token
                userRepository.save(userExist)
            }
            return userMapper.fromEntity(userExist)
        } catch (e: FirebaseAuthException) {
            throw IllegalArgumentException(e.message)
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}