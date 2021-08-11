package ru.uzaretskaya.todo.auth.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.uzaretskaya.todo.auth.entity.User
import java.util.Optional

@Repository
interface UserRepository: CrudRepository<User, Long> {
    @Query("select case when count (u) > 0 then true else false end " +
                "from User u where lower(u.email) = lower(:email)")
    fun existsByEmail(@Param("email") email: String): Boolean

    @Query("select case when count (u) > 0 then true else false end " +
                "from User u where lower(u.username) = lower(:username)")
    fun existsByUsername(@Param("username") username: String): Boolean

    fun findByUsername(username: String?): Optional<User>

    fun findByEmail(email: String?): Optional<User>
}