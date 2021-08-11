package ru.uzaretskaya.todo.auth.controller

import lombok.extern.java.Log
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.uzaretskaya.todo.auth.entity.Activity
import ru.uzaretskaya.todo.auth.entity.User
import ru.uzaretskaya.todo.auth.exception.RoleNotFoundException
import ru.uzaretskaya.todo.auth.exception.UsernameOrEmailExistsException
import ru.uzaretskaya.todo.auth.service.UserService
import ru.uzaretskaya.todo.auth.service.UserService.Companion.DEFAULT_ROLE
import java.lang.String.format
import java.util.UUID
import javax.validation.Valid


@RestController
@RequestMapping("/auth")
@Log
class AuthController(
    val userService: UserService,
    val encoder: PasswordEncoder,
    val authenticationManager: AuthenticationManager
) {

    @PutMapping("/register")
    @Throws(UsernameOrEmailExistsException::class, RoleNotFoundException::class)
    fun register(@RequestBody user: @Valid User): ResponseEntity<User> {
        if (userService.exists(user.username, user.email)) {
            throw UsernameOrEmailExistsException("Username or email already exists!")
        }
        user.password = encoder.encode(user.password)
        val userRole = userService.findByName(DEFAULT_ROLE)
            .orElseThrow {
                RoleNotFoundException(
                    format("Default role %s not found!", DEFAULT_ROLE)
                )
            }
        user.roles.add(userRole)
        val activity = Activity(user, UUID.randomUUID().toString())
        userService.register(user, activity)
        return ResponseEntity.ok().build()
    }
}