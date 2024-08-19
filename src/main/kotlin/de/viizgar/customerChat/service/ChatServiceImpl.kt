package de.viizgar.customerChat.service

import de.viizgar.customerChat.domain.ChatSession
import de.viizgar.customerChat.repository.ChatRepository
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(
    val chatRepository: ChatRepository,
    val userService: UserService,
) : ChatService {


    override fun createChatSession(customerId: Long): ChatSession {
        val chatSession = ChatSession(customerId)
        chatSession.customer = userService.get(customerId)
        chatSession.agent = userService.findAgents().last()
        chatRepository.save(chatSession)
        return chatSession
    }

    override fun deleteChatSession(chatId: Long) {
        chatRepository.deleteById(chatId.toString())

    }

    override fun getChatSession(chatId: Long): ChatSession {
        return chatRepository.findById(chatId.toString()).get()
    }

    override fun appendMessage(chatId: Long, message: String): ChatSession {
        TODO("Not yet implemented")
    }


}