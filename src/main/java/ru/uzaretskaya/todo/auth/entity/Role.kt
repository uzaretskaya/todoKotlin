package ru.uzaretskaya.todo.auth.entity;

import lombok.Getter
import lombok.Setter
import ru.uzaretskaya.todo.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "ROLE_DATA")
class Role(

    @Column
    val name: String

) : BaseEntity<Long>()
