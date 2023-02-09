package br.com.g6.orgfinanceiro.security.dto

import br.com.g6.orgfinanceiro.security.JWTUtil
import br.com.g6.orgfinanceiro.util.authorization
import br.com.g6.orgfinanceiro.util.bearer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
class JWTAuthorizationFilter : BasicAuthenticationFilter {
    private var jwtUtil: JWTUtil

    private var userDetailService: UserDetailsService

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil, userDetailService: UserDetailsService) : super(authenticationManager) {
        this.jwtUtil = jwtUtil
        this.userDetailService = userDetailService
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = request.getHeader(authorization)
        if(authorizationHeader.startsWith(bearer)) {
            val auth = getAuthentication(authorizationHeader)
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(authorizationHeader: String?): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader?.substring(7) ?: ""
        if(jwtUtil.isTokenValid(token)) {
            val name = jwtUtil.getUserName(token)
            val user = userDetailService.loadUserByUsername(name)
            return UsernamePasswordAuthenticationToken(user, null, user.authorities)
        }
        throw UsernameNotFoundException("Auth invalid!")
    }
}

//
//class JWTAuthorizationFilter : BasicAuthenticationFilter {
//
//    @Autowired
//    private var jwtUtil: JWTUtil
//
//
//    @Autowired
//    private var userDetailService: UserDetailsService
//
//    val authorization = "Authorization"
//    val bearer = "Bearer"
//
//    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil, userDetailService: UserDetailsService) : super(authenticationManager) {
//        this.jwtUtil = jwtUtil
//        this.userDetailService = userDetailService
//    }
//
//    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
//        val authorizationHeader = request.getHeader(authorization)
//        if (authorizationHeader != null) {
//            if (authorizationHeader.startsWith(bearer)) {
//                val auth = getAuthentication(authorizationHeader)
//                SecurityContextHolder.getContext().authentication = auth
//            }
//        }
//        chain.doFilter(request, response)
//    }
//
//    private fun getAuthentication(authorizationHeader: String?): UsernamePasswordAuthenticationToken {
//        val token = authorizationHeader?.substring(7) ?: ""
//        if(jwtUtil.isTokenValid(token)) {
//            val username = jwtUtil.getUserName(token)
//            val user = userDetailService.loadUserByUsername(username)
//            return UsernamePasswordAuthenticationToken(user, null, user.authorities)
//        }
//        throw UsernameNotFoundException("Auth invalid!")
//    }
//}
//
//    //    private fun parseJwt(request: String): String {
////
////        val headerAuth = request.getHeader("Auth")
////        return if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
////            headerAuth.substring(7, headerAuth.length)
////        } else null
////    }
