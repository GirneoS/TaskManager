package org.example.taskmanager.model

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val title: String,
    private val body: String,
    @Enumerated(EnumType.STRING)
    private val status: TaskStatus,
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private val user: UserEntity,
)