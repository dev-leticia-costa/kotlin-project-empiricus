package br.com.g6.orgfinanceiro.security

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
    //"Dado um username, tempo de expiração e segredo da aplicação,
// //ele gera um Json Web Token, assinando com o secret e um algoritmo de criptografia."
}