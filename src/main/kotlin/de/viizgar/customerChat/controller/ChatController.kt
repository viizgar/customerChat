package de.viizgar.customerChat.controller

import de.viizgar.customerChat.domain.ChatSession
import de.viizgar.customerChat.domain.Message
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
    ): Message {
        val chatSession = chatService.getChatSession(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No session found for id $id")

        return chatService.appendMessage(chatSession, userId, message)
    }

    @PostMapping("/")
    fun createChatSession(@RequestHeader(value = "customerId", required = true) customerId: Long): ChatSession {
        return chatService.createChatSession(customerId)
    }

    @DeleteMapping("/{id}")
    fun deleteChatSession(@PathVariable id: Long) {
        return chatService.deleteChatSession(id)
    }

    @GetMapping("/{id}/messages")
    fun getMessages(@PathVariable id: Long): List<Message> {
        val chatSession = chatService.getChatSession(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No session found for id $id")

        return chatSession.messages
    }


}