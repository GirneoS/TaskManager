package org.example.taskmanager.config.security

import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.taskmanager.model.UserEntity
import org.example.taskmanager.repository.UserRepo
import org.example.taskmanager.service.JwtService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(val jwtService: JwtService,
                val userDetailsService: CustomUserDetailsService): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader("Authorization")

        if(jwt == null){
            filterChain.doFilter(request, response)
            return
        }
        if(jwt.length < 7 || jwt.isEmpty()) throw JwtException("your jwt is too short")

        val email = jwtService.getEmailFromToken(jwt)
        val userDetails = userDetailsService.loadUserByUsername(email)

        val authToken = UsernamePasswordAuthenticationToken(userDetails,
            null,
            null)

        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
        //далее ищем пользователя в репо, регаем его в SecurityContext и все

        filterChain.doFilter(request, response);
    }
}