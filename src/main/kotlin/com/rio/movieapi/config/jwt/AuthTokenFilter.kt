package com.rio.movieapi.config.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.rio.movieapi.config.security.UserDetailServiceImpl
import com.rio.movieapi.utils.Constant
import com.rio.movieapi.utils.handler.ApiError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthTokenFilter: OncePerRequestFilter() {

    @Autowired
    private val jwtUtils: JwtUtils? = null

    @Autowired
    private val userDetailsService: UserDetailServiceImpl? = null

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = parseJwt(request)
            val userDetails = userDetailsService?.checkByToken(jwt)
            if (request.servletPath.contains("/auth")){
                filterChain.doFilter(request, response)
            } else {
                if (userDetails?.username == "") {
                    response.resetBuffer()
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    val data = ApiError(Constant.authorization, HttpStatus.UNAUTHORIZED)
                    response.outputStream.print(ObjectMapper().writeValueAsString(data))
                    response.flushBuffer()
                } else {
                    filterChain.doFilter(request, response)
                }
            }
        } catch (e: Exception) {
            Companion.logger.error("Gagal: {}", e.message)
        }
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        return if (StringUtils.hasText(headerAuth)) {
            headerAuth
        } else null
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)
    }


}