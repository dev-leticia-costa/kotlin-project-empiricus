package br.com.g6.orgfinanceiro.services

import br.com.g6.orgfinanceiro.dto.MovementDto
import br.com.g6.orgfinanceiro.model.Movement
import br.com.g6.orgfinanceiro.model.UserBalanceResponse
import br.com.g6.orgfinanceiro.model.Users
import br.com.g6.orgfinanceiro.repository.MovementRepository
import br.com.g6.orgfinanceiro.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
//@RequiredArgsConstructor
class MovementService {

    /*@Autowired
    private val movementRepository: MovementRepository? = null

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

    //~~método para pegar o saldo~~
    fun getBalance(): UserBalanceResponse? {
        return try {
            val user: Optional<Users> = getCurrentUser()
            if (user.isPresent()) {
                val filter = MovementDto()
                filter.setTypeMovement("receita")
                val receitas = findByFilter(filter)
                var totalReceita = 0.0
                val response = UserBalanceResponse()
                if (receitas!!.isEmpty()) {
                    response.setReceitas(totalReceita)
                }
                for (movement in receitas) {
                    totalReceita += movement.getValueMovement()
                }
                response.setReceitas(totalReceita)
                filter.setTypeMovement("despesa")
                val despesas = findByFilter(filter)
                var totalDespesa = 0.0
                if (despesas!!.isEmpty()) {
                    response.setDespesas(totalDespesa)
                }
                for (movement in despesas) {
                    totalDespesa += movement.getValueMovement()
                }
                response.setDespesas(totalDespesa)
                response.setSaldo(totalReceita - totalDespesa)
                response
            } else throw RuntimeException()
        } catch (e: Exception) {
            throw RuntimeException("Usuário não encontrado")
        }
    }
*/
}