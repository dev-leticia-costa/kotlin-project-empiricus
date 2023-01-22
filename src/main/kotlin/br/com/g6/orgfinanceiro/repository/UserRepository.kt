package br.com.g6.orgfinanceiro.repository

import br.com.g6.orgfinanceiro.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//interface que herda do JPARepository, para fazer o CRUD de acesso ao banco de dados
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String?): User?
    fun findById(userId:Long?): User?
//    fun login(email:String, passwordAuthentication: PasswordAuthentication)
//    fun itMac
//    abstract fun save(userLogin: Users?): Users
//    fun findBySlug(slug: String): Users?



}