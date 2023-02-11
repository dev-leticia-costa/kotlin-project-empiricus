package br.com.g6.orgfinanceiro.services

import br.com.g6.orgfinanceiro.dto.BalanceDTO
import br.com.g6.orgfinanceiro.dto.MovementDTO
import br.com.g6.orgfinanceiro.repository.MovementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
//@RequiredArgsConstructor
class MovementService {

    @Autowired
    lateinit var repository: MovementRepository

    /*
    @Autowired
    private val userDetailsService: UserDetailsServiceImpl? = null

    @Autowired
    private val userRepository: UserRepository? = null

    //~~método para retornar qual usuário está logado ~~
    private fun getCurrentUser(): Optional<Users?>? {
        val userDetails: UserDetails =
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() as UserDetails
        return userRepository.findByUsername(userDetails.getUsername())
    }

    //~~método para salvar movimentações~~
    fun save(createMovement: Movement): Movement? {
        return try {
            val user: Optional<Users> = getCurrentUser()
            if (user.isPresent()) {
                createMovement.setUser(user.get())
                return movementRepository.save(createMovement)
            }
            null
            //createMovement.setUser(userDetails );
        } catch (e: Exception) {
            throw RuntimeException("Erro ao salvar movimento" + e.message)
        }
    }

    //~~método para os filtros~~
    fun findByFilter(filter: MovementDto): List<Movement>? {
        return try {
            val user: Optional<Users> = getCurrentUser()
            if (user.isPresent()) {
                val filterMovement = FilterMovement()
                filter.setIdUsuario(user.get().getId())
                return movementRepository.findAll(filterMovement.toSpecification(filter))
            }
            null
        } catch (e: Exception) {
            throw RuntimeException("Erro na consulta" + e.message)
        }
    }

    //~~deletar movimento pelo id~~
    fun deleteById(idMovement: Long) {
        try {
            val user: Optional<Users> = getCurrentUser()
            if (user.isPresent()) {
                movementRepository.deleteById(idMovement)
            }
        } catch (e: Exception) {
            throw RuntimeException("Movimento não encontrado " + idMovement + ": " + e.message)
        }
    }
    */
    //~~método para pegar o saldo~~
    fun getBalance(): BalanceDTO {
        val response = BalanceDTO()

        // Credit
        val filter = MovementDTO()
        filter.typeMovement = 1

        var filterMovement = FilterMovementSpecification(filter)
        var listCredit = repository.findAll(filterMovement)
        var totalCredit = 0.0

        for (credit in listCredit) {
            totalCredit += credit.valueMovement
        }
        response.setCredit(totalCredit)

        // Debt
        filter.typeMovement = 2

        filterMovement = FilterMovementSpecification(filter)
        var listDebt = repository.findAll(filterMovement)
        var totalDebt = 0.0

        for (debt in listDebt) {
            totalDebt += debt.valueMovement
        }
        response.setDebt(totalDebt)

        // Saldo
        response.setBalance(totalCredit - totalDebt)

        return response
    }
}