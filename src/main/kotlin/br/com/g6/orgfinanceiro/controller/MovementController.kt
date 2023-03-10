package br.com.g6.orgfinanceiro.controller

import br.com.g6.orgfinanceiro.dto.MovementDto
import br.com.g6.orgfinanceiro.model.Movement
import br.com.g6.orgfinanceiro.repository.MovementRepository
import br.com.g6.orgfinanceiro.services.FilterMovementSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException
import javax.validation.Valid

@RestController
@RequestMapping("/movement")
class MovementController {

    @Autowired
    lateinit var repository: MovementRepository

    @GetMapping("/filter")
    fun findByFilter(@Valid @RequestBody dto: MovementDto): MutableList<Movement> {
        var filterMovement = FilterMovementSpecification(dto)
        return repository.findAll(filterMovement)
    }

    @PostMapping()
    fun post(@Valid @RequestBody createMovement: Movement): Movement {
        return repository.save(createMovement)
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


}