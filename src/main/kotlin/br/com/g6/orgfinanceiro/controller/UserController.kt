package br.com.g6.orgfinanceiro.controller



import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
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

//    @Autowired(required=true)
//    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

//    @PostMapping("/signup")
//    fun save(@RequestBody user: User): ResponseEntity<User> {
//         user.password = bCryptPasswordEncoder.encode(user.password)
//        return ResponseEntity.ok(repository.save(user))
//    }
//
    @PostMapping ("/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> =
        ResponseEntity.created(URI("")).body(service.create(user))
//        val userCreated = service.create(user)


//refatorar signup

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


//    @PostMapping("/signup")
//    fun registerUser(@RequestBody user: User): ResponseEntity<UserDetails> {
//    lateinit var userCreated: User
//        if ( user == service.create(user)) {
//            return ResponseEntity
//                .badRequest()
//                .body("Nome de usuário já cadastrado!")
//
//        }
//            return  ResponseEntity.ok("Usuário registrado com sucesso! Agora você tem mais controle sobre seus boletos :)"+ service.create(user))
//    }
////        user.create
//@PutMapping(value = ["/{userId}"])
//fun update (@PathVariable userId: Long, @RequestBody user: User) : ResponseEntity<User> {
//    //ver se o usuário existe - se sim, coloca o retorna na variável
//    val userUpdateOptional = repository.findById(userId) //or if( !userUpdateOtiona.isPresent)
//    val userUpdateSave = userUpdateOptional
//        .orElseThrow { RuntimeException("User $userId not found!")}
//        .copy(name = user.name, email = user.email, password = user.password)
//    return ResponseEntity.ok(repository.save(userUpdateSave))
//
//}


//        // signup request - dto
//        val (id, name, email, password) = User(
//          .getUsername(),
//            .getEmail(),
//            encoder.encode(getPassword())
//        )

//
//        if (userRepository!!.existsByEmail(signUpRequest.email)) {
//            return ResponseEntity
//                .badRequest()
//                .body(MessageResponse("E-mail já cadastrado"))
//        }
//
//        // signup request - dto
//        val user = User(
//            signUpRequest.username,
//            signUpRequest.email,
//            encoder!!.encode(signUpRequest.password)
//        )
//        val strRoles = signUpRequest.role
//        val roles: MutableSet<Role> = HashSet()
//        if (strRoles == null) {
//            val userRole = roleRepository!!.findByName(ERole.ROLE_USER)
//                .orElseThrow { RuntimeException("Error: Role is not found.") }
//            roles.add(userRole)
//        } else {
//            strRoles.forEach { role: String? ->
//                when (role) {
//                    "admin" -> {
//                        val adminRole =
//                            roleRepository!!.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow {
//                                    RuntimeException(
//                                        "Error: Role is not found."
//                                    )
//                                }
//                        roles.add(adminRole)
//                    }
//
//                    "mod" -> {
//                        val modRole =
//                            roleRepository!!.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow {
//                                    RuntimeException(
//                                        "Error: Role is not found."
//                                    )
//                                }
//                        roles.add(modRole)
//                    }
//
//                    else -> {
//                        val userRole =
//                            roleRepository!!.findByName(ERole.ROLE_USER)
//                                .orElseThrow {
//                                    RuntimeException(
//                                        "Error: Role is not found."
//                                    )
//                                }
//                        roles.add(userRole)
//                    }
//                }
//            }
//        }
//        user.roles = roles
//        var erroEmail = ""
//        try {
//            mailService!!.sendMail(
//                user.email,
//                "Boas vindas",
//                "Olá " + user.username + "! Seja bem vindo ao Meu Boleto Pago"
//            )
//        } catch (e: SMTPSendFailedException) {
//            erroEmail = "\nNão foi possível enviar e-mail de saudação."
//        }
//        userRepository!!.save(user)
//        //alterar save
//        return ResponseEntity.ok(MessageResponse("Usuário registrado com sucesso! Agora você tem mais controle sobre seus boletos :)$erroEmail"))
//    }

//    @Autowired
//    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

//    @PostMapping("/login")
//    fun login (@RequestBody email: String, password: String) :  ResponseEntity<User> {
//        service.PasswordAuthentication(email, password)
//        getCurrentUserEmail(
//    }
//        : Optional<UserLogin?>?): ResponseEntity<UserLogin> {
//            return userService!!.logar(userLogin)
//                .map { resp: UserLogin -> ResponseEntity.ok(resp) }
//                .orElse(
//                    ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .build()
//                )
//        }
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



    //retorna uma response entity com uma lista de accounts
  //
//    @GetMapping("/{slug}")
//    fun findOne(@PathVariable slug: String) =
//        repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

//    }.orElse(ResponseEntity.notFound().build())
    //findById -> retorna um Optional - get para extrair o dado
//retornar uma mensagem para o usuário




//late init