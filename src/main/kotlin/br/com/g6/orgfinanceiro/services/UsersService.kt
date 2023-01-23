package br.com.g6.orgfinanceiro.services

import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.security.UserDetailsImpl
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
 class UsersService  {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun bcryptPassword(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)

        return userRepository.save(user)
    }


    private fun getCurrentUserEmail(token:String): String {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return user.username
    }


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    //    fun myself(): String? {
//        return userRepository.findByEmail(getCurrentUserEmail())?.name
//    }
    fun findUsers() : MutableList<User>? {
        //var mutableList : MutableList<User?> = userRepository.findAll()
        //return mutableList
        return  userRepository.findAll()
    }







}

