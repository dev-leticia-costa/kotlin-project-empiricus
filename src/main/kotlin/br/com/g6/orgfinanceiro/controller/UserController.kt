package br.com.g6.orgfinanceiro.controller



import br.com.g6.orgfinanceiro.model.ERole
import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.repository.UserRepository
import br.com.g6.orgfinanceiro.security.JWTUtil
import br.com.g6.orgfinanceiro.security.JwtResponse
import br.com.g6.orgfinanceiro.security.UserDetailsImpl
import br.com.g6.orgfinanceiro.security.dto.Credentials
import br.com.g6.orgfinanceiro.service.UserDetailsServiceImpl
import br.com.g6.orgfinanceiro.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors
import javax.management.relation.Role

//porta de entrada da aplicação, onde chegam as requisições
//anotações e recursos
//parametros do construtor/atributos: Beans e injeção de dependência
@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtUtils: JWTUtil

    @Autowired
    private lateinit var service: UsersService

    @Autowired
    private lateinit var userDetailsImpl: UserDetailsImpl


//    @Autowired(required=true)
//    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @PostMapping("/signup")
    fun signup(@RequestBody user: User): ResponseEntity<User> {
        repository
            .findByEmail(user.email)


       userDetailsImpl.apply { SimpleGrantedAuthority(ERole.ROLE_USER.name) }
//
//        roles.add(userRole);
        return ResponseEntity.ok(repository.save(user))
    }
//            .ifPresent{ ResponseEntity.notFound()
//                (repository.delete(it))}
//        if (repository.findByEmail(user.email)) {
//            return ResponseEntity
//                .badRequest()
//                .body(new MessageResponse("E-mail já cadastrado"));
//        }
////         user.password = bCryptPasswordEncoder.encode(user.password)

//

//@PostMapping("/signup")
//  fun ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Nome de usuário já cadastrado!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("E-mail já cadastrado"));
//    }
//
//    // signup request - dto
//    User user = new User(signUpRequest.getUsername(),
//               signUpRequest.getEmail(),
//               encoder.encode(signUpRequest.getPassword()));
//
//    Set<String> strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "mod":
//          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(modRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }


//    @PostMapping ("/signup")
//    fun myself(): String? {
//        return userRepository.findByEmail(getCurrentUserEmail())?.name
//    }

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody credentials: Credentials?): ResponseEntity<*> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(credentials!!.email, credentials.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils!!.generateToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl //ver sintaxe
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority? -> item!!.authority }
            .collect(Collectors.toList())
        return ResponseEntity.ok(
            JwtResponse(
                jwt,
                userDetails.username,
                userDetails.password,
                roles
            )

        )
    }

//refatorar signup

    @GetMapping("/users")
    fun read(): Any {
        if (service.findUsers().isNullOrEmpty()) {
            return ResponseEntity.notFound()
        }
        return ResponseEntity.ok(service.findUsers()!!)
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