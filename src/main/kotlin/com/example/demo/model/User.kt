//package com.example.demo.model
//
//import org.jetbrains.annotations.NotNull
//import javax.persistence.Entity
//import javax.persistence.GeneratedValue
//import javax.persistence.Id
//
////---USAR DATA CLASS OU OPEN CLASS?
//@Entity(name = "usuario")//nomear tabela como usuário
//data class User
//    //ver quando usar dataclass - necessidade
//
//    @Id @GeneratedValue //geração automática de id
//    val id: Long? = null,
//    @NotNull  //ver anotaçãao @Size(min = 1, max = 100
//    var name: String,
//    @NotNull
//    var email: String,
//    @NotNull
//    var password: String) {
//

//----CONSTRUTOR PRIMARIO
//    constructor User( name: String, email: String, password: String) : this(name, email, password)
//   ----CONSTRUTOR SECUNDÁRIO
//   construtor para login (??)
////   constructor (email: String, password: String) : this(email, password) { this.email = email; this.password = password}
//}



//     id: Long? = null, //por que poderia ser nulo?
//atributos privados: não necessitam getter and setter, outro construtor?
//retirar o private?
//colocar optional?

