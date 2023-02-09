package br.com.g6.orgfinanceiro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class OrgFinanceiroApplication {
	@GetMapping("/hello")
	fun hello() = "Hello World"
}

fun main(args: Array<String>) {
	runApplication<OrgFinanceiroApplication>(*args)

}
