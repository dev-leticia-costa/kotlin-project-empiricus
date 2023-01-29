package br.com.g6.orgfinanceiro.controller

import br.com.g6.orgfinanceiro.model.Movement
import br.com.g6.orgfinanceiro.model.UserBalanceResponse
import br.com.g6.orgfinanceiro.repository.MovementRepository
import br.com.g6.orgfinanceiro.services.MovementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException
import javax.validation.Valid

@RestController
@RequestMapping("/movement")
class MovementController {

    @Autowired
    lateinit var repository: MovementRepository

    @GetMapping("/all")
    fun index():MutableList<Movement>{
        return repository.findAll()
    }

    @PostMapping
    fun post(@Valid @RequestBody createMovement: Movement): Movement {
        return repository.save(createMovement)
    }

    @GetMapping("/{idMovement}")
    fun get(@PathVariable("idMovement") idMovement: Long):Movement {
        return repository.findById(idMovement).orElseThrow { EntityNotFoundException() }
    }

    @PutMapping("/{idMovement}")
    fun put(@PathVariable("idMovement") idMovement: Long,@Valid @RequestBody newMovement: Movement):Movement {
        val updateMovement = repository.findById(idMovement).orElseThrow {EntityNotFoundException()}

        updateMovement?.apply {
            this.descriptionMovement = newMovement.descriptionMovement
            this.valueMovement = newMovement.valueMovement
            this.dueDate = newMovement.dueDate
            this.seqParcel = newMovement.seqParcel
            this.wasPaid = newMovement.wasPaid
        }

        return repository.save(updateMovement)
    }

    @DeleteMapping("/{idMovement}")
    fun delete(@PathVariable("idMovement") idMovement: Long){
        val deleteMovement = repository.findById(idMovement).orElseThrow {EntityNotFoundException()}
        repository.delete(deleteMovement)
    }

    @DeleteMapping("/all")
    fun deleteAll() {
        return repository.deleteAll()
    }

    //@Autowired
    //private val movementService: MovementService? = null

   /* //	~filtra os movimentos~ //
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    fun findByFilter(@RequestBody filter: MovementDto?): ResponseEntity<List<Movement?>?>? {
        return try {
            ResponseEntity.ok(movementService.findByFilter(filter))
        } catch (e: Exception) {
            ResponseEntity<Any?>(e.message, HttpStatus.BAD_REQUEST)
        }
    }*/

    // ~cria uma nova movimentação ~ //
   /* @PostMapping("/save")
    //@PreAuthorize("hasRole('USER')")
    fun post(@RequestBody createMovement: Movement?): ResponseEntity<Movement?>? {
        return ResponseEntity.status(HttpStatus.CREATED) // STATUS DE QUE FOI CRIADO
            .body(movementService.save(createMovement))
    }*/

    /*@PutMapping("/change")
    //@PreAuthorize("hasRole('USER')")
    fun put(@RequestBody movement: Movement?): ResponseEntity<Movement?>? {
        return ResponseEntity.status(HttpStatus.OK).body(movementService.save(movement))
    }*/

    /*@DeleteMapping("/{idMovement}")
    //@PreAuthorize("hasRole('USER')")
    fun DeleteMovement(@PathVariable idMovement: Long?): ResponseEntity<Long?>? {
        movementService.deleteById(idMovement)
        return ResponseEntity(idMovement, HttpStatus.OK)
    }*/

    // ~exibe o saldo resultante das movimentações do usuário ~ //
   /* @GetMapping("/balance")
    //@PreAuthorize("hasRole('USER')")
    fun getBalance(): ResponseEntity<UserBalanceResponse?>? {
        return ResponseEntity.ok(movementService.getBalance())
    }*/


}