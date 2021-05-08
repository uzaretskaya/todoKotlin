package ru.uzaretskaya.todo.business.util

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> ResponseEntity(message: String, status: HttpStatus): ResponseEntity<T> {
    val headers = HttpHeaders()
    headers.add("Custom-Header", message)
    return ResponseEntity(headers, status)
}