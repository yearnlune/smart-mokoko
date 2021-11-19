package lab.yearnlune

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<SmartMokokoApplication>(*args)
}

@SpringBootApplication
class SmartMokokoApplication