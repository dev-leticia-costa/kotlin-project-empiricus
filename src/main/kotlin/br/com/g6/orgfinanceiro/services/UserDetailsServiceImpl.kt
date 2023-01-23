package br.com.g6.orgfinanceiro.service


import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.security.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

//    constructor(userRepository: UserRepository) {
//        this.userRepository = userRepository
//    }


    //--localiza o usuário no BD com base no email, retorna um usuário e suas permissoes  ----
    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)

        return UserDetailsImpl(user)
    }

//    @Transactional //criação de uma entidade
//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(username: String): UserDetails? {
//        val user: User = userRepository.findByUsername(username)
//            .orElseThrow { UsernameNotFoundException("Usuário não encontrado: $username") }
//        return UserDetailsImpl.build(user)
//    }
}