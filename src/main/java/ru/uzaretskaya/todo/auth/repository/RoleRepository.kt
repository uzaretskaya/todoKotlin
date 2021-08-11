package ru.uzaretskaya.todo.auth.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.uzaretskaya.todo.auth.entity.Role
import java.util.Optional

@Repository
interface RoleRepository: CrudRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>
}