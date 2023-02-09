package br.com.g6.orgfinanceiro.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "users")//nomear tabela como usuário
 data class User (
    //ver quando usar dataclass - necessidade

    @Id @GeneratedValue //geração automática de id (strategy = GenerationType.IDENTITY)
    @JsonIgnore
     val id: Long? = 0,
 //ver anotaçãao @Size(min = 1, max = 100
    var name: String = "",

    var email: String = "",
    val fullname: String = "",

    var password: String)
    //---MÉTODOS DATA CLASS----


    /*
   override fun hashCode(): Int {
      var result = id?.hashCode() ?: 0
      result = 31 * result + name.hashCode()
      result = 31 * result + email.hashCode()
      result = 31 * result + password.contentHashCode()
      return result
   }

     */

//   fun ifPresent(email: String, function: () -> ResponseEntity<String>): ResponseEntity<Users> {
//
//   }
//init{
//   require()
//}
////    var token: String)
//    constructor ( email: String, password: String){
//
//        this.email = email
//        this.password = password
//    }
//data classe gera alguns atributos automáticos como getters and setters, construtores e outros
//If the primary constructor does not have any annotations or visibility modifiers, the constructor keyword can be omitted:
//dúvida: como fazer um construtor secundário ou uma herança para a classe Users  UsersLogin com atributos específicos?
//visibilidade: