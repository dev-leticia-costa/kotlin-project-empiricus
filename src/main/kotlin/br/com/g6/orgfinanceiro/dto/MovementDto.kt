package br.com.g6.orgfinanceiro.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*


data class MovementDto (

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    val dueDateIni: Date? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    val dueDateEnd: Date? = null,

    val typeMovement: String? = null,
    val descriptionMovement: String? = null,
    val idUser: Long? = null,
    val valueMovementIni: Double? = null,
    var valueMovementEnd: Double? = null,
    val wasPaid: Boolean? = null,
    val idMovement: Long? = null
){
/*

    fun getTypeMovement(): String? {
        return typeMovement
    }

    fun setTypeMovement(typeMovement: String?) {
        this.typeMovement = typeMovement
    }

    fun getPeriodoDe(): Date? {
        return periodoDe
    }

    fun setPeriodoDe(periodoDe: Date?) {
        this.periodoDe = periodoDe
    }

    fun getPeriodoAte(): Date? {
        return periodoAte
    }

    fun setPeriodoAte(periodoAte: Date?) {
        this.periodoAte = periodoAte
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getIdUsuario(): Long? {
        return idUsuario
    }

    fun setIdUsuario(idUsuario: Long?) {
        this.idUsuario = idUsuario
    }

    fun getValueMovementIni(): Double? {
        return valueMovementIni
    }

    fun setValueMovementIni(valueMovementIni: Double?) {
        this.valueMovementIni = valueMovementIni
    }

    fun getValueMovementEnd(): Double? {
        return valueMovementEnd
    }

    fun setValueMovementEnd(valueMovementEnd: Double?) {
        this.valueMovementEnd = valueMovementEnd
    }

    fun getWasPaid(): Boolean? {
        return wasPaid
    }

    fun setWasPaid(wasPaid: Boolean?) {
        this.wasPaid = wasPaid
    }

    fun getIdMovement(): Long? {
        return idMovement
    }

    fun setIdMovement(idMovement: Long?) {
        this.idMovement = idMovement
    }
*/
}