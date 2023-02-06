package br.com.g6.orgfinanceiro.cron

import br.com.g6.orgfinanceiro.dto.MovementDto
import br.com.g6.orgfinanceiro.repository.MovementRepository
import br.com.g6.orgfinanceiro.services.FilterMovementSpecification
import br.com.g6.orgfinanceiro.services.SpringMailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import java.time.LocalDate
import java.time.Period
import java.util.*


@Configuration
@EnableScheduling // Habilita agendamento
class CronJobConfig() {

    @Autowired
    lateinit var repository: MovementRepository

    @Autowired
    lateinit var emailService: SpringMailService;

    //second, minute, hour, day, month, weekday
    //@Scheduled(cron = "* * 8 * * ?")
    // AGENDAMENTO
    @Scheduled(cron = "0/30 * * * * ?")
    fun sendMail() {
        expenseDueDate() // Conta a vencer
        overdueAccount() // Conta Vencida
    }
    fun expenseDueDate() {
        var dto = MovementDto()
        dto.wasPaid = false
        dto.typeMovement = 2
        var dataFilter = LocalDate.now().plusDays(1)
        dto.dueDateIni = dataFilter
        dto.dueDateEnd = dataFilter
        // TODO filtrar usuário logado

        var filterMovement = FilterMovementSpecification(dto)
        var listMovements = repository.findAll(filterMovement)

        if(listMovements.isNotEmpty()) {
            var body: String

            if(listMovements.size == 1) {
                var mov = listMovements[0]
                body = "Amanhã vence a despesa:\n->${mov.descriptionMovement}, no valor de: R$ ${mov.valueMovement}"
            } else {
                body = "Amanhã vencem as seguintes despesas: \n"
                for (mov in listMovements) {
                    body += "-> ${mov.descriptionMovement}, no valor de: R$ ${mov.valueMovement} \n"
                }
            }

            val toEMail: String = "geisiele55@hotmail.com" // TODO email do usuário logado
            val subject: String = "Aviso de Vencimento"

            emailService.sendEmail(toEMail, subject, body)
        }
    }

    fun overdueAccount() {
        var dto: MovementDto = MovementDto()
        dto.wasPaid = false
        dto.typeMovement = 2
        var dataFilter = LocalDate.now().minusDays(1)
        dto.dueDateIni = dataFilter
        dto.dueDateEnd = dataFilter
        // TODO filtrar usuário logado

        var filterMovement = FilterMovementSpecification(dto)
        var listMovements = repository.findAll(filterMovement)

        if(listMovements.isNotEmpty()) {
            var body: String

            if(listMovements.size == 1) {
                var mov = listMovements[0]
                body = "Informativo:\n\nNão ocorreu o registro de pagamento da despesa:\n->${mov.descriptionMovement}, no valor de: R$ ${mov.valueMovement}"
            } else {
                body = "Informativo:\n\nNão ocorreu o registro de pagamento das seguintes despesas: \n"
                for (mov in listMovements) {
                    body += "-> ${mov.descriptionMovement}, no valor de: R$ ${mov.valueMovement} \n"
                }
            }

            val toEMail: String = "geisiele55@hotmail.com" // TODO email do usuário logado
            val subject: String = "Atenção! Você possui despesa(s) vencida(s)"

            emailService.sendEmail(toEMail, subject, body)
        }
    }

}