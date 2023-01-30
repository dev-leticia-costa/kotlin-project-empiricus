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
)

