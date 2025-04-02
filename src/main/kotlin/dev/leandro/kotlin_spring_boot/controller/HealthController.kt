package dev.leandro.kotlin_spring_boot.controller

import dev.leandro.kotlin_spring_boot.model.Health
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class HealthController {
    val counter: AtomicLong = AtomicLong()
    @RequestMapping("/health")
    fun getHealth(@RequestParam(value="content", defaultValue = "I'm healthy and Kicking!") content: String): Health {
        return Health(counter.incrementAndGet(), content)
    }
}