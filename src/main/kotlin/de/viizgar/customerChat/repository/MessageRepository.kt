package de.viizgar.customerChat.repository

import de.viizgar.customerChat.domain.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<Message, Long>