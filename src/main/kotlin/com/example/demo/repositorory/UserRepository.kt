package com.example.demo.repositorory

import com.example.demo.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.net.PasswordAuthentication
import java.util.*

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