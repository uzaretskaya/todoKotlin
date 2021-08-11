package ru.uzaretskaya.todo.auth.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.uzaretskaya.todo.auth.repository.UserRepository

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository): UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val userOptional = userRepository.findByUsername(username)

        if (!userOptional.isPresent) {
            userRepository.findByEmail(username)
        }

        if (!userOptional.isPresent) {
            throw UsernameNotFoundException(String.format("User not found with email or username: %s", username))
        }

        return UserDetailsImpl(userOptional.get())
    }
}