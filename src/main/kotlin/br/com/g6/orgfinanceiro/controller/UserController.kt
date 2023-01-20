package br.com.g6.orgfinanceiro.controller

import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.model.Users
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

//porta de entrada da aplicação, onde chegam as requisições
//anotações e recursos
@RestController
@RequestMapping("/user")
//parametros do construtor: entender que a interface é um Bean e fazer a injeção de dependência
class UserController{
    @Autowired
    private lateinit var repository: UserRepository

  //Autowired??
    //receber requisição como método http post
//    @PostMapping("/signin")
//    //quando tiver request tem que ter a response entity?
//    fun create(@RequestBody user: Users) : ResponseEntity<Users> = ResponseEntity.ok(repository.save(user))

    @PostMapping("/signup")
    //quando tiver request tem que ter a response entity?
    fun create(@RequestBody user: Users) : ResponseEntity<Users> = ResponseEntity.ok(repository.save(user))

//    @PostMapping("/login")
//    fun login (@RequestBody email: String, password: String) : Unit =
//        repository.PasswordAuthentication(email, password)
//
////
//fun PasswordAuthentication(@RequestBody email: String, password: CharArray) : Unit =
//    repository
//    .findByEmail(email)
//    .ifPresent{
//
//        protected open fun getPasswordAuthentication(): PasswordAuthentication!
//
//        ResponseEntity.ok("Usuário deletado com sucesso!")
//        (repository.save(it))}
//}

//}

//
//        ResponseEntity.ok("Usuário deletado com sucesso!")
//        (repository.delete(it))}
//}
//    : Optional<UserLogin?>?): ResponseEntity<UserLogin> {
//        return userService!!.logar(userLogin)
//            .map { resp: UserLogin -> ResponseEntity.ok(resp) }
//            .orElse(
//                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .build()
//            )
//    }


    //retorna uma response entity com uma lista de accounts
    @GetMapping("/users")
    fun read() : ResponseEntity<MutableList<Users>> = ResponseEntity.ok(repository.findAll())


    @GetMapping(value = ["/{userId}"])
    fun getById(@PathVariable userId: Long) : ResponseEntity<Users?> =
        repository.findById(userId).map {
            ResponseEntity.ok(it) // ponteiro/this
        }.orElse(ResponseEntity.notFound().build())
//
//    @GetMapping("/{slug}")
//    fun findOne(@PathVariable slug: String) =
//        repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
//
//}


//    @PostMapping("/login")
//    fun login (@RequestBody user: Users) {}


//    @PostMapping("/logins")
//    fun authentication(@RequestBody email: String, password: String): ResponseEntity<Void> {
//
//        repository.findByEmail(email).map { resp: Users -> ResponseEntity.ok(resp) }
//            .orElse(
//                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .build()
//            )
//    }

    @PutMapping(value = ["/{userId}"])
    fun update (@PathVariable userId: Long, @RequestBody user: Users) : ResponseEntity<Users> {
        //ver se o usuário existe - se sim, coloca o retorna na variável
        val userUpdateOptional = repository.findById(userId) //or if( !userUpdateOtiona.isPresent)
        val userUpdateSave = userUpdateOptional
            .orElseThrow { RuntimeException("User $userId not found!")}
            .copy(name = user.name, email = user.email, password = user.password)
        return ResponseEntity.ok(repository.save(userUpdateSave))

        }
//            repository.userUpdate(it)



    @DeleteMapping(value = ["/{userId}"])
    fun delete (@PathVariable userId: Long) : Unit =
        repository
            .findById(userId)
            .ifPresent{

                ResponseEntity.ok("Usuário deletado com sucesso!")
                (repository.delete(it))}
        }//não retorna a resposta


//    }.orElse(ResponseEntity.notFound().build())
    //findById -> retorna um Optional - get para extrair o dado
//retornar uma mensagem para o usuário




//late init