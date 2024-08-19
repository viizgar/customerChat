package de.viizgar.customerChat.service

import de.viizgar.customerChat.domain.User
import de.viizgar.customerChat.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun create(user: User): User {
        return userRepository.save(user)
    }

    override fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    override fun findAgents(): List<User> {
        return userRepository.findAll().filter { user -> user.isAgent }
    }


    override fun get(id: Long): User {
        return userRepository.findById(id).get()
    }
}