package br.com.g6.orgfinanceiro.model

import br.com.g6.orgfinanceiro.enumeration.TypeMovement
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.jetbrains.annotations.NotNull
import org.springframework.data.jpa.repository.Temporal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "Movement")
data class Movement (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idMovement: Long? = null,

    @NotNull
    var valueMovement: Double = 0.0,

    @NotNull
    @Temporal(TemporalType.DATE)
    var dueDate: Date? = null,

    @NotNull
    @Size (min = 1, max = 500)
    var descriptionMovement: String? = null,

    var seqParcel: Int = 0,

    @NotNull
    var typeMovement: Int? = null,

    @NotNull
    var wasPaid: Boolean? = null

    //@ManyToOne
    //@JsonIgnoreProperties("movementList")
    //@JoinColumn(name = "id")
    //private var user: Users? = null,
) {


    /*fun getIdMovement(): Long? {
        return idMovement
    }

    fun setIdMovement(idMovement: Long?) {
        this.idMovement = idMovement
    }

    fun getValueMovement(): Double {
        return valueMovement
    }

    fun setValueMovement(valueMovement: Double) {
        this.valueMovement = valueMovement
    }

    fun getDueDate(): Date? {
        return dueDate
    }

    fun setDueDate(dueDate: Date?) {
        this.dueDate = dueDate
    }

    fun getDescriptionMovement(): String? {
        return descriptionMovement
    }

    fun setDescriptionMovement(descriptionMovement: String?) {
        this.descriptionMovement = descriptionMovement
    }

    fun getSeqParcel(): Int {
        return seqParcel
    }

    fun setSeqParcel(seqParcel: Int) {
        this.seqParcel = seqParcel
    }

    fun getUser(): Users? {
        return user
    }

    fun setUser(user: Users?) {
        this.user = user
    }

    fun getTypeMovement(): TypeMovement? {
        return typeMovement
    }

    fun setTypeMovement(typeMovement: TypeMovement?) {
        this.typeMovement = typeMovement
    }

    fun getWasPaid(): Boolean? {
        return wasPaid
    }

    fun setWasPaid(wasPaid: Boolean?) {
        this.wasPaid = wasPaid
    }*/

}