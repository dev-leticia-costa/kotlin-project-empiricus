package br.com.g6.orgfinanceiro.services

import br.com.g6.orgfinanceiro.model.Users
import br.com.g6.orgfinanceiro.model.UsersLogin
import br.com.g6.orgfinanceiro.repository.UserRepository
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.nio.charset.Charset

class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var enconder : BCryptPasswordEncoder

    fun save(user: Users): Users {
        user.password = enconder.encode(user.password)
        return userRepository.save(user)
    }

    /*
    public Optional<UserLogin> logar(Optional<UserLogin> userLogin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findByUserEmail(userLogin.get().getUserName());

        if(user.isPresent()){
            if(encoder.matches(userLogin.get().getPassword(), user.get().getUserPassword())){
                String auth = userLogin.get().getUserName() + ":" + userLogin.get().getPassword();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                userLogin.get().setToken(authHeader);
                userLogin.get().setName(user.get().getUserName());

                return userLogin;
            }
        }

        return null;
    }
     */

    fun login(usersLogin: UsersLogin?): UsersLogin? {
        var user : Users? = userRepository.findByEmail(usersLogin?.email)
        if(user != null && usersLogin != null){
            if(enconder.matches(usersLogin.password, user.password)){
                var auth: String = usersLogin.email + ":" + usersLogin.password
                var encodeAuth: ByteArray = Base64.encodeBase64(auth.toByteArray(Charset.forName("US-ASCII")))
                var authHeader: String = "Basic " + String(encodeAuth)
                usersLogin.token = authHeader
                usersLogin.email = user.email

                return usersLogin

            }
        }
        return null
    }

}