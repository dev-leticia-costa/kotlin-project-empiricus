package br.com.g6.orgfinanceiro.services

import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
 class UsersService  {
    @Autowired
    lateinit var userRepository: UserRepository

//    @Autowired
//    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder


    fun save(user: User): User {
    //    user.password = bCryptPasswordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun findUsers() : MutableList<User>? {
        //var mutableList : MutableList<User?> = userRepository.findAll()
        //return mutableList
        return  userRepository.findAll()



    }

}

