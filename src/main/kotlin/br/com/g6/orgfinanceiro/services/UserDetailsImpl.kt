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
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

}