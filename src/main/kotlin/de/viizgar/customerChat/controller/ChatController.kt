package de.viizgar.customerChat.controller

import de.viizgar.customerChat.domain.Message
import de.viizgar.customerChat.dto.ChatSessionDTO
import de.viizgar.customerChat.dto.MessageDTO
import de.viizgar.customerChat.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/chat")
class ChatController {

    @Autowired
    private lateinit var chatService: ChatService

    @PostMapping("/{id}")
    fun appendMessage(
        @RequestHeader(value = "userId", required = true) userId: Long,
        @PathVariable id: Long,
        @RequestBody message: String
    ): MessageDTO {
        val chatSession = chatService.getChatSession(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No session found for id $id")

        val messageObj = chatService.appendMessage(chatSession, userId, message)
        return MessageDTO(
            username = messageObj.sender.username,
            timestamp = messageObj.timestamp,
            content = messageObj.content
        )
    }

    @PostMapping("")
    fun createChatSession(@RequestHeader(value = "customerId", required = true) customerId: Long): ChatSessionDTO {
        val chatServiceObj = chatService.createChatSession(customerId)
        return ChatSessionDTO(
            id = chatServiceObj.id,
            agent = chatServiceObj.agent.username,
            customer = chatServiceObj.customer.username
        )
    }

    @DeleteMapping("/{id}")
    fun deleteChatSession(@PathVariable id: Long) {
        return chatService.deleteChatSession(id)
    }

    @GetMapping("/{id}/messages")
    fun getMessages(@PathVariable id: Long): List<MessageDTO> {
        val chatSession = chatService.getChatSession(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No session found for id $id")

        return chatSession.messages.map { message: Message ->
            MessageDTO(
                content = message.content,
                timestamp = message.timestamp,
                username = message.sender.username
            )
        }
    }


}