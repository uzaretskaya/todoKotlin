package ru.uzaretskaya.todo.auth.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.uzaretskaya.todo.auth.entity.Activity
import java.util.Optional

@Repository
interface ActivityRepository: CrudRepository<Activity, Long> {

    @Modifying
    @Transactional
    @Query("update Activity a set a.activated = :active where a.uuid = :uuid")
    fun changeActivated(@Param("uuid") uuid: String, @Param("active") active: Boolean): Int

    fun findByUserId(id: Long): Optional<Activity>

    fun findByUuid(uuid: String): Optional<Activity>
}