package org.example.taskmanager.config.security

import org.example.taskmanager.model.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(private val userEntity: UserEntity): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return ArrayList()
    }

    override fun getPassword(): String {
        return userEntity.password
    }

    override fun getUsername(): String {
        return userEntity.email
    }

    fun getName(): String{
        return userEntity.username
    }
}