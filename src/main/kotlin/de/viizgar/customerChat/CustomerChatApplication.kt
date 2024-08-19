package de.viizgar.customerChat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("de.viizgar.customerChat.domain")
class CustomerChatApplication

fun main(args: Array<String>) {
    runApplication<CustomerChatApplication>(*args)
}
