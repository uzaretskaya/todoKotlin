package ru.uzaretskaya.todo.auth.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.uzaretskaya.todo.auth.entity.Role
import ru.uzaretskaya.todo.auth.entity.User
import java.util.stream.Collectors

class UserDetailsImpl(val user: User): UserDetails {
    private val authorities: MutableCollection<GrantedAuthority> =
        user.roles.stream()
        .map { role: Role -> SimpleGrantedAuthority(role.name) }
        .collect(Collectors.toList())

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}