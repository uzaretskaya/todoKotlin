package ru.uzaretskaya.todo.auth.entity;

import ru.uzaretskaya.todo.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.Email

@Entity
@Table(name = "USER_DATA")
class User(

    @Column
    val email: @Email String,

    @Column
    val password: String,

    @Column
    val username: String,

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    val activity: Activity,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "USER_ROLE",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role> = HashSet()

) : BaseEntity<Long>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return this.email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}
