package de.viizgar.customerChat.dto

import java.time.LocalDateTime

class MessageResponseDTO(
    val username: String,
    val timestamp: LocalDateTime,
    val content: String
)