package ru.uzaretskaya.todo.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.uzaretskaya.todo.auth.entity.Activity
import ru.uzaretskaya.todo.auth.entity.User
import ru.uzaretskaya.todo.auth.repository.ActivityRepository
import ru.uzaretskaya.todo.auth.repository.RoleRepository
import ru.uzaretskaya.todo.auth.repository.UserRepository

@Service
@Transactional
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val activityRepository: ActivityRepository
) {

    companion object {
        val DEFAULT_ROLE = "USER"
    }

    fun register(user: User, activity: Activity) {
        userRepository.save(user)
        activityRepository.save(activity)
    }

    fun exists(username: String, email: String): Boolean =
        userRepository.existsByUsername(username) || userRepository.existsByEmail(email)

    fun findByName(name: String) = roleRepository.findByName(name)

    fun findActivityByUuid(uuid: String) = activityRepository.findByUuid(uuid)

    fun activate(uuid: String) = activityRepository.changeActivated(uuid, true)

    // maybe for future
    fun deactivate(uuid: String) = activityRepository.changeActivated(uuid, false)
}