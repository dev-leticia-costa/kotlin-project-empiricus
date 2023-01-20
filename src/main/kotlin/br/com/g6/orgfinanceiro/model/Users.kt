package br.com.g6.orgfinanceiro.model

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "usuario")//nomear tabela como usuário
 data class Users (
    //ver quando usar dataclass - necessidade

    @Id @GeneratedValue //geração automática de id
     val id: Long? = null,
    @NotNull  //ver anotaçãao @Size(min = 1, max = 100
    var name: String,
    @NotNull
    var email: String,
    @NotNull  var password: String){

   //   fun ifPresent(email: String, function: () -> ResponseEntity<String>): ResponseEntity<Users> {
//
//   }
//init{
//   require()
//}
   override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as Users

      if (id != other.id) return false
      if (name != other.name) return false
      if (email != other.email) return false
      if (!password.contentEquals(other.password)) return false

      return true
   }

    /*
   override fun hashCode(): Int {
      var result = id?.hashCode() ?: 0
      result = 31 * result + name.hashCode()
      result = 31 * result + email.hashCode()
      result = 31 * result + password.contentHashCode()
      return result
   }

     */
    init{
        fun login(){}
    }
}
////    var token: String)
//    constructor ( email: String, password: String){
//
//        this.email = email
//        this.password = password
//    }
//data classe gera alguns atributos automáticos como getters and setters, construtores e outros
//}
//If the primary constructor does not have any annotations or visibility modifiers, the constructor keyword can be omitted:
//dúvida: como fazer um construtor secundário ou uma herança para a classe Users chamada UsersLogin com atributos específicos?
//visibilidade: