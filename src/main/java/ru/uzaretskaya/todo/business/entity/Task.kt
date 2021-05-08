package ru.uzaretskaya.todo.business.entity;

import ru.uzaretskaya.todo.auth.entity.User
import ru.uzaretskaya.todo.base.BaseEntity
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Task(

    @Column
    val title: String,

    @Column
    val completed: Short,

    @Column(name = "task_date")
    val taskDate: Date? = null,

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    val priority: Priority,

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    val category: Category? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User

) : BaseEntity<Long>()
