package de.viizgar.customerChat.service

import de.viizgar.customerChat.domain.User
import org.springframework.stereotype.Service

@Service
interface UserService {

    /**
     * It creates a new persistent user
     */
    fun create(user: User): User

    /**
     * It deletes, if exists, an existing user
     */
    fun delete(id: Long)

    /**
     * It returns the list of all existing agent users
     */
    fun findAgents(): List<User>


    /**
     * It returns a user by id
     */
    fun get(id: Long): User?

    /**
     * It returns all users
     */
    fun getAll(): List<User>
}