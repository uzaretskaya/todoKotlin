package ru.uzaretskaya.todo.business.entity;

import org.springframework.data.util.ProxyUtils
import ru.uzaretskaya.todo.auth.entity.User
import ru.uzaretskaya.todo.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Category(

    @Column
    val title: String?,

    @Column(name = "completed_count", updatable = false)
    val completedCount: Long?,

    @Column(name = "uncompleted_count", updatable = false)
    val uncompletedCount: Long?,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User?
) : BaseEntity<Long>() {

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as Category

        return this.title == other.title
                && this.user == other.user
    }

    override fun hashCode(): Int {
        return title.hashCode() + user.hashCode()
    }
}
