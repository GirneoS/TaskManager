package org.example.taskmanager.controller

import org.example.taskmanager.config.security.CustomUserDetails
import org.example.taskmanager.model.AuthRequest
import org.example.taskmanager.model.AuthResponse
import org.example.taskmanager.service.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val jwtService: JwtService,
                     private val authManager: AuthenticationManager) {
    @PostMapping
    fun auth(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val authentication = authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
        ))

        val userDetails = authentication.principal as CustomUserDetails
        val jwt = jwtService.generateToken(userDetails.username)

        return ResponseEntity.ok(AuthResponse(jwt))
    }
}