package ru.uzaretskaya.todo.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Type
import ru.uzaretskaya.todo.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.validation.constraints.NotBlank

@Entity
@DynamicUpdate
class Activity(
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private val user: User,

    @Column(updatable = false)
    val uuid: @NotBlank String,

    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    val activated: Boolean = false,

    ) : BaseEntity<Long>()
