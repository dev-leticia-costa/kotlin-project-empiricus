package br.com.g6.orgfinanceiro.repository

import br.com.g6.orgfinanceiro.model.Movement
import br.com.g6.orgfinanceiro.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
//internal
interface MovementRepository : JpaRepository<Movement, Long>