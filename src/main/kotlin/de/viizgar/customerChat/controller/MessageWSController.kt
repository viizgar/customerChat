package de.viizgar.customerChat.controller

import de.viizgar.customerChat.controller.exceptions.CustomWebSocketException
import de.viizgar.customerChat.dto.MessageResponseDTO
import de.viizgar.customerChat.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestParam


@Controller
class MessageWSController {
    data class WsMessageDTO(val content: String, val userId: Long, val chatId: Long)

    @Autowired
    private lateinit var chatService: ChatService

    @Autowired
    private lateinit var messagingTemplate: SimpMessagingTemplate

    @MessageMapping("/message/{chatId}")
    @Throws(Exception::class)
    fun appendMessage(
        @DestinationVariable chatId: Long,
        @RequestParam message: WsMessageDTO
    ): MessageResponseDTO {


        val chatSession = chatService.getChatSession(message.chatId)
            ?: throw CustomWebSocketException("No session found for id $message.chatId")

        val messageObj = chatService.appendMessage(chatSession, message.userId, message.content)
        val messageResponseDTO = MessageResponseDTO(
            username = messageObj.sender.username,
            timestamp = messageObj.timestamp,
            content = messageObj.content
        )

        messagingTemplate.convertAndSend("/topic/chat/" + message.chatId, messageResponseDTO)

        return messageResponseDTO
    }


}