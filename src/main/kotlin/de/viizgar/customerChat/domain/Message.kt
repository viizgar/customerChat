package de.viizgar.customerChat.domain

import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.random.Random

@Entity
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = Random.nextLong(until = 1_099_511_627_776),
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var sender: User = User(),
    var content: String = "",
    val timestamp: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatsession_id", referencedColumnName = "id")
    var session: ChatSession = ChatSession(),
)
