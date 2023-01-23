package br.com.g6.orgfinanceiro.security

import br.com.g6.orgfinanceiro.model.ERole
import br.com.g6.orgfinanceiro.model.User
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl(private val user : User) : UserDetails {

////8888
    override fun getAuthorities(): MutableCollection<out GrantedAuthority?> {
    val authorities = ArrayList<SimpleGrantedAuthority>()
    authorities.add(SimpleGrantedAuthority(ERole.ROLE_USER.name))
    return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }
    override fun getUsername(): String {
        return user.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }

}