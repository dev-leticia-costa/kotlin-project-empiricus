package br.com.g6.orgfinanceiro.security


class JwtResponse(
    var accessToken: String,
    var username: String,
    var email: String,
    var roles: MutableList<String?>,

    ) {

    var token = "Bearer"

}
