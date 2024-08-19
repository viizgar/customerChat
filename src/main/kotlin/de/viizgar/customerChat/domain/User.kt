package de.viizgar.customerChat.domain

import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val username: String = "",

    @Column(nullable = false)
    val isAgent: Boolean = false
)
