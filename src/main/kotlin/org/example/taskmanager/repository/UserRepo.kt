package org.example.taskmanager.repository

import org.example.taskmanager.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity
}