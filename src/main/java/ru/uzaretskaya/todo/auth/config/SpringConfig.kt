package ru.uzaretskaya.todo.auth.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity(debug = true)
open class SpringConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.formLogin()?.disable()
        http?.httpBasic()?.disable()
        http?.requiresChannel()?.anyRequest()?.requiresSecure()
        http?.csrf()?.disable()
    }
}