package de.viizgar.customerChat.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val sender: User = User(),
    val content: String = "",
    val timestamp: LocalDateTime = LocalDateTime.now(),
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "chatsession_id", referencedColumnName = "id")
    val session: ChatSession = ChatSession(),
)
