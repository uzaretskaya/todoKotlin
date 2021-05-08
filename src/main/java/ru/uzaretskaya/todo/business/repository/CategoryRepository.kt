package ru.uzaretskaya.todo.business.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.uzaretskaya.todo.business.entity.Category

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {

    fun findByUserEmailOrderByTitleAsc(email:String): List<Category>

}