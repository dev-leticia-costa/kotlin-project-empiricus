package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
	val jdbcUrl = "jdbc:mysql://localhost/organizador_financeiro?createDatabaseIfNotExist=true"
}
