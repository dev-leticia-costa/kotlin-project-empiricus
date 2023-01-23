package br.com.g6.orgfinanceiro.model

data class UsersLogin(
    var name : String,
    var email: String,
    var password: String,
    var token: String
)
