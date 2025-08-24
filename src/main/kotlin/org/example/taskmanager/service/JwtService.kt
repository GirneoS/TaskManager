package org.example.taskmanager.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey


@Service
class JwtService {
    private val phrase = "with-this-phrase-encoding-all-jwts"
    private val key: SecretKey = Keys.hmacShaKeyFor(phrase.toByteArray())
    private val expirationTime = 864_000_000

    fun generateToken(email: String): String{
        val now = Date()
        val expirationDate = Date(now.time + expirationTime)

        return Jwts.builder()
            .subject(email)
            .issuedAt(now)
            .signWith(key)
            .expiration(expirationDate)
            .compact()
    }

    fun getEmailFromToken(token: String): String {
        val claims: Jws<Claims>  = Jwts.parser()
            .decryptWith(key)
            .build()
            .parseSignedClaims(token)
        val body = claims.payload
        return body.subject
    }
}