package de.viizgar.customerChat.domain

import jakarta.persistence.*

@Entity(name = "User")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val username: String = "",

    @Column(nullable = false)
    val isAgent: Boolean = false
)
