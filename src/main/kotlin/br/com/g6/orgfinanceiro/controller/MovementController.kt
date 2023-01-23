package br.com.g6.orgfinanceiro.controller

import br.com.g6.orgfinanceiro.model.Movement
import br.com.g6.orgfinanceiro.model.User
import br.com.g6.orgfinanceiro.model.UserBalanceResponse
import br.com.g6.orgfinanceiro.repository.MovementRepository
//import br.com.g6.orgfinanceiro.services.MovementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException
//import javax.validation.Valid

@RestController
@RequestMapping("/movement")
class MovementController {

    @Autowired
    lateinit var repository: MovementRepository
//
//    @GetMapping
//    fun index(): MutableList<Movement> {
//        return repository.findAll()
//    }

    @PostMapping("/save")
    fun post(@RequestBody createMovement: Movement): Movement =
        repository.save(createMovement)

//
//    fun create(user: User): User {
//        user.password = bCryptPasswordEncoder.encode(user.password)
//
//        return userRepository.save(user)
//    }


    @GetMapping("/show/{idMovement}")
    fun get(@PathVariable("idMovement") idMovement: Long): Movement {
        return repository.findById(idMovement).orElseThrow { EntityNotFoundException() }
    }

//    @PutMapping("/change/{idMovement}")
//    fun put(@PathVariable("idMovement") idMovement: Long, @RequestBody newMovement: Movement): Movement {
//        val updateMovement = repository.findById(idMovement).orElseThrow { EntityNotFoundException() }
//
//        updateMovement?.apply {
//            this.descriptionMovement = newMovement.descriptionMovement
//            this.valueMovement = newMovement.valueMovement
//            this.dueDate = newMovement.dueDate
//            this.seqParcel = newMovement.seqParcel
//            this.wasPaid = newMovement.wasPaid
//        }
//
//        return repository.save(updateMovement)
//    }

    @DeleteMapping("/delete/{idMovement}")
    fun delete(@PathVariable("idMovement") idMovement: Long) {
        val deleteMovement = repository.findById(idMovement).orElseThrow { EntityNotFoundException() }
        repository.delete(deleteMovement)
    }

    @DeleteMapping("/delete")
    fun deleteAll() {
        return repository.deleteAll()
    }
}