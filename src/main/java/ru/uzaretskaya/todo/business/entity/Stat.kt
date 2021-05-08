package ru.uzaretskaya.todo.business.entity;

import ru.uzaretskaya.todo.auth.entity.User
import ru.uzaretskaya.todo.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Stat(

    @Column(name = "completed_total", updatable = false)
    val completedTotal: Long,

    @Column(name = "uncompleted_total", updatable = false)
    val uncompletedTotal: Long,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User

) : BaseEntity<Long>()
