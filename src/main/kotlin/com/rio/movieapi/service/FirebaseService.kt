package com.rio.movieapi.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.InputStream
import javax.annotation.PostConstruct

@Service
class FirebaseService {

    @PostConstruct
    fun initialize(){
        try {
            val serviceAccount = ClassPathResource("serviceAccountKey.json").inputStream
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
            FirebaseApp.initializeApp(options)
        } catch (e: Exception){
            throw IllegalArgumentException(e.message)
        }

    }
}