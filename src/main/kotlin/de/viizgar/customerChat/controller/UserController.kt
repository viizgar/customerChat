package de.viizgar.customerChat.controller

import de.viizgar.customerChat.domain.User
import de.viizgar.customerChat.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/user")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/")
    fun create(@RequestBody user: User): User {
        return userService.create(user)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return userService.delete(id)
    }

}