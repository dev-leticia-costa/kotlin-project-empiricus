package br.com.g6.orgfinanceiro.repository

import br.com.g6.orgfinanceiro.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//interface que extends da JPArEPOSITORY, para fazer todo o CRUD de acesso ao baco de dados
@Repository
interface UserRepository : JpaRepository<Users, Long> {
    fun findByEmail(email: String?): Users?
    fun findById(userId:Long?): Users?
//    fun login(email:String, passwordAuthentication: PasswordAuthentication)
//    fun itMac


//    abstract fun save(userLogin: Users?): Users
//    fun findBySlug(slug: String): Users?



}