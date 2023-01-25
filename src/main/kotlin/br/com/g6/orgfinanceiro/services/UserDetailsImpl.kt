package br.com.g6.orgfinanceiro.services

import br.com.g6.orgfinanceiro.model.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(user: Users?) : UserDetails{
//    @Serial
    private val serialVersionUID = 1L;

    //nome e senha
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null;
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}