package de.viizgar.customerChat.controller

import de.viizgar.customerChat.service.ChatService
import de.viizgar.customerChat.service.UserService
import org.apache.coyote.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var chatService: ChatService

    @GetMapping("/web/{chatId}")
    fun chatRoom(
        @PathVariable(name = "chatId") chatId: Long,
        @RequestParam(name = "userId") userId: Long,
        model: Model
    ): String {

        // Retrieve user and chat session
        val user = userService.get(userId) ?: throw ResourceNotFoundException("User not found")
        val chat = chatService.getChatSession(chatId) ?: throw ResourceNotFoundException("Chat not found")

        // Only users associated to a certain chat session can be retrieved
        if (chat.customer.id != user.id && chat.agent.id != user.id) {
            throw BadRequestException("Customer or Agent users doesn't exist")
        }

        // Add common attributes to the model
        model.addAttribute("senderUsername", user.username)
        model.addAttribute("chatId", chatId)
        model.addAttribute("senderId", userId)

        // Determine user role and receiver details
        val (receiverUsername: String, userRole: String) = if (user.isAgent) {
            chat.customer.username to "Agent"
        } else {
            chat.agent.username to "Customer"
        }

        // Add role and receiver details to the model
        model.addAttribute("receiverUsername", receiverUsername)
        model.addAttribute("userRole", userRole)

        return "index"
    }

}