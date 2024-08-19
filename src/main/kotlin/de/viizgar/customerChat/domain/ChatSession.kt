package de.viizgar.customerChat.domain

import jakarta.persistence.*

@Entity
class ChatSession(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    var customer: User = User(),
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    var agent: User = User()
)
