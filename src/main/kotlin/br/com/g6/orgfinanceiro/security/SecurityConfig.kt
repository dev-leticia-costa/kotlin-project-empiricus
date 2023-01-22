package br.com.g6.orgfinanceiro.security

import br.com.g6.orgfinanceiro.security.dto.JWTAuthenticationFilter
import br.com.g6.orgfinanceiro.security.dto.JWTAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder




@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtUtil : JWTUtil

    //--método configurar para tratar requisições: permite todas as rotas passadas
    // como parâmetro no antMatchers
    override fun configure(http: HttpSecurity?) {
        if (http != null) {
//----CRFS: é um token que é utilizado em aplicações MVC  pelo Spring para cada HTTP request
            //CSRF é habilitado pelo Security e desabilitando, pode-se autorizar as seguintes requisições (permitAll).
            // --As demais devem ser autenticadas.

            http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/signup").permitAll()
//                .antMatchers(HttpMethod.GET,"/user").permitAll()
//                .antMatchers(HttpMethod.GET,"/user/{userId}").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/user/{userId}").permitAll()
//                .antMatchers(HttpMethod.PUT,"/user/{userId}").permitAll()
                .anyRequest().authenticated()

        }
//--    //-----CONFERIR PATHS (https://cursos.alura.com.br/forum/topico-dois-ou-mais-roles-66327)
        //antMatcher() is a method of HttpSecurity,
        // iBasically, http.antMatcher() tells Spring to only configure HttpSecurity if the path matches this pattern.
        //The authorizeRequests().antMatchers() is then used to apply authorization to one or more paths you specify in antMatchers().
        //*STACKOVERFLOW*

        if (http != null) {
//JTWAut herda de UsernamePasswordAuthenticationFilter
            //adicionando filtro de autenticação nas requisições
            http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil = jwtUtil))
           http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil = jwtUtil, userDetailService = userDetailsService))
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //desabilita a autenticação por sessao
            //
        }
    }

    //--instancia a classe de criptografia
//    @Bean
//    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
//        return BCryptPasswordEncoder()
//    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
    //criptografia da senha
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

}