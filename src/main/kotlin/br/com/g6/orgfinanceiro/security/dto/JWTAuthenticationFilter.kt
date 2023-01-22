package br.com.g6.orgfinanceiro.security.dto

import br.com.g6.orgfinanceiro.security.JWTUtil
import br.com.g6.orgfinanceiro.security.UserDetailsImpl
import com.fasterxml.jackson.databind.ObjectMapper

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter: UsernamePasswordAuthenticationFilter {


    private var jwtUtil: JWTUtil

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil) : super() {
        this.authenticationManager = authenticationManager
        this.jwtUtil = jwtUtil
    }

    // --Recebe na Request as credenciais do usuário e o autentica um UserDetails .
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            //--inputStream : leitura do input
            // -- ObjectMapper : serializar um objeto (converter em formato que possa ser armazenado ou transferido.
            //ver sintaxe linha 32
            val (email, password) = ObjectMapper().readValue(request.inputStream, Credentials::class.java)


            val token = UsernamePasswordAuthenticationToken(email, password)

            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found!")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = jwtUtil.generateToken(username)

        response.addHeader("Autorização:",
            token)
    }
//O attemptAuthentication recebe na Request as credenciais do usuário e o autentica um UserDetails .
// O successfulAuthentication por sua vez, gera o Token e devolve no header para o usuário começar a utilizá-lo
}