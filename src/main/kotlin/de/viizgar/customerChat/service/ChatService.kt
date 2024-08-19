package de.viizgar.customerChat.service

import de.viizgar.customerChat.domain.ChatSession
import org.springframework.stereotype.Service

@Service
interface ChatService {

    /**
     * It initializes a new Chat session between a customer and an existing customer service agent
     * @param customerId the customer id who wants support
     * @return the created session
     */
    fun createChatSession(customerId: Long): ChatSession

    /**
     * It deletes an existing chat session
     * @param chatId the chat id to be deleted
     */
    fun deleteChatSession(chatId: Long)

    /**
     * It returns the chat session information
     * @param chatId the chat id to be returned
     */
    fun getChatSession(chatId: Long): ChatSession

    /**
     * It appends a message to the message list of a certain chat session
     * @param chatId the chat id to append the message
     * @param message the text content to be appended to the chat message history
     */
    fun appendMessage(chatId: Long, message: String): ChatSession

}