package de.viizgar.customerChat.controller

import de.viizgar.customerChat.domain.ChatSession
import de.viizgar.customerChat.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
class ChatController {

    @Autowired
    private lateinit var chatService: ChatService

    @PostMapping("/")
    fun createChatSession(@RequestHeader(value = "customerId", required = true) customerId: Long): ChatSession {
        return chatService.createChatSession(customerId)
    }

    @DeleteMapping("/{id}")
    fun deleteChatSession(@PathVariable id: Long) {
        return chatService.deleteChatSession(id)
    }

    @GetMapping("/{id}")
    fun getChatSession(@PathVariable id: Long): ChatSession {
        return chatService.getChatSession(id)
    }

    @PostMapping("/{id}/")
    fun appendMessage(@PathVariable id: Long, @RequestBody message: String): ChatSession {
        return chatService.appendMessage(id, message)
    }
}