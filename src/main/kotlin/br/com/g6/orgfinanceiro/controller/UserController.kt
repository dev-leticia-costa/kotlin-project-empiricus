package br.com.g6.orgfinanceiro.controller



import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.security.JWTUtil
import br.com.g6.orgfinanceiro.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

//porta de entrada da aplicação, onde chegam as requisições
//anotações e recursos
//parametros do construtor/atributos: Beans e injeção de dependência
@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    private lateinit var repository: UserRepository



    @Autowired
    private lateinit var jwtUtils: JWTUtil

    @Autowired
    private lateinit var service: UsersService

//    val userLoggedId = (SecurityContextHolder.getContext().authentication.principal as UserSecurity).id
//    val userLogged = yourUserService.findById(userLoggedId)
    @GetMapping("/me")
    fun me() = ResponseEntity.ok(service.myself()!!)


    @GetMapping("/users")
    fun read(): Any {
        if (service.findUsers().isNullOrEmpty()) {
            return ResponseEntity.notFound()
        }
        return ResponseEntity.ok(service.findUsers()!!)
    }

    @PostMapping("/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> {
        val userCreated = service.create(user)
        return ResponseEntity.created(URI("")).body(userCreated)
    }

    @GetMapping(value = ["/{userId}"])
    fun getById(@PathVariable userId: Long): ResponseEntity<User?> =
        repository.findById(userId).map {
            ResponseEntity.ok(it) // ponteiro/this
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping(value = ["/{userId}"])
    fun update(@PathVariable userId: Long, @RequestBody user: User): ResponseEntity<User> {
        //ver se o usuário existe - se sim, coloca o retorna na variável
        val userUpdateOptional = repository.findById(userId) //or if( !userUpdateOtiona.isPresent)
        val userUpdateSave = userUpdateOptional
            .orElseThrow { RuntimeException("User $userId not found!") }
            .copy(name = user.name, email = user.email, password = user.password)
        return ResponseEntity.ok(repository.save(userUpdateSave))

    }

    @DeleteMapping(value = ["/{userId}"])
    fun delete(@PathVariable userId: Long): Unit =
        repository
            .findById(userId)
            .ifPresent {
                ResponseEntity.ok("Usuário deletado com sucesso!")
                (repository.delete(it))
            }
}
//não retorna a resposta