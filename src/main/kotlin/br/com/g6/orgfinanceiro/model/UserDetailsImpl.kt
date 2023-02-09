package br.com.g6.orgfinanceiro.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl(
    private val user: User
//    val id: User,
//    val email: String,
//    private val password: String,
//    private val uAuthorities: Collection<GrantedAuthority>
) : UserDetails {

    ////8888
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()


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
}
//
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails

//class UserSecurity
//    (
//    val id: String,
//    val email: String,
//    private val uPassword: String,
//    private val uAuthorities: MutableCollection<GrantedAuthority>
//) : UserDetails {
//    override fun getAuthorities() = uAuthorities
//    override fun getPassword() = uPassword
//    override fun getUsername() = email
//    override fun isAccountNonExpired() = true
//    override fun isAccountNonLocked() = true
//    override fun isCredentialsNonExpired()= true
//    override fun isEnabled() = true
//}
//    lass UserSecurity
//    (
//    val id: String,
//    val email: String,
//    private val uPassword: String,
//    private val uAuthorities: MutableCollection<GrantedAuthority>
//    ) : UserDetails {
//        override fun getAuthorities() = uAuthorities
//        override fun getPassword() = uPassword
//        override fun getUsername() = email
//        override fun isAccountNonExpired() = true
//        override fun isAccountNonLocked() = true
//        override fun isCredentialsNonExpired()= true
//        override fun isEnabled() = true

