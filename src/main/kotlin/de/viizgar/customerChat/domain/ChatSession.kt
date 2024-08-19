package de.viizgar.customerChat.domain

import jakarta.persistence.*

@Entity
class ChatSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne(cascade = [CascadeType.REFRESH])
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    var customer: User = User(),
    @OneToOne(cascade = [CascadeType.REFRESH])
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    var agent: User = User(),

    @OneToMany(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    var messages: List<Message> = emptyList()

)
