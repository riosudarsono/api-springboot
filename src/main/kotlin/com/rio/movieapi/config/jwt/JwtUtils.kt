package com.rio.movieapi.config.jwt


import com.rio.movieapi.config.security.UserDetailsImpl
import io.jsonwebtoken.*
import io.jsonwebtoken.security.SignatureException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtils {
    private val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java)

    @Value("\${jwt.refreshExpirationMs}")
    private val refreshJwtExpirationMs = 0

    @Value("\${jwt.expirationMs}")
    private val jwtExpirationMs = 0

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    fun generateJwtToken(authentication: Authentication): String? {
        val principal = authentication.principal as UserDetailsImpl
        return Jwts.builder().setSubject(principal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun generateRefreshJwtToken(authentication: Authentication): String? {
        val principal = authentication.principal as UserDetailsImpl
        return Jwts.builder().setSubject(principal.username)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + refreshJwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserNameFromJwtToken(token: String?): String? {
        return Jwts.parser().setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }
}