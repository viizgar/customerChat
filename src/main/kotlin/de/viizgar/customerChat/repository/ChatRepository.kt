package de.viizgar.customerChat.repository

import de.viizgar.customerChat.domain.ChatSession
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : CrudRepository<ChatSession, String>