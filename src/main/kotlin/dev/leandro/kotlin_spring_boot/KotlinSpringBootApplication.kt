package dev.leandro.kotlin_spring_boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringBootApplication>(*args)
}
