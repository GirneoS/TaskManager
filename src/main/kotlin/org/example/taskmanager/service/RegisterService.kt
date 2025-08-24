package org.example.taskmanager.service

import org.example.taskmanager.model.AuthRequest
import org.example.taskmanager.model.UserEntity
import org.example.taskmanager.repository.UserRepo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterService(private val passwordEncoder: PasswordEncoder, private val userRepo: UserRepo) {
    fun registerUser(authRequest: AuthRequest) {
        val passHash = passwordEncoder.encode(authRequest.password)
        userRepo.save(UserEntity(
            null,
            authRequest.username,
            authRequest.email,
            passHash
        ))
    }
}