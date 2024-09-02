package de.viizgar.customerChat.controller.exceptions

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller

class CustomWebSocketException(message: String) : RuntimeException(message)

@Controller
class WebSocketExceptionHandler {
    private val logger = LoggerFactory.getLogger(WebSocketExceptionHandler::class.java)

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    fun handleCustomException(exception: CustomWebSocketException): String {
        logger.error("Handling exception: ${exception.message}")
        return exception.message ?: "Unknown error occurred"
    }

}