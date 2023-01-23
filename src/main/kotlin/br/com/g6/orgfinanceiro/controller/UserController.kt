package br.com.g6.orgfinanceiro.controller

import br.com.g6.orgfinanceiro.dto.UsersDTO
import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.model.Users
import br.com.g6.orgfinanceiro.model.UsersLogin
import br.com.g6.orgfinanceiro.services.UserService
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
import javax.validation.Valid

//porta de entrada da aplicação, onde chegam as requisições
//anotações e recursos
@RestController
@RequestMapping("/user")
//parametros do construtor: entender que a interface é um Bean e fazer a injeção de dependência
class UserController(private val repository: UserRepository) {

    @Autowired
    private lateinit var userService : UserService;

    //receber requisição como método http post
    @PostMapping("/signup")
    //quando tiver request tem que ter a response entity?
    fun create(@RequestBody @Valid usersDTO: UsersDTO) : ResponseEntity<Users> {
        var user : Users = Users(null, usersDTO.name, usersDTO.email, usersDTO.password)

        ResponseEntity.ok(userService.save(user))
    }
/*
         @PostMapping("/login")
            public ResponseEntity<UserLogin> authentication(@RequestBody Optional<UserLogin> userLogin){
                return userService.logar(userLogin)
                        .map(resp -> ResponseEntity.ok(resp))
                        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .build());
    }
 */
    @PostMapping("/login")
    fun authentication (@RequestBody usersLogin: UsersLogin?) : ResponseEntity<UsersLogin> {
        return userService.login(UsersLogin)


    }


    //retorna uma response entity com uma lista de accounts
    @GetMapping("/users")
    fun read() : ResponseEntity<MutableList<Users>> = ResponseEntity.ok(repository.findAll())


    @GetMapping(value = ["/{userId}"])
    fun getById(@PathVariable userId: Long) : ResponseEntity<Users?> =
        repository.findById(userId).map {
            ResponseEntity.ok(it) // ponteiro/this
        }.orElse(ResponseEntity.notFound().build())




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



    @DeleteMapping(value = ["/delete/{userId}"])
    fun delete (@PathVariable userId: Long) : Unit =
        repository
            .findById(userId)
            .ifPresent{

                ResponseEntity.ok("Usuário deletado com sucesso!")
                (repository.delete(it))}
        }


//    }.orElse(ResponseEntity.notFound().build())
    //findById -> retorna um Optional - get para extrair o dado
//retornar uma mensagem para o usuário

//late init para o user security