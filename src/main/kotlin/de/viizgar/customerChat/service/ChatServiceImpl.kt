package de.viizgar.customerChat.service

import de.viizgar.customerChat.domain.ChatSession
import de.viizgar.customerChat.domain.Message
import de.viizgar.customerChat.repository.ChatRepository
import de.viizgar.customerChat.repository.MessageRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull

@Service
class ChatServiceImpl(
    val chatRepository: ChatRepository,
    val userService: UserService,
    private val messageRepository: MessageRepository,
) : ChatService {


    override fun createChatSession(customerId: Long): ChatSession {
        val customer = userService.get(customerId)
        val agent = userService.findAgents().lastOrNull()
        if (customer != null && agent !== null) {
            val chatSession = ChatSession()
            chatSession.customer = customer
            chatSession.agent = agent
            chatRepository.save(chatSession)
            return chatSession
        } else {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Costumer or Agent not found. Without them a chhat cannot be started"
            )
        }
    }

    override fun deleteChatSession(chatId: Long) {
        chatRepository.deleteById(chatId)

    }

    override fun getChatSession(chatId: Long): ChatSession? {
        return chatRepository.findById(chatId).getOrNull()
    }

    override fun appendMessage(chat: ChatSession, userId: Long, message: String): Message {
        val user = userService.get(userId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        val msg = Message(content = message, session = chat, sender = user)

        messageRepository.save(msg)
        return msg

    }


}