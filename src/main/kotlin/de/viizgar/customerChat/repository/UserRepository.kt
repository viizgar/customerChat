package de.viizgar.customerChat.repository

import de.viizgar.customerChat.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>