package br.com.g6.orgfinanceiro.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private val expiration: Long = 3600000
    //expiração: 1 hora

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }
    fun isTokenValid(token: String): Boolean
    {
        val claims = getClaimsToken(token)
        if (claims != null) {
            val username = claims.subject
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true
            }
        }
        return false
    }


    private fun getClaimsToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }

    fun getUserName(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }
    //"Dado um username, tempo de expiração e segredo da aplicação,
// //ele gera um Json Web Token, assinando com o secret e um algoritmo de criptografia."
}