package com.rio.movieapi.repository

import com.rio.movieapi.data.entity.UserData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<UserData, Long> {

    @Query("SELECT u FROM UserData as u WHERE u.token=:token")
    fun getUserByToken(token: String): UserData?

    @Query("SELECT u FROM UserData as u WHERE u.email=:email")
    fun getUserByEmail(email: String): UserData?

//    @Query("INSERT INTO User (uid, name, email, image) VALUES (:uid, :name, :email, :image)")
//    fun saveUser(uid: String, name: String, email: String, image: String)

}