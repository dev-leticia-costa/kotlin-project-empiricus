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
    @Temporal(TemporalType.TIMESTAMP)
    var dueDate: Date? = null,

    @NotNull
    @Size (min = 1, max = 500)
    var descriptionMovement: String? = null,

    var seqParcel: Int = 0,

    //@ManyToOne
//@JsonIgnoreProperties("movementList")
//@JoinColumn(name = "id")
//private var user: Users? = null,
        @NotNull
        @Enumerated(EnumType.ORDINAL)
        var typeMovement: TypeMovement? = null,

        @NotNull
        var wasPaid: Boolean? = null

         )