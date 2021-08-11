package ru.uzaretskaya.todo.auth.exception

import org.springframework.security.core.AuthenticationException

class UsernameOrEmailExistsException: AuthenticationException {
    private lateinit var msg: String
    override lateinit var cause: Throwable

    constructor(msg: String): super(msg)
    constructor(msg: String, cause: Throwable): super(msg, cause)
}