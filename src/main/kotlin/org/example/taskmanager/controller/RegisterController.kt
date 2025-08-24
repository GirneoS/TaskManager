package org.example.taskmanager.controller

import org.example.taskmanager.model.AuthRequest
import org.example.taskmanager.model.AuthResponse
import org.example.taskmanager.service.JwtService
import org.example.taskmanager.service.RegisterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/register")
class RegisterController(private val jwtService: JwtService,
                         private val registerService: RegisterService) {
    @PostMapping
    fun register(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        registerService.registerUser(authRequest)
        val jwt = jwtService.generateToken(authRequest.email)

        return ResponseEntity.ok(AuthResponse(jwt))
    }
}