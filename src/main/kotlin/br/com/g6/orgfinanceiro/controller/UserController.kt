package br.com.g6.orgfinanceiro.controller



import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

//porta de entrada da aplicação, onde chegam as requisições
//anotações e recursos
//parametros do construtor/atributos: Beans e injeção de dependência
@RestController
@RequestMapping("/user")
class UserController{
    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
   private lateinit var service: UsersService

    @Autowired(required=true)
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

//    @PostMapping("/signup")
//    fun save(@RequestBody user: User): ResponseEntity<User> {
//         user.password = bCryptPasswordEncoder.encode(user.password)
//        return ResponseEntity.ok(repository.save(user))
//    }

    @PostMapping ("/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> {
        val userCreated = service.create(user)
        return ResponseEntity.created(URI("")).body(userCreated)
    }


//    @PostMapping("/signup")
//    fun create(@RequestBody user: User) : ResponseEntity<User> {
//        return ResponseEntity.ok(service.save(user))}

    @GetMapping("/users")
    fun read() : Any{
       if(service.findUsers().isNullOrEmpty()){
         return ResponseEntity.notFound()
       }
    return ResponseEntity.ok(service.findUsers()!!)}







    @GetMapping(value = ["/{userId}"])
    fun getById(@PathVariable userId: Long) : ResponseEntity<User?> =
        repository.findById(userId).map {
            ResponseEntity.ok(it) // ponteiro/this
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping(value = ["/{userId}"])
    fun update (@PathVariable userId: Long, @RequestBody user: User) : ResponseEntity<User> {
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
            .ifPresent{ ResponseEntity.ok("Usuário deletado com sucesso!")
                (repository.delete(it))}
    }//não retorna a resposta


//    @Autowired
//    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

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
  //
//    @GetMapping("/{slug}")
//    fun findOne(@PathVariable slug: String) =
//        repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

//    }.orElse(ResponseEntity.notFound().build())
    //findById -> retorna um Optional - get para extrair o dado
//retornar uma mensagem para o usuário




//late init