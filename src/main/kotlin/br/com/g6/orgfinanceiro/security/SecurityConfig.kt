package br.com.g6.orgfinanceiro.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    //--método configurar para tratar requisições
    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/signup").permitAll()
                .anyRequest().authenticated()
        }
        if (http != null) {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
    }

    

}